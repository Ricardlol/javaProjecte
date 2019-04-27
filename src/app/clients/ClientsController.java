/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clients;

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

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ListView;
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
    @FXML Label errorTextDocumentation;
    @FXML Label errorTextName;
    @FXML Label errorTextTel;
    @FXML Label errorTextEmail;
    @FXML Label errorTextNac;
    @FXML Label errorTextOc;
    @FXML Label errorTextCiv;
    @FXML Label errorTextIncorrectEmail;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    
   // table in page
    @FXML GridPane gridpane;
    int fila=10;
    int col=4;
    
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
    
    private boolean comprovarCampos(){
        boolean result= true;
        result=comprovarDocumentacion();
        if (client.getText().length()==0){
            errorTextName.setVisible(true);
            result= false;
        }
        if (tel.getText().length()==0){
            errorTextTel.setVisible(true);
            result= false;
        }
        if (email.getText().length()==0){
            errorTextEmail.setVisible(true);
            result= false;
        }
        if(email.getText().length()>0){
            boolean mensaje=validate(4,email.getText());
            if(!mensaje){
                errorTextIncorrectEmail.setVisible(true);
                result= false;
            }
        }
        if (nac.getText().length()==0){
            errorTextNac.setVisible(true);
            result= false;
        }
        if (ocupation.getText().length()==0){
            errorTextOc.setVisible(true);
            result= false;
        }
        if (status.getText().length()==0){
            errorTextCiv.setVisible(true);
            result= false;
        }
        return result;
    }
    
    private boolean comprovarDocumentacion(){
        boolean result=true;
        if(documnetation.getText().length()==0 && optionDoc.getText().equals("----")){
            errorTextDocumentation.setText("Rellena el campo i elige una opcion");
            errorTextDocumentation.setVisible(true);
            result = false;
        }else if(documnetation.getText().length()==0){
            errorTextDocumentation.setVisible(true);
            result = false;
        }else if(optionDoc.getText().equals("----")){
            errorTextDocumentation.setText("Elige una opcion");
            errorTextDocumentation.setVisible(true);
            result = false;
        }
        if (result){
            result = validate(1,optionDoc.getText());
            System.out.println(result);
            if (result==false){
                errorTextDocumentation.setText(optionDoc.getText()+" opcio incorrecta");
                errorTextDocumentation.setVisible(true);
            }
        }
        return result;
    }
    
    private void ocultarMensajes(){
        errorTextDocumentation.setVisible(false);
        errorTextName.setVisible(false);
        errorTextTel.setVisible(false);
        errorTextEmail.setVisible(false);
        errorTextNac.setVisible(false);
        errorTextOc.setVisible(false);
        errorTextCiv.setVisible(false);
        errorTextIncorrectEmail.setVisible(false);
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
