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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projjava.model.Zamowienie;
import projjava.model.TowarDAO;

/**
 * FXML Controller class
 *
 * @author Zang
 */
public class PracownikController implements Initializable {

    @FXML
    private TableView<Zamowienie> tabela;
    @FXML
    private TableColumn<Zamowienie, Integer> colZamId;
    @FXML
    private TableColumn<Zamowienie, Integer> colIdPr;
    @FXML
    private TableColumn<Zamowienie, Integer> colIlosc;
    @FXML
    private TableColumn<Zamowienie, String> colKlient;
    @FXML
    private TableColumn<Zamowienie, String> colStatus;
    @FXML
    private TableColumn<Zamowienie, String> colUwaga;
    @FXML
    private TextArea uwaga;
    @FXML
    private TextField idZamowienia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colZamId.setCellValueFactory(cellData -> cellData.getValue().getZamowienieId().asObject());
        colIdPr.setCellValueFactory(cellData -> cellData.getValue().getZamowienieIdPr().asObject());
        colIlosc.setCellValueFactory(cellData -> cellData.getValue().getZamowienieIlosc().asObject());
        colKlient.setCellValueFactory(cellData -> cellData.getValue().getZamowienieKlient());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().getZamowienieStatus());
        colUwaga.setCellValueFactory(cellData -> cellData.getValue().getZamowienieUwaga());
        ObservableList<Zamowienie> zamList;
        try {
            zamList = TowarDAO.getAllZamowienieRecords();
            populateTable(zamList);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private void populateTable(ObservableList<Zamowienie> zamList) {
        tabela.setItems(zamList);
    }

    @FXML
    private void wroc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/TowarView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void wRealizacji(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            TowarDAO.aktualizujZamowienie(Integer.parseInt(idZamowienia.getText()), "W realizacji" ,uwaga.getText());
            ObservableList<Zamowienie> zamList = TowarDAO.getAllZamowienieRecords();
            populateTable(zamList);
        }
        catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji danych!"+e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void wWyslane(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            TowarDAO.aktualizujZamowienie(Integer.parseInt(idZamowienia.getText()), "Wysłane" ,uwaga.getText());
            ObservableList<Zamowienie> zamList = TowarDAO.getAllZamowienieRecords();
            populateTable(zamList);
        }
        catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji danych!"+e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void wAnulowano(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            TowarDAO.aktualizujZamowienie(Integer.parseInt(idZamowienia.getText()), "Anulowano" ,uwaga.getText());
            ObservableList<Zamowienie> zamList = TowarDAO.getAllZamowienieRecords();
            populateTable(zamList);
        }
        catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji danych!"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
}
