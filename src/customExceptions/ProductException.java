package customExceptions;

public class ProductException extends GUIAlertException {

    public ProductException(String message) {
        super(message);
        this.AlertTitle = "Product exception";
        this.AlertHeader = "There was an error while modifying the product";
    }

}
