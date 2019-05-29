/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.productReservas;

import app.model.Authentication;
import app.model.Reserves;
import app.model.Extras;
import app.model.Clients;
import app.model.Apartament;
import app.apartament.ApartamentController;

//Otros imports
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

//Otros imports
import java.net.URL;

import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;


import javafx.stage.Stage;




/**
 *
 * @author ricardLopez & josemanuMartin
 */
public class productReservasController implements Initializable {
     
    @FXML TextField numReserva;
    @FXML ChoiceBox<String> servicioExtra;
    @FXML TextField reservaBuscar;
    @FXML Label txtMsgError;
    @FXML Label txtMsgContratado;
    
    private Extras extrasobj;
    private Reserves reservesobj;     
    private Stage stage;
    private String usu = Authentication.getUsuari();
    
    ObservableList<String> nomproductes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        ocultarMensajes();
        reservesobj = new Reserves(); 
        extrasobj = new Extras();
        getServiciosExtras();
        servicioExtra.setItems(nomproductes);
        servicioExtra.setValue("Peluqueria");
        
        numReserva.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
                txtMsgContratado.setVisible(false);
            }
        });
        
        servicioExtra.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
                txtMsgContratado.setVisible(false);
            }
        });
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public void getServiciosExtras(){
        ResultSet result = (ResultSet) extrasobj.getNombreProductes();
        try {
            while(result.next()) {
                nomproductes.add(result.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    public String getidExtras(String nombre){
        ResultSet result = (ResultSet) extrasobj.search(nombre);
        String idstr="";
        try {
            while(result.next()) {
               idstr  = result.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return idstr;
    }
    
    public boolean getNuReserva(int nreserva){
        boolean rcreada = false;
        String nr="";
        System.out.println("Buscar Si existe la reserva");
        ResultSet result = (ResultSet) reservesobj.getCreadaReserva(nreserva);
        try {
            while(result.next()) {
                rcreada=true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return rcreada;
    }
    
    //getCreadaReserva()
    
    public boolean serviciosContratados(int nreserva, int producto){
        ResultSet result = (ResultSet) extrasobj.getSExtrasContratados(nreserva, producto);
        extrasobj.getSExtrasContratados(nreserva, producto);
        boolean servicio=false;
        System.out.println("Hola1");
        try {
            System.out.println("lineas servicios contratados: "+result.getFetchSize());
            while(result.next()) {               
                System.out.println("Producto Contratado: "+result.getString("fk_pk_reserva")+"---"+result.getString("fk_pk_productos"));
                servicio=true;
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return servicio;
    
    }
    
    public void btnService(){
        System.out.println("Hola Extra");
        int id = Integer.parseInt(getidExtras(servicioExtra.getValue()));
        int nreserva = Integer.parseInt(numReserva.getText());
        boolean existeReserva = getNuReserva(nreserva);
        boolean contratado = serviciosContratados(nreserva, id);
        System.out.println(contratado);
        if(!contratado){
            if(existeReserva){
                extrasobj.create(nreserva, id, usu);
                txtMsgContratado.setText("Contratado el servicio "+servicioExtra.getValue());
                txtMsgContratado.setVisible(true);
            }else{
                System.out.println("Aun no está creada la reserva "+nreserva+" y no puedes contratar el servicio");
                txtMsgError.setText("Aun no está creada la reserva "+nreserva+"\ny no puedes contratar el servicio");
                txtMsgError.setVisible(true);
            }
        }else{
            System.out.println("El servicio "+servicioExtra.getValue()+" Ya lo tienes contratado \ny no lo puedes volver a contratar");
            txtMsgError.setText("El servicio "+servicioExtra.getValue()+" Ya lo tienes contratado \ny no lo puedes volver a contratar");
            txtMsgError.setVisible(true); 
        }
    }
    
    /*
    public void btnService(){
        System.out.println("Hola Extra");
        int id = Integer.parseInt(getidExtras(servicioExtra.getValue()));
        int nreserva = Integer.parseInt(numReserva.getText());
        int bdreserva = Integer.parseInt(getNuReserva());
        System.out.println("numReserva: "+nreserva+" -- bdNumReserva: "+bdreserva);
        System.out.println("numReserva: "+numReserva.getText()+" Servicio: "+ Integer.toString(id));
        boolean contratado = serviciosContratados(nreserva, id);
        System.out.println(contratado);
        if(!contratado){
            if(bdreserva - nreserva >= 1){ 
                extrasobj.create(nreserva, id, usu);
                txtMsgError.setText("Contratado el servicio "+servicioExtra.getValue());
                txtMsgError.setVisible(true);
            }else{
                System.out.println("Aun no está creada la reserva "+nreserva+" y no puedes contratar el servicio");
                txtMsgError.setText("Aun no está creada la reserva "+nreserva+"\ny no puedes contratar el servicio");
                txtMsgError.setVisible(true);
            }
        }else{
            System.out.println("El servicio "+servicioExtra.getValue()+" Ya lo tienes contratado \ny no lo puedes volver a contratar");
            txtMsgError.setText("El servicio "+servicioExtra.getValue()+" Ya lo tienes contratado \ny no lo puedes volver a contratar");
            txtMsgError.setVisible(true); 
        }
        
        
    }
    
    */
    

    public void btnSearch() {
        
    }
    
    private void ocultarMensajes(){
       
       txtMsgError.setVisible(false);
       txtMsgContratado.setVisible(false);
       
    }
    
}


