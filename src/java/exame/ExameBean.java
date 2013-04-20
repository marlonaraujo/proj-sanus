/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package exame;

import java.sql.Date;

/**
 *
 * @author Vilmar
 */
public class ExameBean {
    
    public String getMedico(){
        return medico;
    }
    
    public String getPaciente(){
        return paciente;
    }
    
    public int getCrm(){
        return crm;
    }
    
    public int getCpf(){
        return cpf;
        
    }
    
    public void setMedico(String medico){
        this.medico = medico;
    }
    
    public void setPaciente(String paciente){
        this.paciente = paciente;
    }
    
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    
    public void setCrm(int crm){
        this.crm = crm;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public Date getData(){
        return data;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setData(Date data){
        this.data = data;
    }

    public ExameBean(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }
    public ExameBean(int codigo, String nome, String medico, String paciente, Date data, String descricao){
        this.codigo = codigo;
        this.nome = nome;
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.descricao = descricao;
    }
    
    public ExameBean(){
        
    };
    
    private int codigo;
    private String nome;
    private String descricao;
    private int cpf;
    private int crm;
    private Date data;
    private String paciente;
    private String medico;
    
    
}
