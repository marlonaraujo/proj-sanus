/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import data.*;

/**
 *
 * @author emilianoeloi
 */
public class PessoaBean implements Serializable {
    
    private ConverteData convData = new ConverteData();
    
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private Date dataNascimento;
    private String senha;
    private int status;
    private ArrayList<String> perfis;
    
    public PessoaBean(){
        
    }
    
    public PessoaBean(int codigo, String nome, String rg,String cpf,Date dataNascimento,String email,String senha ){
        this.codigo = codigo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }
    
    public boolean checkPerfil(String perfil){
        for(int i=0;i<perfis.size();i++){  
            if(perfis.get(i).equalsIgnoreCase(perfil)){
                return true;
            }
        } 
        return false;
    }

    public ArrayList<String> getPerfis() {
        return perfis;
    }

    public void setPerfis(ArrayList<String> perfis) {
        this.perfis = perfis;
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return (this.cpf == null)?null:cpf.replace(".", "").replace("-", "");
    }

    public String getEmail() {
        return this.email;
    }

    public String getRg() {
        return this.rg;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
        //return (dataNascimento==null)?convData.converteEmData("12/12/2012"):this.dataNascimento;
    }

    public String getSenha() {
        return this.senha;
    }
    
    public int getStatus(){
        return this.status;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
}
