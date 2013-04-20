/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package receita;

/**
 *
 * @author NewTech
 */
public class ReceitaDAOException extends Exception {
    
     public ReceitaDAOException(){
        
    }
    public ReceitaDAOException(String arg) {
        super (arg) ; 
    }
    
    public ReceitaDAOException(Throwable arg) {
        super(arg) ; 
    }
    public ReceitaDAOException(String arg, Throwable arg2){
        super(arg,arg2); 
    }
    
}
