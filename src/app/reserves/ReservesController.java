/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reserves;

import app.model.Authentication;
import app.model.Reserves;
import app.model.Extras;
import app.model.Clients;
import app.model.Apartament;
import app.model.Personas;
import app.model.Reservas;

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
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML Label errorClienteNoEncontrado;
    @FXML Label txtMsgError;
    @FXML Label fechasNoDisponibles;
    @FXML Label txtReservaCreada;
    
    // all DatePicker in page
    @FXML DatePicker fechaini;
    @FXML DatePicker fechafin;
    
    // all ChoiceBox in page
    //@FXML private ChoiceBox cash;
    @FXML ChoiceBox<String> cash;
   // @FXML ChoiceBox<String> servicioExtra;
    
    // table in page
    @FXML TableView tabla;
    @FXML TableColumn cCliente;
    @FXML TableColumn cImporte;
    @FXML TableColumn cAbono;
    
    //buttons
    @FXML private Button save;
    
    private Reserves reservesobj;
   // private Extras extrasobj;
    private Clients clienteobj;
    private Apartament apartamentObj;
  
    private String usu = Authentication.getUsuari();

    private float Preuapartamento = 0;
    //final float Preuapartamento = ApartamentController.getPrecio("1");

    private String id;
    private ObservableList<Reservas> reservasData;
    ObservableList<String> availableChoices = FXCollections.observableArrayList("Pendiente", "Pagada", "Anulada");
   // ObservableList<String> nomproductes = FXCollections.observableArrayList();
        
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cCliente.setCellValueFactory(new PropertyValueFactory<Reservas,StringProperty>("cliente"));
        cImporte.setCellValueFactory(new PropertyValueFactory<Reservas,StringProperty>("importe"));
        cAbono.setCellValueFactory(new PropertyValueFactory<Reservas,StringProperty>("abono"));
        
        tabla.setRowFactory(tv -> {
           TableRow<Reservas> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
               if(event.getClickCount() == 2 && (! row.isEmpty())){
                   Reservas rowData = row.getItem();
                   horaEntrada.setText(rowData.getHoraIn());
                   horaSalida.setText(rowData.getHorafi());
                   Import.setText(rowData.getImporte());
                   idClient.setText(rowData.getCliente());
                   
               }
           });
           return row;
        });
        
        ocultarMensajes();
        reservesobj = new Reserves(); 
        //extrasobj = new Extras();
        apartamentObj = new Apartament();
        cash.setItems(availableChoices);
        cash.setValue("Pendiente");  
        
        
        //System.out.println("Hello "+getNumReserva());
        numReserva.setText(getNumReserva());
       /*
        getServiciosExtras();
        servicioExtra.setItems(nomproductes);
        servicioExtra.setValue("Peluqueria");
        */
        idClient.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                errorClienteNoEncontrado.setVisible(false);       
            }
        });
        fechaini.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
                fechasNoDisponibles.setVisible(false);
            }
        });
        fechafin.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
                fechasNoDisponibles.setVisible(false);
            }
        });

    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public float getPrecioApartamento(){
        //ApartamentController.getPrecio("1");
        System.out.println("Buscar id reserva");
        Preuapartamento = apartamentObj.getPrecio("1");
        return Preuapartamento;
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
    /*
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
    
    */
    /*
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
    }*/
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
    /*
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
    
    }*/
    /*
    public void btnService(){
        System.out.println("Hola Extra");
        int id = Integer.parseInt(getidExtras(servicioExtra.getValue()));
        int nreserva = Integer.parseInt(numReserva.getText());
        int bdreserva = Integer.parseInt(getNumReserva());
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
        
        
    }    */
    
    public void btnSave() throws ParseException{
        int nreserva = Integer.parseInt(numReserva.getText());
        int bdreserva = Integer.parseInt(getNumReserva());
        System.out.println("numReserva: "+nreserva+" -- bdNumReserva: "+bdreserva);
        if(bdreserva - nreserva == 0){ 
            if(!erroresEntradas()){
               System.out.println(hora());
                    String h = hora();
                    horaEntrada.setText(h);
                    horaSalida.setText("Pendiente");
                    float importEstada = Importe(fechaini.getValue(), fechafin.getValue());
                    Import.setText(Float.toString(importEstada));

                    System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ cash.getValue());

                   //System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ "Pendiente");
                    reservesobj.create(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), cash.getValue()); 
                    txtReservaCreada.setText("Contratada la reserva "+numReserva.getText());
                    txtReservaCreada.setVisible(true);
            }
        }else{
            System.out.println("La reserva "+nreserva+" ya está creada");
            txtMsgError.setText("La reserva "+nreserva+" ya está creada");
            txtMsgError.setVisible(true); 
        }
                
         
    }
    
    
    

    public void btnModify() throws ParseException{
        int nreserva = Integer.parseInt(numReserva.getText());
        int bdreserva = Integer.parseInt(getNumReserva());
        System.out.println("numReserva: "+nreserva+" -- bdNumReserva: "+bdreserva);
        if(bdreserva - nreserva == 1){ 
            if(!erroresEntradas()){
                float importEstada = Importe(fechaini.getValue(), fechafin.getValue());
                Import.setText(Float.toString(importEstada));

                //ResultSet result = (ResultSet) reservesobj.search(idClient.getText());
                ResultSet result = (ResultSet) reservesobj.search(numReserva.getText());
                //numReserva
                try {
                    while(result.next()) {
                       id = result.getString("id");
                    }
                } catch (SQLException e) {
                    System.out.println("SQLException"+ e.getMessage());
                    System.out.println("SQLState"+ e.getSQLState());
                    System.out.println("VendorError"+ e.getErrorCode());
                }
                System.out.println("Modificada: ");
                System.out.println(idClient.getText()+" "+ idApartament.getText()+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ cash.getValue()+ " "+id);
                reservesobj.modify(idClient.getText(), idApartament.getText(), usu, fechaini.getValue(), horaEntrada.getText(), fechafin.getValue(), horaSalida.getText(), Import.getText(), cash.getValue(), id);
            }
        }else{
            System.out.println("Aun no está creada la reserva "+nreserva+" Creala primero y después la podrás modificar");
            txtMsgError.setText("Aun no está creada la reserva "+nreserva+"\nCreala primero y después la podrás modificar");
            txtMsgError.setVisible(true); 
        }
        
    }

    public void btnDelete() {
        reservesobj.delete(idClient.getText(), null);
    }

    public void btnSearch() {        
        ResultSet result = (ResultSet) reservesobj.search(reservaBuscar.getText());
        try {
            reservasData = FXCollections.observableArrayList();
            while(result.next()) {
                Reservas reserva = new Reservas(result);
                reservasData.add(reserva);
            }
            tabla.setItems(reservasData);
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    private boolean erroresEntradas() throws ParseException{
        boolean error = false;
        boolean hayreserva=verificarReserva();
        int diasReserva=0;
        
        if(idClient.getText().equalsIgnoreCase("") && fechaini.getValue() == null && fechafin.getValue() == null){
            txtMsgError.setText("Tienes que rellenar los \ntres campos obligatorios");
            txtMsgError.setVisible(true);
            error = true;
        }
        else{
            if(idClient.getText().equalsIgnoreCase("")){
                System.out.println("Cliente "+idClient.getText()+" No encontrado");
                errorClienteNoEncontrado.setText("No has introducido ningun cliente");                
                errorClienteNoEncontrado.setVisible(true);
                error = true;
            }else{
                boolean clienteEncontrado=encontrarCliente();
                if(!clienteEncontrado){
                    errorClienteNoEncontrado.setText("El Cliente no está registrado, registralo");
                    errorClienteNoEncontrado.setVisible(true);
                    error = true;
                }            
            }
            
            if(fechaini.getValue() == null && fechafin.getValue() == null){
                System.out.println("Las Fechas Tienen que tener valor");
                txtMsgError.setText("Las Fechas Tienen que tener valor");
                txtMsgError.setVisible(true);
                error = true;
            }else if(fechaini.getValue() == null){
                System.out.println("La Fecha de Entrada tiene que tener valor"); 
                txtMsgError.setText("La Fecha de Entrada tiene que tener valor");
                txtMsgError.setVisible(true);
                error = true;
            }else if(fechaini.getValue().isBefore(LocalDate.now())){
                System.out.println("La Fecha de Entrada no puede ser \nanterior a la actual");
                txtMsgError.setText("La Fecha de Entrada no puede ser \nanterior a la actual");
                txtMsgError.setVisible(true);
                error = true;
            }            
            else if(fechafin.getValue() == null){
                System.out.println("La Fecha de Salida tiene que tener valor");
                txtMsgError.setText("La Fecha de Salida tiene que tener valor");
                txtMsgError.setVisible(true);
                error = true;
            }else if(fechaini.getValue() != null && fechafin.getValue() != null){
                diasReserva = diasEstancia(fechaini.getValue(), fechafin.getValue());
                System.out.println(fechafin.getValue().isBefore(fechaini.getValue()));
                System.out.println(fechaini.getValue().isBefore(LocalDate.now()));
                System.out.println(diasReserva);
                
                if(diasReserva < 1 || fechafin.getValue().isBefore(fechaini.getValue()) ){
                    System.out.println("Introduce una fecha de salida posterior a la fecha de entrada");
                    txtMsgError.setText("Introduce una fecha de salida \nposterior a la fecha de entrada");
                    txtMsgError.setVisible(true);
                    error = true;
                }
                
            }
            
            if(verificarReserva()){
                System.out.println("Verificando si se puede reservar");
                fechasNoDisponibles.setVisible(true);
                error = true;
            }
        
        }
        return error;
    }
    
     private boolean encontrarCliente(){
        boolean clienteEncontrado = false;
        System.out.println("-"+idClient.getText().equalsIgnoreCase("")+"-");
        System.out.println("Buscar Cliente");
        if(idClient.getText().equalsIgnoreCase("")){
            System.out.println("NO has introducido Ningun valor");
        }else{
            System.out.println("Has introducido valor");
            String id="";
            ResultSet result = (ResultSet) reservesobj.searchCliente(idClient.getText());
            try {
                while(result.next()) {
                    id = result.getString("documento");                
                    clienteEncontrado=true;
                }
            } catch (SQLException e) {
                System.out.println("SQLException"+ e.getMessage());
                System.out.println("SQLState"+ e.getSQLState());
                System.out.println("VendorError"+ e.getErrorCode());
            }
        }
        return clienteEncontrado;
        
    }
    
    
    private boolean verificarReserva(){
        boolean reservaEncontrada = false;
        int cont=0;
        System.out.println("Verificando reserva");
        ResultSet result = (ResultSet) reservesobj.getExistsReserva(fechaini.getValue(), fechafin.getValue());
        try {            
            while(result.next()) {
                reservaEncontrada=true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return reservaEncontrada;
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
    
    private float Importe(LocalDate fechaIni, LocalDate fechaFin) throws ParseException{
        
        //Period period = Period.between(fechaIni, fechaFin);        
       // return Preuapartamento * (float) period.getDays();
        return getPrecioApartamento() * (float) diasEstancia(fechaIni, fechaFin);
    }
    
    private int diasEstancia(LocalDate fechaIni, LocalDate fechaFin) throws ParseException{
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
        String fini = fechaIni.format(formatter);
        String ffin = fechaFin.format(formatter);
        
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
 
        Date fechaInicial=dateFormat1.parse(fini);
        Date fechaFinal=dateFormat2.parse(ffin);
        int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);

       /**He observado que la clase Date da un día de Menos
        * al realizar la comparación de fechas 
        * cuando el Mes de la fecha inicial es Marzo
        * y el mes de la fecha inicial es abril,
        * Lo corrijo con este if
       */
       if(fechaIni.getMonthValue()==3 && fechaFin.getMonthValue()==4){
           dias++;
       }
 
	
       /** Period period = Period.between(fechaIni, fechaFin);
        * return period.getDays();
        */
        
        return dias;    
    }
    
    private void ocultarMensajes(){
       errorClienteNoEncontrado.setVisible(false);
       txtMsgError.setVisible(false);
       fechasNoDisponibles.setVisible(false);
       txtReservaCreada.setVisible(false);
    }
    
    
}


