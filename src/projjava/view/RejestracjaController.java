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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Zang
 */
public class RejestracjaController implements Initializable {

    @FXML
    private PasswordField haslo;
    @FXML
    private TextField login;
    @FXML
    private Button rejestracja;
    @FXML
    private TextField email;
    @FXML
    private TextField dane;
    @FXML
    private TextField miasto;
    @FXML
    private TextField adres;
    @FXML
    private Button wrocprzycisk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rejestruj(ActionEvent event) throws IOException {
        String loginT = login.getText();
        String hasloT = haslo.getText();
        String emailT = email.getText();
        String daneT = dane.getText();
        String miastoT = miasto.getText();
        String adresT = adres.getText();
        
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/loginmysql", "root","");
            pst = (PreparedStatement) con.prepareStatement("insert into klienci(login, haslo, email, dane, miasto, adres) values(?,?,?,?,?,?)");
            pst.setString(1, loginT);
            pst.setString(2, hasloT);
            pst.setString(3, emailT);
            pst.setString(4, daneT);
            pst.setString(5, miastoT);
            pst.setString(6, adresT);
            int status = pst.executeUpdate();
            
            if(status==1) {
                JOptionPane.showMessageDialog(null,"Pomyślnie utworzono konto");
                Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Test.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null,"Wystąpił błąd podczas tworzenia konta");
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RejestracjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RejestracjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void powrot(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Logowanie.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    
}
