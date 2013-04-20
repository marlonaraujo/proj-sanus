/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

import pessoa.*;

/**
 *
 * @author emilianoeloi
 */
public class PacienteBean extends PessoaBean {
    
    private int codigoPessoa;
    
    public void setCodigoPessoa(int codigoPessoa){
        this.codigoPessoa = codigoPessoa;
    }
    
    public int getCodigoPessoa(){
        return this.codigoPessoa;
    }

    public PacienteBean() {
        super();
    }
    
}
