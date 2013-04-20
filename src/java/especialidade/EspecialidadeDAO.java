/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package especialidade;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class EspecialidadeDAO {
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    
    public Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    
    public int ultimoRegistro() throws EspecialidadeDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select last_value from especialidade_codigo_especialidade_seq");
            this.rs = this.stmt.executeQuery();
            
            if(! rs.next() ) 
                throw new EspecialidadeDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo >= 1)
                ultimo = ultimo + 1;
            //String codigo = String.valueOf(ultimo);
            
            return ultimo;
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
    }
    public void salvar(EspecialidadeBean especialidade) throws EspecialidadeDAOException,SQLException{
        
        try{
            
            
            String sql = "INSERT INTO especialidade(Nome_Especialidade,Descricao_Especialidade)values(?,?)";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setString(1, especialidade.getNome());
            this.stmt.setString(2, especialidade.getDescricao());
            this.stmt.executeUpdate();
            
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
        
    }
    public void excluir(EspecialidadeBean especialidade) throws EspecialidadeDAOException, SQLException{
        
        try{
            String sql = "delete from especialidade where codigo_especialidade = ?";
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setInt(1, especialidade.getCodigo());
            this.stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
    public void atualizar(EspecialidadeBean especialidade) throws EspecialidadeDAOException, SQLException{
        
        try{
            String sql = "update especialidade set nome_especialidade = ?, descricao_especialidade = ? where codigo_especialidade = ?";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setString(1, especialidade.getNome());
            this.stmt.setString(2, especialidade.getDescricao());
            this.stmt.setInt(3, especialidade.getCodigo());
            this.stmt.executeUpdate();
            
            
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
        
    }
    public List retornaEspecialidades()throws EspecialidadeDAOException, SQLException{
        List especialidades = new ArrayList();
        
        try{
            String sql = "select * from especialidade";
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                EspecialidadeBean especialidade = new EspecialidadeBean();
                especialidade.setCodigo(rs.getInt(1));
                especialidade.setNome(rs.getString(2));
                especialidade.setDescricao(rs.getString(3));
                
                especialidades.add(especialidade);
            }
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
        }finally{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
        
        return especialidades;
    }
    public EspecialidadeBean codigoEspecialidade(int codigo) throws EspecialidadeDAOException, SQLException{ 
        try{ 
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select * from especialidade where codigo_especialidade = ?");
            this.stmt.setInt(1, codigo); 
            this.rs = this.stmt.executeQuery( ); 
            if(! rs.next() ) 
                throw new EspecialidadeDAOException( "Não  foi encontrado nenhum registro com:  " + codigo); 
            
            EspecialidadeBean especialidade = new EspecialidadeBean();
            especialidade.setCodigo(rs.getInt(1));
            especialidade.setNome(rs.getString(2));
            especialidade.setDescricao(rs.getString(3));
            
            return especialidade;
            
        }catch(SQLException e){
            throw new EspecialidadeDAOException(e);
            
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        } 
        
    }
    
}
