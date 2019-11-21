package GUI;

import backend.Cafe;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import GUI.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kavarna U Tri retardovanych kohoutu");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(650);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
