/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

/**
 *
 * @author NewTech
 */
public class ConsultaDAOException extends Exception {
    
    
    public ConsultaDAOException(){
        
    }
    public ConsultaDAOException(String arg) {
        super (arg) ; 
    }
    
    public ConsultaDAOException(Throwable arg) {
        super(arg) ; 
    }
    public ConsultaDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
