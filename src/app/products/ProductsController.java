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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ProductsController implements Initializable {
    // textfield de la pagina
    private @FXML TextField nombreProduct;
    private @FXML TextField precioProduct;
    private @FXML TextField prodBuscar;
    
    private @FXML TextArea descprod;
    
    // todos los labels de la pagina
    private @FXML Label errorGlobal;
    
    // table in page
    private @FXML GridPane gridpane;
    private final int fila=10;
    private final int col=3;
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
        cabeceras();
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
    
    private void cabeceras(){
        for(int x=0;x<col; x++){
            if(x==0){
                gridpane.add(new Label("NOMBRE"), x,0);
            }else if(x==1){
                gridpane.add(new Label("PRECIO"), x,0);
            }else{
                gridpane.add(new Label("DESCRIPCIÓN"), x,0);
            }
        }
    }
    
    public void btnSearch(){
        int i=1;
        gridpane.getChildren().clear();
        cabeceras();
        ResultSet result = (ResultSet) productes.search(prodBuscar.getText());
        try {
            while(result.next()) {
                gridpane.add(new Label(result.getString("nombre")),0,i);
                gridpane.add(new Label(result.getString("precio")),1,i);
                ResultSet resultdesc = (ResultSet) productes.searchDescricio(result.getString("id"));
                while(resultdesc.next()){
                    gridpane.add(new Label(resultdesc.getString("descripcion")),2,i);
                }
                i++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
}
