/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.main;
import projjava.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projjava.view.LogowanieController;

/**
 *
 * @author Zang
 */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/TowarView.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/projjava/view/Test.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("/projjava/view/Rejestracja.fxml"));
        Parent root4 = FXMLLoader.load(getClass().getResource("/projjava/view/Logowanie.fxml"));
  
        Scene scene = new Scene(root);
        Scene scene2 = new Scene(root2);
        Scene rejestracja = new Scene(root3);
        Scene logowanie = new Scene(root4);
        
        
        
        primaryStage.setScene(logowanie);           
        primaryStage.show();
                    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
