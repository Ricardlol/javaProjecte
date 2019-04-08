/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clients;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ClientsController implements Initializable{

    @FXML Button btnOk;
    @FXML TextField documnetation;
    @FXML TextField client;
    @FXML TextField tel;
    @FXML TextField email;
    @FXML TextField nac;
    @FXML TextField ocupation;
    @FXML TextField status;
    @FXML Label errorTextDocumentation;
    @FXML Label errorTextName;
    @FXML Label errorTextTel;
    @FXML Label errorTextEmail;
    @FXML Label errorTextNac;
    @FXML Label errorTextOc;
    @FXML Label errorTextCiv;
    @FXML Label errorTextIncorrectEmail;
    @FXML MenuButton optionDoc;
    
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
    
    public void btnGuardar(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            System.out.println("A la base de datos !!!");
        } else{
            System.out.println("Pon los bien puto");
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
            boolean mensaje=comprovarEmail(email.getText());
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
        /*if (result){
            result = comprovarString(optionDoc.getText());
            System.out.println(result);
            if (result==false){
                errorTextDocumentation.setText(optionDoc.getText()+" opcio incorrecta");
                errorTextDocumentation.setVisible(true);
            }
        }*/
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
    
    private boolean comprovarEmail(String email){
        boolean result = false;
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"+"[a-z0-9-]+(\\.[a-z0-9-])$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (email != null){
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
              System.out.println("Válido");
            }
        }
        
        return result;
    }
    public void setStage (Stage stage){
        this.stage = stage;
    }
}
