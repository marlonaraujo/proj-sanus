/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

/**
 *
 * @author NewTech
 */
public class PacienteDAOException extends Exception {
    
     public PacienteDAOException(){
        
    }
    public PacienteDAOException(String arg) {
        super (arg) ; 
    }
    
    public PacienteDAOException(Throwable arg) {
        super(arg) ; 
    }
    public PacienteDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
