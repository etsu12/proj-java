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
public class Towar {
    private IntegerProperty idProperty;
    private StringProperty nazwaProperty;
    private IntegerProperty iloscProperty;
    
    public Towar() {
        this.idProperty = new SimpleIntegerProperty();
        this.nazwaProperty = new SimpleStringProperty();
        this.iloscProperty = new SimpleIntegerProperty();
    }
    
    public int getTowId() {
        return idProperty.get();
    }
    
    public void setTowId(int id) {
        this.idProperty.set(id);
    }
    
    public IntegerProperty getTowarId() {
        return idProperty;
    }
    
    public int getTowIlosc() {
        return iloscProperty.get();
    }
    
    public void setTowIlosc(int ilosc) {
        this.iloscProperty.set(ilosc);
    }
    
    public IntegerProperty getTowarIlosc() {
        return iloscProperty;
    }
    
    public String getTowNazwa() {
        return nazwaProperty.get();
    }
    
    public void setTowNazwa(String nazwa) {
        this.nazwaProperty.set(nazwa);
    }
    
    public StringProperty getTowarNazwa() {
        return nazwaProperty;
    }
}
