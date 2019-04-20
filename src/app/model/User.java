package app.model;
/**
 * @web http://www.jc-mouse.net/
 * @author jc mouse
 */
public class User {
    
    private String nick;
    private String pass;
    private int admin;
    
    /** Constructor de clase */
    public User(){}

    /** Constructor de clase
     * @param nick apodo
     * @param pass palabra secreta
     * @param admin si es administrador o no
     */
    public User(String nick, String pass, int admin){
        this.nick = nick;
        this.pass = pass;
        this.admin = admin;
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }    
    
}
