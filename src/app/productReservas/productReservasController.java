/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.productReservas;

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
     
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public void btnService(){
        System.out.println("Hola Extra");
        
    }    
    
    public void btnSave() throws ParseException{
        
    }
    
    
    

    public void btnModify() throws ParseException{
        
        
    }

    public void btnDelete() {
    }

    public void btnSearch() {
        
    }
    
}


