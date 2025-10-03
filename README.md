# Java_developper_test

## Mock API

Run with `./gradlew bootRun` in the mock-api directory.
Local api server will start on port 8080

### GET /api/issues
Returns a list of issues.

## Eclipse E4 plugin
Run by opening the code in Eclipse and right click -> Run as ... -> Eclipse Application.
A new IDE window will open. Select Window -> Show View -> Other... -> Sample Category -> Sample Data and Result.
Now you should have the windows open that you selected in the above step. 
The Sample Data Window will contain a button. When Pressed, the Result Window is updated with data from the API.


## Design Notes
The Application has 2 Views in the views package. A Sample Data View and a Results View.
When the button in the Sample Data View is pressed, the application will async request the data from the api.
Using the Eclipse EventBroker, we can emit this data from one view to another. I used dependency injection to achieve this.
The EventBroker is injected into the emitter part and a handler method is injected into the receiving part.
The data is parsed and pretty printed with a simple custom helper method.
I chose not to use a JSON parsing library, keeping the application minimal. This results in Json string being the format of the data however.
