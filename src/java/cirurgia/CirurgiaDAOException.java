/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cirurgia;

/**
 *
 * @author Administrador
 */
public class CirurgiaDAOException extends Exception{ 
    
    public CirurgiaDAOException( ){
    
    } 
    public CirurgiaDAOException(String arg) {
        super (arg) ; 
    }
    
    public CirurgiaDAOException(Throwable arg) {
        super(arg) ; 
    }
    public CirurgiaDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }

}

