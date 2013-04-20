/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

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
 * @author NewTech
 */
public class ConsultaDAO {
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    
    public String ultimoRegistro() throws ConsultaDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.stmt = con.prepareStatement("SELECT last_value from consultas_codigo_consulta_seq");
            this.rs = this.stmt.executeQuery();
            
            if(! rs.next() ) 
                throw new ConsultaDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new ConsultaDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    
    public void salvar (ConsultaBean consulta) throws ConsultaDAOException, SQLException{
        
        try{
                      
            String str = this.ultimoRegistro();
            int codigo = (Integer.parseInt(str));
            
           
            String sql = "INSERT INTO consultas (descricao_consulta, data) VALUES (?,?)";
            String query = "insert into paciente_consulta(codigo_paciente, codigo_consulta) values (?,?) ";  
            String med = "insert into medico_consulta(codigo_medico,codigo_consulta)values(?,?)";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setString(1, consulta.getDescricao());
            this.stmt.setDate(2, consulta.getData());
            this.stmt.executeUpdate();
            
            this.stmt = this.con.prepareStatement(query);
            this.stmt.setInt(1, consulta.getCpf());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();
            
            this.stmt = this.con.prepareStatement(med);
            this.stmt.setInt(1, consulta.getCrm());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();    
            
            
            
        }catch(SQLException e){
            throw new ConsultaDAOException(e);
        }finally{
           if(rs != null) rs.close();
           if(stmt != null) stmt.close();
           if(con != null) con.close();
        }
        
    }
    
    public void excluir(ConsultaBean consulta)  throws ConsultaDAOException, SQLException {
        if (consulta == null) 
            throw new ConsultaDAOException ("O valor passado não pode ser nulo"); 
        try {
            String sql = "delete from consultas where codigo_consulta = ?";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql); 
            this.stmt.setInt(1, consulta.getCodigo()); 
            this.stmt.executeUpdate(); 
            
            
        }catch (SQLException e) { 
            throw new ConsultaDAOException(e);
            
        }finally { 
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
    
    public void atualizar(ConsultaBean consulta)  throws ConsultaDAOException, SQLException{ 
        try{ 
            String sql = "UPDATE  consultas SET descricao_consulta = ?, " +  
                    "data = ? where codigo_consulta = ?"; 
            String query = "UPDATE paciente_consulta set codigo_paciente = ? where codigo_consulta = ?";
            String med = "update medico_consulta set codigo_medico = ? where codigo_consulta = ?";
                        
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql); 
            this.stmt.setString(1, consulta.getDescricao());
            this.stmt.setDate(2, consulta.getData());
            this.stmt.setInt(3, consulta.getCodigo());
            this.stmt.executeUpdate(); 
            
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, consulta.getCpf());
            this.stmt.setInt(2, consulta.getCodigo());
            this.stmt.executeUpdate();
                        
            this.stmt = con.prepareStatement(med);
            this.stmt.setInt(1, consulta.getCrm());
            this.stmt.setInt(2, consulta.getCodigo());
            this.stmt.executeUpdate();
            
        }catch (SQLException e){ 
            throw new ConsultaDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } 
    }
    
    public List retornarTodos(){
        
        List consulta = new ArrayList();
        ConsultaBean consultaBean = null;
            
        try{
            
            String query = "select c.codigo_consulta, c.descricao_consulta, c.data,pe.nome_pessoa,pm.nome_pessoa from consultas"
                    +" as c inner join paciente_consulta as pc on pc.codigo_consulta = c.codigo_consulta inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_consulta as mc on mc.codigo_consulta = c.codigo_consulta inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa = me.codigo_pessoa";
            
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                consultaBean = new ConsultaBean();
                consultaBean.setCodigo(rs.getInt(1));
                consultaBean.setDescricao(rs.getString(2));
                consultaBean.setData(rs.getDate(3));
                consultaBean.setPaciente(rs.getString(4));
                consultaBean.setMedico(rs.getString(5));
                
                consulta.add(consultaBean);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.stmt!=null)
                    stmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return consulta;
    }
    
    public List consultaPaciente(int codigo) throws SQLException{
        List <ConsultaBean> list = new ArrayList<ConsultaBean>(); 
        
        try{
            String query = "select c.codigo_consulta, pm.nome_pessoa, pe.nome_pessoa, c.data, c.descricao_consulta from consultas"
                    +" as c inner join paciente_consulta as pc on pc.codigo_consulta = c.codigo_consulta inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_consulta as mc on mc.codigo_consulta = c.codigo_consulta inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa ="
                    +"me.codigo_pessoa and pe.codigo_pessoa = ?";
            
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                list.add(new ConsultaBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
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
    
    public List pacientes(int codigo) throws SQLException{
        List pessoas = new ArrayList();
        String cod = String.valueOf(codigo);
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente, nome_pessoa from pessoas as pe inner join pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa"
                    +" where pe.status_pessoa = 2 or pe.status_pessoa = 1 and pe.id_pessoa ='"+cod+"'");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
        
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
     
     public List medicos() throws SQLException{
        List pessoas = new ArrayList();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_medico, nome_pessoa from pessoas as pe inner join medicos as pa on pa.codigo_pessoa = pe.codigo_pessoa");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
        
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
     public List codigoPaciente(int codigo) throws SQLException, ConsultaDAOException{
        List pessoas = new ArrayList();
        int paciente = 0;
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente from paciente_consulta where codigo_consulta = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                paciente = rs.getInt(1); 
                pessoas.add(paciente);
                
            }
            
                      
        }catch(SQLException e){
            throw new ConsultaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     
     public List codigoMedico(int codigo) throws SQLException, ConsultaDAOException{
        List pessoas = new ArrayList();
        int medico = 0;
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_medico from medico_consulta where codigo_consulta = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                medico = rs.getInt(1); 
                pessoas.add(medico);
                
            }
            
                      
        }catch(SQLException e){
            throw new ConsultaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     public ConsultaBean retornarPeloCodigo(Integer codigo){
        
        ConsultaBean consultaBean = null;
            
        try{    
            String query = "SELECT Codigo_consulta, Descricao_consulta, data FROM consultas WHERE Codigo_consulta = ?";
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                consultaBean = new ConsultaBean();
                consultaBean.setCodigo(rs.getInt(1));
                consultaBean.setDescricao(rs.getString(2));
                consultaBean.setData(rs.getDate(3));
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.stmt!=null)
                    stmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return consultaBean;
    }
     
    public List retornarConsultasMedico(int codigo){
        
        List consulta = new ArrayList();
        ConsultaBean consultaBean = null;
            
        try{
            
            String query = "select c.codigo_consulta, c.descricao_consulta, c.data,pe.nome_pessoa,pm.nome_pessoa from consultas"
                    +" as c inner join paciente_consulta as pc on pc.codigo_consulta = c.codigo_consulta inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_consulta as mc on mc.codigo_consulta = c.codigo_consulta inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa = "
                    +"me.codigo_pessoa and pm.codigo_pessoa = ?";
            
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                consultaBean = new ConsultaBean();
                consultaBean.setCodigo(rs.getInt(1));
                consultaBean.setDescricao(rs.getString(2));
                consultaBean.setData(rs.getDate(3));
                consultaBean.setPaciente(rs.getString(4));
                consultaBean.setMedico(rs.getString(5));
                
                consulta.add(consultaBean);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(this.rs!=null)
                    rs.close();
                if(this.stmt!=null)
                    stmt.close();
                if(this.con!=null)
                    this.con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return consulta;
    }
    
}
