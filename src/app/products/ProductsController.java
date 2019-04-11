/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.products;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import com.mysql.jdbc.Connection;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ProductsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void setStage (Stage stage){
        this.stage = stage;
    }   
    
}
