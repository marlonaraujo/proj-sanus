/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

import java.sql.Date;

/**
 *
 * @author NewTech
 */
public class ConsultaBean {
    
    private int codigo;
    private String descricao;
    private int crm;
    private int cpf;
    private String medico;
    private String paciente;
    private Date data;
    
    public ConsultaBean(){
        
    }
    
    public ConsultaBean(int codigo, int crm, int cpf, Date data,String descricao){
        this.codigo = codigo;
        this.crm = crm;
        this.cpf = cpf;
        this.data = data;
        this.descricao = descricao;
    }
    
    public ConsultaBean(int codigo, String medico, String paciente, Date data, String descricao){
       this.codigo = codigo;
       this.medico = medico;
       this.paciente = paciente;
       this.data = data;
       this.descricao = descricao;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setCrm(int crm){
        this.crm = crm;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    public void setMedico(String medico){
        this.medico = medico;
    }
    public void setPaciente(String paciente){
        this.paciente = paciente;
    }
    public void setData(Date data){
        this.data = data;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public int getCodigo(){
        return codigo;
    }
    public int getCrm(){
        return crm;
    }
    public int getCpf(){
        return cpf;
    }
    public String getMedico(){
        return medico;
    }
    public String getPaciente(){
        return paciente;
    }
    public Date getData(){
        return data;
    }   
    public String getDescricao(){
        return descricao;
    }
}
