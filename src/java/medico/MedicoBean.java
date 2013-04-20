/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medico;

import pessoa.*;

/**
 *
 * @author emilianoeloi
 */
public class MedicoBean extends PessoaBean {
    
    private String crm;
    private int codigoPessoa;
    private int codigoHospital;
    private int codigoEspecialidade;
    
    public MedicoBean() {
        super();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public int getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(int codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public int getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(int codigoHospital) {
        this.codigoHospital = codigoHospital;
    }

    public int getCodigoEspecialidade() {
        return codigoEspecialidade;
    }

    public void setCodigoEspecialidade(int codigoEspecialidade) {
        this.codigoEspecialidade = codigoEspecialidade;
    }
}
