/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clients;

import app.model.Authentication;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.stage.Stage;

import app.model.Clients;
import app.model.Personas;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ClientsController implements Initializable{
    
    // all TextFields in page
    @FXML TextField documnetation;
    @FXML TextField client;
    @FXML TextField tel;
    @FXML TextField email;
    @FXML TextField nac;
    @FXML TextField ocupation;
    @FXML TextField nameSearch;
    
    // all Labels in page
    @FXML Label errorTextGlobal;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    @FXML MenuButton status;
    
    @FXML TableView tabla;
    @FXML TableColumn cNombre;
    // Botons
    @FXML Button save;
    @FXML Button modify;
    @FXML Button delete;
    
    private Clients clientobj;
    
    private Stage stage;
    
    private ObservableList<Personas> personData;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //oculta mensaje de error
        cNombre.setCellValueFactory(new PropertyValueFactory<Personas,StringProperty>("nombre"));
        ocultarMensajes();
        clientobj = new Clients();
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
    }
    
    private void deshabilitarBtn(){
        delete.setDisable(true);
    }
    
    public void changeToDNI(){
        optionDoc.setText("DNI");
    }
    
    public void changeToPassport(){
        optionDoc.setText("PASSPORT");
    }
    
    public void changeToNIE(){
        optionDoc.setText("NIE");
    }
    
    public void changeToSoltero(){
        status.setText("SOLTERO");
    }
    
    public void changeToDivorciado(){
        status.setText("DIVORCIDO");
    }
    
    public void changeToCasado(){
        status.setText("CASADO");
    }
    
    public void btnSave(){
        clientobj.create(documnetation.getText(), client.getText(), tel.getText(), email.getText(), nac.getText(), ocupation.getText(), status.getText());
        /*ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            System.out.println("A la base de datos !!!");
        } else{
            System.out.println("Pon los bien puto");
        }*/
    }
    
    public void btnModify(){
        clientobj.modify(documnetation.getText(), client.getText(), tel.getText(), email.getText(), nac.getText(), ocupation.getText(), status.getText());
    }
    
    public void btnDelete(){
        clientobj.delete(documnetation.getText(), null);
    }
    
    public void btnSearch(){        
        ResultSet result = (ResultSet) clientobj.search(nameSearch.getText());
        try {
            personData = FXCollections.observableArrayList();
            while(result.next()) {
                Personas persona = new Personas(result);
                personData.add(persona);
            }
            System.out.println(personData);
            tabla.setItems(personData);
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    private void ocultarMensajes(){
       errorTextGlobal.setVisible(false);
    }
    
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static final Pattern VALID_DNI_REGEX = Pattern.compile("/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", Pattern.CASE_INSENSITIVE);

    
    private boolean validate(int opcion, String cadena) {
        Matcher matcher=VALID_EMAIL_ADDRESS_REGEX .matcher(cadena);;    
        if(opcion==1){
            if(optionDoc.getText().equals("DNI")){
                System.out.println("DNI");
                matcher = VALID_DNI_REGEX .matcher(cadena);
            }
            
        }
        return matcher.find();
    }
    public void setStage (Stage stage){
        this.stage = stage;
    }
}
