/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Administrador
 */
public class GenericDAOException extends RuntimeException{ 
    
    public GenericDAOException( ){
    
    } 
    public GenericDAOException(String arg) {
        super (arg) ; 
    }
    
    public GenericDAOException(Throwable arg) {
        super(arg) ; 
    }
    public GenericDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }

}

