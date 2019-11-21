package customExceptions;

public abstract class GUIAlertException extends Exception {

    protected String AlertTitle;
    protected String AlertHeader;
    protected String AlertMessage;

    public GUIAlertException(String message) {
        this.AlertMessage = message;
    }

    public String getAlertTitle() {
        return AlertTitle;
    }

    public String getAlertHeader() {
        return AlertHeader;
    }

    public String getAlertMessage() {
        return AlertMessage;
    }
}
