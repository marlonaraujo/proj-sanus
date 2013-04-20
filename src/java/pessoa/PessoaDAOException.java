/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

/**
 *
 * @author emilianoeloi
 */
public class PessoaDAOException extends Exception {
    
    public PessoaDAOException(){
        
    }
    public PessoaDAOException(String arg){
        super(arg);
    }
    public PessoaDAOException(Throwable thr){
        super(thr);
    }
    public PessoaDAOException(String arg, Throwable thr){
        super(arg, thr);
    }
}
