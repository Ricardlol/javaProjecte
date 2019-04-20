package app.model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import javafx.stage.Stage;
/**
 * @web http://www.jc-mouse.net/
 * @author ricardLopez & joseManuel
 */
public class Authentication  implements Connectionsdb{
    //atributos
    private String user;
    private String pass;
    private static int tipus;
    
    public Authentication(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
    
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String tabla = "usuarios";
    
    private void connection(){
        conn = Connectionsdb.connectarMySQL();
        try{
            stmt=conn.createStatement();
        }catch(SQLException e){
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
     
    public boolean verifyUserData(String nom, String pass) {
        boolean userfind=false;
        connection();
        sSQL ="SELECT * FROM "+tabla+" WHERE nombre='"+nom+"' AND password='"+pass+"';";
        try {
            rs=stmt.executeQuery(sSQL);
            if(rs !=null){
                while (rs.next())
                {
                    //indicamos que hemos encontrado el usuario
                    userfind =true;                
                    
                    //Asignamos a la variable tipus el tipo de usuario que es
                    //Si es administrador o recepcionista
                    tipus=rs.getInt (4);
               }
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
            return userfind;
        }
    }

    public void setStage(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Obtenemos el tipo de usuario que es
    public static int getTipus(){
        return tipus;
    }

   
}
