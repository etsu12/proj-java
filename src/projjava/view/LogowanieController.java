/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.view;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import projjava.main.MainApp;

/**
 * FXML Controller class
 *
 * @author Zang
 */
public class LogowanieController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField haslo;
    @FXML
    private Button zalogujButton;
    @FXML
    private Hyperlink goscButton;
    @FXML
    private Hyperlink rejestracjaButton;
    @FXML
    private Hyperlink adminButton;
    
    private MainApp mainApp;
    public Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zaloguj(ActionEvent event) throws IOException {
        String loginT = login.getText();
        String hasloT = haslo.getText();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        if(login.equals("") && haslo.equals("")) {
            JOptionPane.showMessageDialog(null,"Nie wprowadzono danych");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginmysql", "root","");
                pst = (PreparedStatement) con.prepareStatement("select * from uzytkownik where login=? and haslo=?");
                
                pst.setString(1, loginT);
                pst.setString(2, hasloT);
                
                rs = pst.executeQuery();
                        
                if(rs.next()) {
                    /*Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Klient.fxml"));
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();*/
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/projjava/view/Klient.fxml"));
                    Parent root = (Parent) loader.load();
                    
                    KlientController klientKont = loader.getController();
                    klientKont.myFuction(loginT);
                    
                    
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                } else {
                    JOptionPane.showMessageDialog(null,"Wprowadzono błędne dane");
                    login.setText("");
                    haslo.setText("");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    @FXML
    private void logujGosc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Gosc.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rejestracja(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Rejestracja.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void adminLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Test.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
    
    
}
