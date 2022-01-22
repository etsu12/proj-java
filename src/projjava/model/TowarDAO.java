/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.model;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import projjava.util.DBUtil;
import java.sql.ResultSet;
import javafx.collections.FXCollections;

/**
 *
 * @author Zang
 */
public class TowarDAO {
    public static void wprowadzTowar(String nazwa, int ilosc) throws SQLException, ClassNotFoundException {
        String sql = "insert into produkty(nazwa, ilosc) values('"+nazwa+"', '"+ilosc+"')";
        try {
            DBUtil.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Exception przy wprowadzaniu danych"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void wprowadzZamowienie(int id_prod, int ilosc, String klient, String status, String uwaga) throws SQLException, ClassNotFoundException {
        String sql = "insert into zamowienia(id_prod, ilosc, klient, status, uwaga) values('"+id_prod+"', '"+ilosc+"', '"+klient+"', '"+status+"', '"+uwaga+"')";
        try {
            DBUtil.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Exception przy wprowadzaniu danych"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void aktualizujTowar(int id, int ilosc) throws SQLException, ClassNotFoundException {
        String sql = "update produkty set ilosc= '"+ilosc+"' where id= '"+id+"'";
        
        try {
            DBUtil.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji!");
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void aktualizujZamowienie(int id, String status, String uwaga) throws SQLException, ClassNotFoundException {
        String sql = "update zamowienia set status= '"+status+"', uwaga= '"+uwaga+"' where id_zamowienia= '"+id+"'";
        
        try {
            DBUtil.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Błąd przy aktualizacji!");
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void usunTowar(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from produkty where id = '"+id+"'";
        
        try {
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Błąd przy usuwaniu rekordu!"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ObservableList<Towar> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from produkty";
        
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Towar> towList = getTowarObjects(rsSet);
            return towList;
        }
        catch(SQLException e) {
            System.out.println("Błąd przy przechwytywaniu danych z bazy"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ObservableList<Zamowienie> getAllZamowienieRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from zamowienia";
        
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Zamowienie> zamList = getZamowienieObjects(rsSet);
            return zamList;
        }
        catch(SQLException e) {
            System.out.println("Błąd przy przechwytywaniu danych z bazy"+e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Towar> getTowarObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
             ObservableList<Towar> towlist = FXCollections.observableArrayList();
        
        while(rsSet.next()) {
            Towar tow = new Towar();
            tow.setTowId(rsSet.getInt("id"));
            tow.setTowNazwa(rsSet.getString("nazwa"));
            tow.setTowIlosc(rsSet.getInt("ilosc"));
            towlist.add(tow);
        }
        return towlist;
        } catch (SQLException e) {
            System.out.println("Błąd przy przechwytywaniu danych z bazy"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    private static ObservableList<Zamowienie> getZamowienieObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
             ObservableList<Zamowienie> zamlist = FXCollections.observableArrayList();
        
        while(rsSet.next()) {
            Zamowienie zam = new Zamowienie();
            zam.setZamId(rsSet.getInt("id_zamowienia"));
            zam.setZamIdPr(rsSet.getInt("id_prod"));
            zam.setZamIlosc(rsSet.getInt("ilosc"));
            zam.setZamKlient(rsSet.getString("klient"));
            zam.setZamStatus(rsSet.getString("status"));
            zam.setZamUwaga(rsSet.getString("uwaga"));
            zamlist.add(zam);
        }
        return zamlist;
        } catch (SQLException e) {
            System.out.println("Błąd przy przechwytywaniu danych z bazy"+e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ObservableList<Towar> szukajTowar(int towId) throws ClassNotFoundException, SQLException {
        String sql = "select * from produkty where id = "+towId;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Towar> list = getTowarObjects(rsSet);
            return list;
        } catch(SQLException e) {
            System.out.println("Błąd przy wyszukiwaniu rekordu"+e);
            e.printStackTrace();
            throw e;
        }
    }
}
