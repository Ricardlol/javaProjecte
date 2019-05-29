/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author daw2
 */
public class Personas {
    StringProperty doc;
    StringProperty nom;
    StringProperty tel;
    StringProperty nac;
    StringProperty estado;
    StringProperty ocupation;
    StringProperty email;
    public Personas(ResultSet result) throws SQLException {
        doc= new SimpleStringProperty(result.getString("documento"));
        nom=new SimpleStringProperty(result.getString("nombre"));
        tel=new SimpleStringProperty(result.getString("telefono"));
        nac=new SimpleStringProperty(result.getString("nacionalidad"));
        email=new SimpleStringProperty(result.getString("email"));
        estado=new SimpleStringProperty(result.getString("estadoCivil"));
        ocupation=new SimpleStringProperty(result.getString("ocupacion"));
    }
    
    public String getDoc(){
        return doc.get();
    }
    public String getNom(){
        return nom.get();
    }
    public String getTel(){
        return tel.get();
    }
    public String getNac(){
        return nac.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getEstado(){
        return estado.get();
    }
    public String getOcupacion(){
        return ocupation.get();
    }
    
}
