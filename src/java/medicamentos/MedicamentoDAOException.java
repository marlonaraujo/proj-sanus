/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicamentos;

/**
 *
 * @author NewTech
 */
public class MedicamentoDAOException extends Exception {
    
     public MedicamentoDAOException(){
        
    }
    public MedicamentoDAOException(String arg) {
        super (arg) ; 
    }
    
    public MedicamentoDAOException(Throwable arg) {
        super(arg) ; 
    }
    public MedicamentoDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
