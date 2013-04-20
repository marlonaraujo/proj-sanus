/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cirurgia;

import java.sql.Date;


/**
 *
 * @author Administrador
 */
public class CirurgiaBean {
    private int codigo;
    private String cirurgia;
    private int crm;
    private int cpf;
    private String descricao;
    private Date data;
    private String paciente;
    private String medico;
    
    public CirurgiaBean(int codigo, String cirurgia, int cpf, int crm, String descricao, Date data) {
        this.codigo = codigo;
        this.cirurgia = cirurgia;
        this.cpf = cpf;
        this.crm = crm;
        this.descricao = descricao;
        this.data = data;
    }
    public CirurgiaBean(int codigo, String cirurgia, String medico, String paciente, Date data, String descricao) {
        this.codigo = codigo;
        this.cirurgia = cirurgia;
        this.descricao = descricao;
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;
    }
    public CirurgiaBean() {
        
    }
    public CirurgiaBean(int codigo, String cirurgia, String paciente){
        this.codigo = codigo;
        this.cirurgia = cirurgia;
        this.paciente = paciente;
    }
    public CirurgiaBean(int codigo, String cirurgia){
        this.codigo = codigo;
        this.cirurgia = cirurgia;
    }
    public CirurgiaBean(int codigo,String cirurgia,String descricao, Date data){
        this.codigo = codigo;
        this.cirurgia = cirurgia;
        this.descricao = descricao;
        this.data = data;
    }
       
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public void setCirurgia(String cirurgia){
        this.cirurgia = cirurgia;
    }
    
    public void setCrm(int crm){
        this.crm = crm;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setData(Date data){
        this.data = data;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getCirurgia(){
        return cirurgia;
    }
    public int getCrm(){
        return crm;
    }
    public int getCpf(){
        return cpf;
    }
    
    public String getDescricao(){
        return descricao;
    }
    public Date getData(){
        return data;
    }
    public String getPaciente(){
        return paciente;
    }
    public String getMedico(){
        return medico;
    }
    
}
