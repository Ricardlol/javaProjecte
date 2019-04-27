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

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
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
    @FXML TextField status;
    @FXML TextField nameSearch;
    
    // all Labels in page
    @FXML Label errorTextGlobal;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    
   // table in page
    @FXML GridPane gridpane;
    int fila=10;
    int col=4;
    
    // Botons
    @FXML Button save;
    @FXML Button modify;
    @FXML Button delete;
    
    private Clients clientobj;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //oculta mensaje de error
        ocultarMensajes();
        clientobj = new Clients();
        cabeceras();
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
    }
    
    private void deshabilitarBtn(){
        save.setDisable(true);
        modify.setDisable(true);
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
    
    private void cabeceras(){
        for(int x=0;x<col; x++){
            if(x==0){
                gridpane.add(new Label("DOCUMENTO"), x,0);
            }else if(x==1){
                gridpane.add(new Label("NOMBRE"), x,0);
            }else if(x==2){
                gridpane.add(new Label("TEL"), x,0);
            }else{
                gridpane.add(new Label("EMAIL"), x,0);
            }
        }
    }
    
    public void btnSearch(){
        int i=1;
        gridpane.getChildren().clear();
        cabeceras();
        ResultSet result = (ResultSet) clientobj.search(nameSearch.getText());
        try {
            while(result.next()) {
                gridpane.add(new Label(result.getString("documento")),0,i);
                gridpane.add(new Label(result.getString("nombre")),1,i);
                gridpane.add(new Label(result.getString("telefono")),2,i);
                gridpane.add(new Label(result.getString("email")),3,i);
                i++;
            }
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
