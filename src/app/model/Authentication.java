package app.model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @web http://www.jc-mouse.net/
 * @author ricardLopez & joseManuel
 */
public class Authentication  implements Connectionsdb{
    //atributos
    private String user;
    private String pass;
    private static int tipus;
    private static String usu;
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String tabla = "usuarios";
    
    public Authentication(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
    
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
    
    /**
     * verifica si l'usuari existeix en la base de dades
     * @param nom nom del usuari que verificarem
     * @param pass password del usuari
     * @return 
     */
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
                    usu=rs.getString (2);
               }
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return userfind;
    }
    
    /**
     * per veure si un usuari es admin o no
     * @return el tipus que es l'usuari
     */
    public static int getTipus(){
        return tipus;
    }
    
    /**
     * 
     * @return 
     */
    public static  String getUsuari(){
        return usu;
    }

   
}
