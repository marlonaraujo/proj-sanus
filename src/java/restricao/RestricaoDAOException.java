/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restricao;

/**
 *
 * @author Administrador
 */
public class RestricaoDAOException extends Exception{ 
    
    public RestricaoDAOException( ){
    
    } 
    public RestricaoDAOException(String arg) {
        super (arg) ; 
    }
    
    public RestricaoDAOException(Throwable arg) {
        super(arg) ; 
    }
    public RestricaoDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }

}