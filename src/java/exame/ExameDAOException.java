/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exame;

/**
 *
 * @author NewTech
 */
public class ExameDAOException extends Exception {
    
    public ExameDAOException( ){
    
    } 
    public ExameDAOException(String arg) {
        super (arg) ; 
    }
    
    public ExameDAOException(Throwable arg) {
        super(arg) ; 
    }
    public ExameDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
