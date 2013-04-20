/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restricao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pessoa.PessoaBean;
/**
 *
 * @author Administrador
 */
public class RestricaoDAO {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    private Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    public void salvar (RestricaoBean restricoes) throws RestricaoDAOException, SQLException{
        
        try{
            
            this.con = getConexao();
            
            RestricaoDAO aux = new RestricaoDAO();
            String str = aux.ultimoRegistro();
            int codigo = (Integer.parseInt(str));
            
           
            String sql = "INSERT INTO restricoes (descricao_restricao, tipo_restricao) VALUES (?,?)";
            String query = "insert into paciente_restricao(codigo_paciente, codigo_restricao) values (?,?) ";  
            
            this.stmt = this.con.prepareStatement(sql);
            this.stmt.setString(1, restricoes.getDescricao());
            this.stmt.setString(2, restricoes.getTipo());
            this.stmt.executeUpdate();
            
            this.stmt = this.con.prepareStatement(query);
            this.stmt.setInt(1, restricoes.getCpf());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();
                
            
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
        }finally{
           if(rs != null) rs.close();
           if(stmt != null) stmt.close();
           if(con != null) con.close();
        }
        
    }
    public void excluir(RestricaoBean restricoes)  throws RestricaoDAOException, SQLException {
        if (restricoes == null) 
            throw new RestricaoDAOException ("O valor passado não pode ser nulo"); 
        try {
            String sql = "delete from restricoes where codigo_restricao = ?";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql); 
            this.stmt.setInt(1, restricoes.getCodigo()); 
            this.stmt.executeUpdate(); 
            
            
        }catch (SQLException e) { 
            throw new RestricaoDAOException(e);
            
        }finally { 
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
    
    public void atualizar(RestricaoBean restricoes)  throws RestricaoDAOException, SQLException{ 
        try{ 
            String sql = "UPDATE  restricoes SET descricao_restricao = ?, " +  
                    "tipo_restricao = ? where codigo_restricao = ?"; 
            String query = "UPDATE paciente_restricao set codigo_paciente = ? where codigo_restricao = ?";
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql); 
            this.stmt.setString(1, restricoes.getDescricao());
            this.stmt.setString(2, restricoes.getTipo());
            this.stmt.setInt(3, restricoes.getCodigo());
            this.stmt.executeUpdate(); 
            
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, restricoes.getCpf());
            this.stmt.setInt(2, restricoes.getCodigo());
            this.stmt.executeUpdate();
            
        }catch (SQLException e){ 
            throw new RestricaoDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } 
    }

    
    public List retornaRestricoes() throws SQLException, RestricaoDAOException{
        List <RestricaoBean> restricoes = new ArrayList <RestricaoBean> ();
        
        try{
            this.con = getConexao();
            String query = "select * from paciente_restricao as pr inner join restricoes as re on pr.codigo_restricao = re.codigo_restricao "+
                           "inner join pacientes as pa on pa.codigo_paciente = pr.codigo_paciente inner join pessoas as pe on "+ 
                            "pa.codigo_pessoa = pe.codigo_pessoa order by re.codigo_restricao asc";
           
            this.stmt = this.con.prepareStatement(query);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                
                int codigo = rs.getInt(2);
                String descricao = rs.getString(9);
                String tipo = rs.getString(5);
                int cpf = rs.getInt(1);
                
                restricoes.add(new RestricaoBean(codigo, descricao, tipo, cpf));
                
                
            
            }
            
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return restricoes;
    }
    
    
    public RestricaoBean adminRestricoes(int codigo) throws RestricaoDAOException, SQLException{ 
        try{ 
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select * from restricoes where codigo_restricao = ?");
            this.stmt.setInt(1, codigo); 
            this.rs = this.stmt.executeQuery(); 
            if(! rs.next() ) 
                throw new RestricaoDAOException( "Não  foi encontrado nenhum registro com:  " + codigo); 

            String descricao = rs.getString(2); 
            String tipo = rs.getString(3); 
            
            
            return new RestricaoBean(codigo, descricao, tipo, 0);
            
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
            
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        } 
        
    }
   public String ultimoRegistro() throws RestricaoDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("SELECT last_value from restricoes_codigo_restricao_seq");
            this.rs = this.stmt.executeQuery();
            
            if(! rs.next() ) 
                throw new RestricaoDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    public List pacientes() throws SQLException,RestricaoDAOException{
        List pessoas = new ArrayList();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente, nome_pessoa from pessoas as pe inner join pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
        
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
    public List codigoPaciente(int codigo) throws SQLException, RestricaoDAOException{
        List pessoas = new ArrayList();
        int paciente = 0;
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente from paciente_restricao where codigo_restricao = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                paciente = rs.getInt(1); 
                pessoas.add(paciente);
                
            }
            
                      
        }catch(SQLException e){
            throw new RestricaoDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
    
    public List restricaoPaciente(int codigo) throws SQLException{
        List <RestricaoBean> list = new ArrayList<RestricaoBean>(); 
        
        try{
            String sql = "select r.codigo_restricao, r.descricao_restricao, r.tipo_restricao from restricoes as r "+
                         "inner join paciente_restricao as pr inner join pacientes as p on p.codigo_paciente = pr.codigo_paciente"
                    +    " on pr.codigo_restricao = r.codigo_restricao and p.codigo_pessoa = ?";
            
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                list.add(new RestricaoBean(rs.getInt(1),rs.getString(2),rs.getString(3),0));
            }
            
        }catch(SQLException e){
            throw new SQLException(e);
            
        }finally{
            if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return list;
    }
    
   
}
