/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restricao;

/**
 *
 * @author Administrador
 */
public class RestricaoBean {
    private int codigo;
    private String descricao;
    private String tipo;
    private int cpf;
    
    public RestricaoBean(int codigo, String descricao, String tipo, int cpf){
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.cpf = cpf;
    }
    public RestricaoBean(){
        
    }
    
    public int getCodigo(){
        return codigo;
    }
    public String getDescricao(){
        return descricao;
    }
    public String getTipo(){
        return tipo;
    }
    public int getCpf(){
        return cpf;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    
}
