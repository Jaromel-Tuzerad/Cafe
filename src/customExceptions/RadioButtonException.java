package customExceptions;

public class RadioButtonException extends GUIAlertException {

    public RadioButtonException(String message) {
        super(message);
        this.AlertTitle = "Radio button exception";
        this.AlertHeader = "There was an error with the used radio buttons";
    }

}
