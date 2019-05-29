/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.products;

import app.model.Productes;
import app.model.Authentication;
import app.model.Products;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML TableView tabla;
    @FXML TableColumn cNombre;
    @FXML TableColumn cPrecio;
    @FXML TableColumn cDes;
    
    // Botons
    @FXML Button save;
    @FXML Button modify;
    @FXML Button delete;
    
    private ObservableList<Products> productsData;
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
        cNombre.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("nom"));
        cPrecio.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("precio"));
        cDes.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("descrip"));
        productes= new Productes();
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
    }
    
    private void deshabilitarBtn(){
        save.setDisable(true);
        modify.setDisable(true);
        delete.setDisable(true);
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
        int i=1;
        ResultSet result = (ResultSet) productes.search(prodBuscar.getText());
        try {
            productsData = FXCollections.observableArrayList();
            while(result.next()) {
                Products producte = new Products(result);
                ResultSet resultdesc = (ResultSet) productes.searchDescricio(result.getString("id"));
                while(resultdesc.next()){
                    producte.setDescrip(resultdesc.getString("descripcion"));
                }
                productsData.add(producte);
            }
            tabla.setItems(productsData);
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
}
