package eclipse_plugin_test.parts.views;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import eclipse_plugin_test.parts.events.EventTopics;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

public class SampleDataView {
	private Button fetchButton;
	private Logger logger;

	@Inject
	IEventBroker eventBroker;
	
    @Inject
    LoggerFactory loggerFactory;
	
    @PostConstruct
    void init() {
    	logger = loggerFactory.getLogger(getClass());
    }
    
	@PostConstruct
    public void createControls(Composite parent) {
        GridLayout layout = new GridLayout();
        layout.marginWidth = 10;
        layout.marginHeight = 10;
        parent.setLayout(layout);

        fetchButton = new Button(parent, SWT.PUSH);
        fetchButton.setText("Fetch Data");

        GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, false, false);
        gridData.widthHint = 120;
        gridData.heightHint = 40;
        fetchButton.setLayoutData(gridData);
        fetchButton.addListener(SWT.Selection, e -> fetchDataAsync());
    }
	

	private void fetchDataAsync() {
		if (fetchButton == null || fetchButton.isDisposed()) {
			return;
		}
		fetchButton.setEnabled(false);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/issues"))
                .GET()
                .build();
		CompletableFuture.supplyAsync(() -> {
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (Exception e) {
                logger.error("An error occured", e);
                return "Error: Request failed";
            }
        }).thenAccept(data -> {
            // Send event with the fetched data
            eventBroker.post(EventTopics.DATA_FETCHED, data);
            fetchButton.getDisplay().asyncExec(() -> {
            	fetchButton.setEnabled(true);
            });
        });
	}
	
}
