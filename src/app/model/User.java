package app.model;
/**
 * @web http://www.jc-mouse.net/
 * @author jc mouse
 */
public class User {
    
    private String nick;
    private String pass;
    private String fullName;
    
    /** Constructor de clase */
    public User(){}

    /** Constructor de clase
     * @param nick apodo
     * @param pass palabra secreta
     * @param fullName nombre completo
     */
    public User(String nick, String pass, String fullName){
        this.nick = nick;
        this.pass = pass;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }    
    
}
