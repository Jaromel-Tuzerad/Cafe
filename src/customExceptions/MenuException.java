package customExceptions;

public class MenuException extends GUIAlertException {

    public MenuException(String message) {
        super(message);
        this.AlertTitle = "Menu exception";
        this.AlertHeader = "There was an error while modifying the menu";
    }

}
