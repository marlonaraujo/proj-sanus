/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package medicamentos;

/**
 *
 * @author Vilmar
 */
public class MedicamentoBean {

   private int codigo;
   private String medicamento;

   
   public MedicamentoBean(int codigo, String medicamento){
       this.codigo = codigo;
       this.medicamento = medicamento;
       
   }
   
   public MedicamentoBean (){
       
   }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }
   
   
    
    
}
