/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reserves;

import app.model.Authentication;

import app.model.Reserves;

//Otros imports
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;




import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Date;


/**
 *
 * @author ricardLopez & josemanuMartin
 */
public class ReservesController implements Initializable {
    
   
    
    // all TextFields in page
    @FXML TextField idApartament;
    @FXML TextField horaEntrada;
    @FXML TextField horaSalida;
    @FXML TextField Import;
    @FXML TextField idClient;
    @FXML TextField reservaBuscar;
    
    // all Labels in page
   // @FXML Label errorTextCliente;
    //@FXML Label errorDateIniEmpty;
    
    // all DatePicker in page
    @FXML DatePicker fechaini;
    @FXML DatePicker fechafin;
    
    // all ChoiceBox in page
    @FXML private ChoiceBox cash;
    
    
  
   /**
     * Initializes the controller class.
     */
    
    private Reserves reservesobj;
    
    private String usu = Authentication.getUsuari();
    private String fin;
    private String fout;
    private String id;
    
    
    private Stage stage;
    @FXML
    private Button save;
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
       
        
        reservesobj = new Reserves();
        
        
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
   

    @FXML
    public void btnSave() {
      
       System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ "Pendiente");
      // System.out.println(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), "Pendiente");/*cash.getValue().toString()*/
       //System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ java.sql.Date.valueOf( fechaini.getValue())+" "+ horaEntrada.getText()+" "+ java.sql.Date.valueOf(  fechafin.getValue())+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ "Pendiente");
       
       //reservesobj.create(idClient.getText(), idApartament.getText(), usu, java.sql.Date.valueOf(fechaini.getValue()), horaEntrada.getText(), java.sql.Date.valueOf(fechafin.getValue()), horaSalida.getText(), Import.getText(), "Pendiente");
        reservesobj.create(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), "Pendiente"/*cash.getValue().toString()*/);
    }

    @FXML
    public void btnModify() {
        //Buscar id
        
        ResultSet result = (ResultSet) reservesobj.search(idClient.getText());
        try {
            while(result.next()) {
               id = result.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        
        //Modificar reserva
        //good reservesobj.modify(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), "Pendiente");
        reservesobj.modify(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), "Pendiente", id);
    }

    @FXML
    public void btnDelete() {
        reservesobj.delete(idClient.getText(), null);
    }

    @FXML
    public void btnSearch() {
        System.out.println("Buscar");
        ResultSet result = (ResultSet) reservesobj.search(reservaBuscar.getText());
        try {
            while(result.next()) {
                String id = result.getString("id");
                System.out.println(id +" "+"\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    
    
   
    
}


