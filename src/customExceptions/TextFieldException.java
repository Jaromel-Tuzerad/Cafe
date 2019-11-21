package customExceptions;

public class TextFieldException extends GUIAlertException {

    public TextFieldException(String message) {
        super(message);
        this.AlertTitle = "Text field exception";
        this.AlertHeader = "There is an error in the input text field(s)";
    }

}
