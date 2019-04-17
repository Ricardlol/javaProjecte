/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.products;

import app.model.Productes;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ProductsController implements Initializable {
    // textfield de la pagina
    @FXML TextField nombreProduct;
    @FXML TextField precioProduct;
    @FXML TextField prodBuscar;
    
    @FXML TextArea descprod;
    
    // todos los labels de la pagina
    @FXML Label errorTextNomProduct;
    @FXML Label errorTextprecioProd;
    @FXML Label errorTextDescprod;
    @FXML Label errorGlobal;
    
    /**
     * Initializes the controller class.
     */
    
    private Productes productes;
    
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productes= new Productes();
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public void btnSave(){
        productes.create(nombreProduct.getText(), precioProduct.getText(), descprod.getText());
    }
    
    public void btnModify(){
        productes.modify(nombreProduct.getText(), precioProduct.getText(), descprod.getText());
    }
    
    public void btnDelete(){
        productes.delete(nombreProduct.getText(), descprod.getText());
    }
    
    public void btnSearch(){
        System.out.println("Buscar");
        ResultSet result = (ResultSet) productes.search(prodBuscar.getText());
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
