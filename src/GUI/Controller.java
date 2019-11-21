package GUI;

import backend.Dessert;
import backend.Drink;
import customExceptions.GUIAlertException;
import customExceptions.RadioButtonException;
import customExceptions.TextFieldException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import backend.Cafe;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Cafe cafe = new Cafe("Kavarna U Tri blbych kokotu");

    //Alert for showing errors
    private void callAlert(GUIAlertException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e.getAlertTitle());
        alert.setHeaderText(e.getAlertHeader());
        alert.setContentText(e.getAlertMessage());
        alert.showAndWait();
    }

    //Buy product (0, 0)
    @FXML
    private ChoiceBox buyListProducts;
    @FXML
    private TextField amountBuyProduct;

    //Change balance (0, 2)
    @FXML
    private Label balanceLabel = new Label();
    @FXML
    private TextField withdrawAmount = new TextField();
    @FXML
    private TextField depositAmount = new TextField();

    //Add product (1, 0)
    @FXML
    private TextField inputProductName;
    @FXML
    private TextField inputBuyPrice;
    @FXML
    private TextField inputSellPrice;
    @FXML
    private TextField inputWeightPerPortion;
    @FXML
    private RadioButton setTypeDrink;
    @FXML
    private RadioButton setTypeDessert;

    @FXML
    private ChoiceBox sellListProducts;
    @FXML
    private TextField amountSellProduct;

    //Remove product (1, 1)
    @FXML
    private ChoiceBox removeProductsList;

    //List products (2, 1)
    @FXML
    private GridPane menuGrid;

    //Withdraw money
    @FXML
    public void withdraw() {
        try {
            String amountString = withdrawAmount.getText();
            float amountFloat;
            if (amountString.isEmpty()) {
                throw new TextFieldException("One or more of the input text fields is empty");
            }
            try {
                 amountFloat = Float.parseFloat(withdrawAmount.getText());
            } catch(Exception e) {
                throw new TextFieldException("The input value is invalid");
            }
            cafe.changeBalance(0 - amountFloat);
            balanceLabel.setText(Float.toString(cafe.getMoney()));
        } catch(GUIAlertException e) {
            callAlert(e);
        } catch(Exception e) {
            System.out.println("Unhandled exception: " + e.getMessage());
        }
    }

    //Deposit money
    @FXML
    public void deposit() {
        try {
            String amountString = depositAmount.getText();
            float amountFloat;
            if (amountString.isEmpty()) {
                throw new TextFieldException("One or more of the input text fields is empty");
            }
            try {
                amountFloat = Float.parseFloat(depositAmount.getText());
            } catch(Exception e) {
                throw new TextFieldException("The input value is invalid");
            }
            cafe.changeBalance(amountFloat);
            balanceLabel.setText(Float.toString(cafe.getMoney()));
        } catch(GUIAlertException e) {
            callAlert(e);
        } catch(Exception e) {
            System.out.println("Unhandled exception: " + e.getMessage());
        }
    }

    //Add product to the menu
    public void addProduct() {
        try {
            String productName = inputProductName.getText();
            String buyPriceString = inputBuyPrice.getText();
            String sellPriceString = inputSellPrice.getText();
            if(productName.isEmpty() || buyPriceString.isEmpty() || sellPriceString.isEmpty()) {
                throw new TextFieldException("One or more of the inputs is empty");
            }

            float buyPrice;
            float sellPrice;
            try {
                buyPrice = Float.parseFloat(buyPriceString);
                sellPrice = Float.parseFloat(sellPriceString);
            } catch(Exception e) {
                throw new TextFieldException("Correct the value in the text field inputs");
            }

            if (setTypeDessert.isSelected()) {
                cafe.addProduct(new Dessert(productName, buyPrice, sellPrice));
                ((Label)menuGrid.getChildren().get(cafe.getMenu().size()-1)).setText(productName);
                ((Label)menuGrid.getChildren().get(cafe.getMenu().size()+9)).setText("0");
                buyListProducts.getItems().add(productName);
                removeProductsList.getItems().add(productName);
                sellListProducts.getItems().add(productName);
            } else {
                if (setTypeDrink.isSelected()) {

                    //Additional variable for type Drink
                    String weightPerPortionString = inputWeightPerPortion.getText();
                    if(weightPerPortionString.isEmpty()) {
                        throw new TextFieldException("One or more of the inputs is empty");
                    }
                    float weightPerPortion;
                    try {
                        weightPerPortion = Float.parseFloat(weightPerPortionString);
                    } catch(Exception e) {
                        throw new TextFieldException("Correct the value in the Weight/portion input");
                    }

                    cafe.addProduct(new Drink(productName, buyPrice, sellPrice, weightPerPortion));
                    ((Label)menuGrid.getChildren().get(cafe.getMenu().size()-1)).setText(productName);
                    ((Label)menuGrid.getChildren().get(cafe.getMenu().size()+9)).setText("0");
                    buyListProducts.getItems().add(productName);
                    sellListProducts.getItems().add(productName);
                    } else {
                    throw new RadioButtonException("Select the type of the product you want to add");
                }
            }
        } catch(GUIAlertException e) {
            callAlert(e);
        } catch(Exception e) {
            System.out.println("Unhandled exception: " + e.getMessage());
        }
    }

    //Setting the Weight/portion text fields visibility
    @FXML
    public void setWPPVisible() {
        inputWeightPerPortion.setVisible(true);
    }
    @FXML
    public void setWPPInvisible() {
        inputWeightPerPortion.setVisible(false);
    }

    @FXML
    private void buyProduct() {
        try {
            String amountString = amountBuyProduct.getText();
            String productName = (String)buyListProducts.getValue();
            if(amountString.isEmpty() || productName.isEmpty()) {
                throw new TextFieldException("Enter the amount of products you wish to buy");
            }
            float amount;
            try {
                amount = Float.parseFloat(amountString);
            } catch(Exception e) {
                throw new TextFieldException("Correct the value of the input text field");
            }
            cafe.buyProduct(productName, amount);
            ((Label)menuGrid.getChildren().get(10+cafe.getProductIndex(productName))).setText(String.valueOf(cafe.getMenu().get(cafe.getProductIndex(productName)).getStoredAmount()));
            balanceLabel.setText(String.valueOf(cafe.getMoney()));
        } catch(GUIAlertException e) {
            callAlert(e);
        } catch(Exception e) {
            System.out.println("Unhandled exception: " + e.getMessage());
        }

    }

    @FXML
    private void sellProduct() {
        try {
            String amountString = amountSellProduct.getText();
            String productName = (String)sellListProducts.getValue();
            if(amountString.isEmpty() || productName.isEmpty()) {
                throw new TextFieldException("Enter the amount of products you wish to buy");
            }
            float amount;
            try {
                amount = Float.parseFloat(amountString);
            } catch(Exception e) {
                throw new TextFieldException("Correct the value of the input text field");
            }
            cafe.sellProduct(productName, amount);
            ((Label)menuGrid.getChildren().get(10+cafe.getProductIndex(productName))).setText(String.valueOf(cafe.getMenu().get(cafe.getProductIndex(productName)).getStoredAmount()));
            balanceLabel.setText(String.valueOf(cafe.getMoney()));
        } catch(GUIAlertException e) {
            callAlert(e);
        } catch(Exception e) {
            System.out.println("Unhandled exception: " + e.getMessage());
        }
    }

    //Method called upon start
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initial balance label value
        balanceLabel.setText(String.valueOf(cafe.getMoney()));

        //Radio button group - only one can be selected at a time
        ToggleGroup radioButtonGroup = new ToggleGroup();
        setTypeDessert.setToggleGroup(radioButtonGroup);
        setTypeDrink.setToggleGroup(radioButtonGroup);

        //Initial ChoiceBox content
        for(int i=0; i < cafe.getMenu().size(); i++) {
            buyListProducts.getItems().add(cafe.getMenu().get(i).getName());
        }
    }
}
