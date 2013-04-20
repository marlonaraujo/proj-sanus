/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package hospital;

public class HospitalBean{
    
    private int codigo;
    private String nome;

    public HospitalBean(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public HospitalBean(){
        
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
