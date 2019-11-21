package customExceptions;

public class BalanceException extends GUIAlertException {

    public BalanceException(String message) {
        super(message);
        this.AlertTitle = "Balance exception";
        this.AlertHeader = "There was an error while changing the balance of the Cafe";
    }

}
