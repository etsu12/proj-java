/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import projjava.model.Towar;
import projjava.model.TowarDAO;

/**
 * FXML Controller class
 *
 * @author Zang
 */
public class GoscController implements Initializable {

    @FXML
    private TableView tabela;
    @FXML
    private TableColumn<Towar, Integer> id;
    @FXML
    private TableColumn<Towar, String> nazwa;
    @FXML
    private TableColumn<Towar, Integer> ilosc;

    /**
     * Initializes the controller class.
     */ 
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(cellData -> cellData.getValue().getTowarId().asObject());
        nazwa.setCellValueFactory(cellData -> cellData.getValue().getTowarNazwa());
        ilosc.setCellValueFactory(cellData -> cellData.getValue().getTowarIlosc().asObject());
        ObservableList<Towar> towList;
        try {
            towList = TowarDAO.getAllRecords();
            populateTable(towList);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GoscController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GoscController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void populateTable(ObservableList<Towar> towList) {
        tabela.setItems(towList);
    }

    @FXML
    private void wroc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Logowanie.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
