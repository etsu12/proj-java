/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Zang
 */
public class Zamowienie {
    private IntegerProperty idProperty;
    private IntegerProperty idprProperty;
    private IntegerProperty iloscProperty;
    private StringProperty klientProperty;
    private StringProperty statusProperty;
    private StringProperty uwagaProperty;
    
    public Zamowienie() {
        this.idProperty = new SimpleIntegerProperty();
        this.idprProperty = new SimpleIntegerProperty();
        this.iloscProperty = new SimpleIntegerProperty();
        this.klientProperty = new SimpleStringProperty();
        this.statusProperty = new SimpleStringProperty();
        this.uwagaProperty = new SimpleStringProperty();
    }
    
    public int getZamId() {
        return idProperty.get();
    }
    
    public void setZamId(int id) {
        this.idProperty.set(id);
    }
    
    public IntegerProperty getZamowienieId() {
        return idProperty;
    }
    
    public int getZamIlosc() {
        return iloscProperty.get();
    }
    
    public void setZamIlosc(int ilosc) {
        this.iloscProperty.set(ilosc);
    }
    
    public IntegerProperty getZamowienieIlosc() {
        return iloscProperty;
    }
    
    public Integer getZamIdPr() {
        return idprProperty.get();
    }
    
    public void setZamIdPr(Integer nazwa) {
        this.idprProperty.set(nazwa);
    }
    
    public IntegerProperty getZamowienieIdPr() {
        return idprProperty;
    }
    
    public String getZamKlient() {
        return klientProperty.get();
    }
    
    public void setZamKlient(String nazwa) {
        this.klientProperty.set(nazwa);
    }
    
    public StringProperty getZamowienieKlient() {
        return klientProperty;
    }
    
    public String getZamStatus() {
        return statusProperty.get();
    }
    
    public void setZamStatus(String nazwa) {
        this.statusProperty.set(nazwa);
    }
    
    public StringProperty getZamowienieStatus() {
        return statusProperty;
    }
    
    public String getZamUwaga() {
        return uwagaProperty.get();
    }
    
    public void setZamUwaga(String nazwa) {
        this.uwagaProperty.set(nazwa);
    }
    
    public StringProperty getZamowienieUwaga() {
        return uwagaProperty;
    }
}
