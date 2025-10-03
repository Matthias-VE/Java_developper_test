package eclipse_plugin_test.parts.views;


import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import eclipse_plugin_test.parts.events.EventTopics;
import eclipse_plugin_test.parts.util.JSONHelper;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

public class ResultsView {

    private Text textArea;

    @Inject
    IEventBroker eventBroker;

    @Inject @Optional
    void dataFetchedHandler(@UIEventTopic(EventTopics.DATA_FETCHED) String data) {
    	if (data == null || textArea == null || textArea.isDisposed()) {
            return;
        }

    	String prettyJson = JSONHelper.prettyPrintJson(data);
    	textArea.getDisplay().asyncExec(() -> textArea.setText(prettyJson));
    }

    @PostConstruct
    public void createControls(Composite parent) {
        textArea = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        textArea.setEditable(false);
    }
}
