/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reserves;

import app.model.Authentication;
import app.model.Reserves;
import app.model.Extras;

//Otros imports
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


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
    @FXML TextField numReserva;
    // all Labels in page
   // @FXML Label errorTextCliente;
    //@FXML Label errorDateIniEmpty;
    
    // all DatePicker in page
    @FXML DatePicker fechaini;
    @FXML DatePicker fechafin;
    
    // all ChoiceBox in page
    //@FXML private ChoiceBox cash;
    @FXML ChoiceBox<String> cash;
    @FXML ChoiceBox<String> servicioExtra;
    
    // table in page
    @FXML GridPane gridpane;
    int fila=10;
    int col=3;
    
    //buttons
    @FXML private Button save;
    
    private Reserves reservesobj;
    private Extras extrasobj;
  
    private String usu = Authentication.getUsuari();

    private final float Preuapartamento = (float) 100.00;

    private String id;
    ObservableList<String> availableChoices = FXCollections.observableArrayList("Pendiente", "Pagada", "Anulada");
    ObservableList<String> nomproductes = FXCollections.observableArrayList();
        
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
        reservesobj = new Reserves(); 
        extrasobj = new Extras();
        cash.setItems(availableChoices);
        cash.setValue("Pendiente");  
        cabeceras();
        
        
        //System.out.println("Hello "+getNumReserva());
        numReserva.setText(getNumReserva());
       
        getServiciosExtras();
        servicioExtra.setItems(nomproductes);

    }
    
    private void cabeceras(){
        for(int x=0;x<col; x++){
            if(x==0){
                gridpane.add(new Label("CLIENTE"), x,0);
            }else if(x==1){
                gridpane.add(new Label("IMPORTE"), x,0);
            }else{
                gridpane.add(new Label("ABONO"), x,0);
            }
        }
    }
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public String getNumReserva(){
        int nreserva = 0 ;
        String nr="";
        System.out.println("Buscar id reserva");
        ResultSet result = (ResultSet) reservesobj.getNumeroReserva();
        try {
            while(result.next()) {
                String id = result.getString("id");
                nreserva = Integer.parseInt(id);
                nreserva++;
                System.out.println(nreserva);
                nr= Integer.toString(nreserva);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return nr;
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
    /*
    @FXML
    public int idServicio(String nombre){
        int idservicio = 0 ;
        String ns="";
        System.out.println("Buscar id Servicio");
        ResultSet result = (ResultSet) reservesobj.search(nombre);
        try {
            while(result.next()) {
                String id = result.getString("id");
                idservicio = Integer.parseInt(id);
                
                System.out.println(idservicio);
                //ns= Integer.toString(nservicio);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            
            return idservicio;
        }
    }*/
    
    public void btnService(){
        System.out.println("Hola radiola");
        int nreserva = Integer.parseInt(numReserva.getText());
        int id = Integer.parseInt(getidExtras(servicioExtra.getValue()));
        
        System.out.println("Contratar Servicio Extra "+nreserva+"---"+id+ "---"+ usu);
        extrasobj.create(nreserva, id, usu);
    }
   
    public void btnSave() {
        System.out.println(hora());
        String h = hora();
        horaEntrada.setText(h);
        horaSalida.setText("Pendiente");
        float importEstada = Importe(fechaini.getValue(), fechafin.getValue());
        Import.setText(Float.toString(importEstada));
        
        System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ cash.getValue());
      
       //System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ "Pendiente");
        reservesobj.create(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), cash.getValue());
    }

    public void btnModify() {
        float importEstada = Importe(fechaini.getValue(), fechafin.getValue());
        Import.setText(Float.toString(importEstada));
        
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

    public void btnDelete() {
        reservesobj.delete(idClient.getText(), null);
    }

    public void btnSearch() {
        int i=1;
        gridpane.getChildren().clear();
        cabeceras();
        ResultSet result = (ResultSet) reservesobj.search(reservaBuscar.getText());
        try {
            while(result.next()) {
                gridpane.add(new Label(result.getString("fk_cliente")),0,i);
                gridpane.add(new Label(result.getString("importe")),1,i);
                gridpane.add(new Label(result.getString("abono")),2,i);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    private String hora(){
        
        LocalTime l = LocalTime.now();
        int iHora, iMinuto, iSegundo;
        String sHora, sMinuto, sSegundo;
        iHora=l.getHour();
        iMinuto=l.getMinute();
        iSegundo=l.getSecond();
        if(iHora<10){
            sHora=Integer.toString(iHora);
            sHora="0"+sHora;
            System.out.println(sHora);
        }else{
           sHora=Integer.toString(iHora); 
        }
        
        if(iMinuto<10){
            sMinuto="0"+Integer.toString(iMinuto);
        }else{
           sMinuto=Integer.toString(iMinuto); 
        }
        
        if(iSegundo<10){
            sSegundo="0"+Integer.toString(iSegundo);
        }else{
           sSegundo=Integer.toString(iSegundo); 
        }
        
        String lahora=sHora+":"+sMinuto+":"+sSegundo;
        
        return lahora;
    }
    
    private float Importe(LocalDate fechaIni, LocalDate fechaFin){
        
        Period period = Period.between(fechaIni, fechaFin);        
        return Preuapartamento * (float) period.getDays();
    }
}


