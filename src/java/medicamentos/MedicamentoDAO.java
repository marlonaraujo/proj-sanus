/*
 * To change this template, choose Tools | T
 * emplates
 * and open the template in the editor.
 */
package medicamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factory.ConexaoFactory;

/**
 *
 * @author emilianoeloi
 */
public class MedicamentoDAO {
    
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    
    public Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    
    
   
    
    public void cadastrar(MedicamentoBean medicamentoBean) throws SQLException,MedicamentoDAOException{
        try{
            String query = "INSERT INTO medicamentos (bula_medicamento) VALUES (?)";
            
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            
            this.ptmt.setString(1, medicamentoBean.getMedicamento());
            this.ptmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void alterar(MedicamentoBean medicamentoBean){
        try{
            String query = "UPDATE medicamentos SET bula_medicamento = ? WHERE codigo_medicamento = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setString(1, medicamentoBean.getMedicamento());
            this.ptmt.setInt(2, medicamentoBean.getCodigo());
            this.ptmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    public void excluir(MedicamentoBean medicamentoBean){ //Integer codigo
        try{
            String query = "DELETE from medicamentos WHERE codigo_medicamento = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setInt(1, medicamentoBean.getCodigo());
            this.ptmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    public List retornarTodos(){
        
        List medicamento = new ArrayList();
        MedicamentoBean medicamentoBean = null;
            
        try{    
            String query = "SELECT codigo_medicamento, bula_medicamento FROM medicamentos";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.rs = this.ptmt.executeQuery();
            
            
            while(rs.next()){
                medicamentoBean = new MedicamentoBean();
                medicamentoBean.setCodigo(rs.getInt(1));
                medicamentoBean.setMedicamento(rs.getString(2));
                medicamento.add(medicamentoBean);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return medicamento;
    }
    
    public MedicamentoBean retornarPeloCodigo(Integer codigo){
        
        MedicamentoBean medicamentoBean = null;
            
        try{    
            String query = "SELECT codigo_medicamento, bula_medicamento FROM medicamentos WHERE codigo_medicamento = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setInt(1, codigo);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                medicamentoBean = new MedicamentoBean();
                medicamentoBean.setCodigo(rs.getInt(1));
                medicamentoBean.setMedicamento(rs.getString(2));

            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return medicamentoBean;
    }
     public String ultimoRegistro() throws MedicamentoDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.ptmt = con.prepareStatement("SELECT last_value from medicamentos_codigo_medicamento_seq");
            this.rs = ptmt.executeQuery();
            
            if(! rs.next() ) {
                throw new MedicamentoDAOException("Não  foi encontrado nenhum registro");
            }else{ 

                int ultimo = rs.getInt(1);
                if(ultimo > 1) {
                    ultimo = ultimo + 1;
                }
                String codigo = String.valueOf(ultimo);
            
            return codigo;
            }
        }catch(SQLException e){
            throw new MedicamentoDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.ptmt != null) ptmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    
}
