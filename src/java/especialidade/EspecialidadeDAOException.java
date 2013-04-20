/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package especialidade;

/**
 *
 * @author Administrador
 */
public class EspecialidadeDAOException extends Exception {
    
    public EspecialidadeDAOException(){
        
    }
    public EspecialidadeDAOException(String arg) {
        super (arg) ; 
    }
    
    public EspecialidadeDAOException(Throwable arg) {
        super(arg) ; 
    }
    public EspecialidadeDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
