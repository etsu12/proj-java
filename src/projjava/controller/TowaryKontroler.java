/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.controller;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import projjava.model.Towar;
import projjava.model.TowarDAO;

/**
 *
 * @author Zang
 */
public class TowaryKontroler {
    @FXML
    private TextField txtNazwa;
    @FXML
    private TextField txtIlosc;
    @FXML
    private TextArea konsola;
    @FXML
    private TextField szukajId;
    @FXML
    private TextField szukajIlosc;
    @FXML
    private Button wrocButton;
    @FXML
    private TableView<Towar> tabela;
    @FXML
    private TableColumn<Towar, Integer> colId;
    @FXML
    private TableColumn<Towar, String> colNazwa;
    @FXML
    private TableColumn<Towar, Integer> colIlosc;
    
    @FXML
    private void wprowadzTowar(ActionEvent event) throws ClassNotFoundException, SQLException {
      try {
        TowarDAO.wprowadzTowar(txtNazwa.getText(), Integer.parseInt(txtIlosc.getText()));
        konsola.setText("Wartości zostały wprowadzone do bazy!");
        ObservableList<Towar> towList = TowarDAO.getAllRecords();
        populateTable(towList);
       } catch (SQLException e) {
            System.out.println("Błąd przy wprowadzaniu danych"+e);
            e.printStackTrace();
            throw e;
       }
}
    @FXML
    private void aktualizujTowar(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            TowarDAO.aktualizujTowar(Integer.parseInt(szukajId.getText()), Integer.parseInt(szukajIlosc.getText()));
            konsola.setText("Wartości zostały zaktualizowane!");
            ObservableList<Towar> towList = TowarDAO.getAllRecords();
            populateTable(towList);
        }
        catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji danych!"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    @FXML
    private void usunTowar(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            TowarDAO.usunTowar(Integer.parseInt(szukajId.getText()));
            konsola.setText("Rekord został usunięty pomyślnie!");
            ObservableList<Towar> towList = TowarDAO.getAllRecords();
            populateTable(towList);
        } catch (SQLException e) {
            System.out.println("Błąd przy usuwaniu danych"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    @FXML
    private void initialize() throws Exception{
        /*colTowId.setCellValueFactory(cellData -> cellData.getValue().getTowarId().asObject());
        colTowNazwa.setCellValueFactory(cellData -> cellData.getValue().getTowarNazwa());
        colTowIlosc.setCellValueFactory(cellData -> cellData.getValue().getTowarIlosc().asObject());
        ObservableList<Towar> towList = TowarDAO.getAllRecords();
        populateTable(towList);*/
        
        
        colId.setCellValueFactory(cellData -> cellData.getValue().getTowarId().asObject());
        colNazwa.setCellValueFactory(cellData -> cellData.getValue().getTowarNazwa());
        colIlosc.setCellValueFactory(cellData -> cellData.getValue().getTowarIlosc().asObject());
        ObservableList<Towar> towList;
        try {
            towList = TowarDAO.getAllRecords();
            populateTable(towList);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TowaryKontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TowaryKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateTable(ObservableList<Towar> towList) {
        tabela.setItems(towList);
    }
    
    @FXML
    private void szukajTowar(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Towar> list = TowarDAO.szukajTowar(Integer.parseInt(szukajId.getText()));
        if(list.size() > 0) {
            populateTable(list);
            konsola.setText("Rekord został znaleziony");
        } else {
            konsola.setText("Nie znaleziono rekordu");
        }
    }
    
    @FXML
    private void szukajWszystkieTowary(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Towar> towList = TowarDAO.getAllRecords();
        populateTable(towList);
    }
    
    @FXML
    private void wroc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Logowanie.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void przejdzZamowienia(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projjava/view/Pracownik.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
