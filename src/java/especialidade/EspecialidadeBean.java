/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package especialidade;

/**
 *
 * @author Administrador
 */
public class EspecialidadeBean {
    private int codigo;
    private String nome;
    private String descricao;
    
    public EspecialidadeBean(){
        
    }
        
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    
    public int getCodigo(){
        return codigo;
    }
    public String getNome(){
        return nome;
    }
    public String getDescricao(){
        return descricao;
    }
    
}
