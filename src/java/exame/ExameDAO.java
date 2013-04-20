/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factory.ConexaoFactory;
import pessoa.PessoaBean;

/**
 *
 * @author emilianoeloi
 */
public class ExameDAO {
    
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    
    public ExameDAO (){
        
    }
    
    private Connection getConexao() throws SQLException{
        Connection con;
        con=ConexaoFactory.getInstancia().getConexao();
        return con;
    }
    
    private void obterProximoCodigo(ExameBean exame){
                    
        try{    
            
            String query = "SELECT NEXTVAL ('exames_codigo_exame_seq') AS codigo";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                exame.setCodigo(rs.getInt(1));
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
    }
    
   
    
    public void cadastrar(ExameBean exameBean) throws SQLException, ExameDAOException{
        
         
         
        try{
            String med = "insert into medico_exames(codigo_medico,codigo_exame)values(?,?)";
            String sql = "insert into paciente_exames(codigo_paciente,codigo_exame) values (?,?)";
            String query = "INSERT INTO exames (Nome_Exame, Descricao_exame, data) VALUES (?, ?, ?)";
            
            String str = this.ultimoRegistro();
            int codigo = (Integer.parseInt(str));
            
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            //this.ptmt.setInt(1, exameBean.getCodigo());
            this.ptmt.setString(1, exameBean.getNome());
            this.ptmt.setString (2, exameBean.getDescricao());
            this.ptmt.setDate(3, exameBean.getData());
            this.ptmt.executeUpdate();
            
            this.ptmt = con.prepareStatement(sql);
            this.ptmt.setInt(1, exameBean.getCpf());
            this.ptmt.setInt(2, codigo);
            this.ptmt.executeUpdate();
            
            this.ptmt = con.prepareStatement(med);
            this.ptmt.setInt(1, exameBean.getCrm());
            this.ptmt.setInt(2, codigo);
            this.ptmt.executeUpdate();
            
        }catch(SQLException e){
            throw new ExameDAOException(e);
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
    
    public void alterar(ExameBean exameBean){
        try{
            String query = "UPDATE exames SET Nome_exame = ?, Descricao_exame = ? WHERE Codigo_exame = ?";
            String sql = "UPDATE paciente_exames set codigo_paciente = ? where codigo_exame = ?";
            
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setString(1, exameBean.getNome());
            this.ptmt.setString (2, exameBean.getDescricao());
            this.ptmt.setInt(3, exameBean.getCodigo());
            this.ptmt.executeUpdate();
            
            this.ptmt = con.prepareStatement(sql);
            this.ptmt.setInt(1, exameBean.getCpf());
            this.ptmt.setInt(2, exameBean.getCodigo());
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
    
    public void excluir(ExameBean examebean)throws SQLException, ExameDAOException{ //Integer codigo
        try{
            String query = "DELETE from exames WHERE Codigo_exame = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setInt(1, examebean.getCodigo());
            this.ptmt.executeUpdate();
        }catch(SQLException e){
            throw new ExameDAOException(e);
        }finally{
            
                if(this.rs!=null)
                    rs.close();
                if(this.ptmt!=null)
                    ptmt.close();
                if(this.con!=null)
                    this.con.close();
        }
    }
    
    public List retornarTodos(){
        
        List exame = new ArrayList();
        ExameBean exameBean = null;
            
        try{    
            String query = "SELECT Codigo_exame, Nome_exame, Descricao_exame FROM exames";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                exameBean = new ExameBean();
                exameBean.setCodigo(rs.getInt(1));
                exameBean.setNome(rs.getString(2));
                exameBean.setDescricao(rs.getString(3));
                exame.add(exameBean);
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
        return exame;
    }
    
    public ExameBean retornarPeloCodigo(Integer codigo){
        
        ExameBean exameBean = null;
            
        try{    
            String query = "SELECT Codigo_exame, Nome_exame, Descricao_exame, data FROM exames WHERE Codigo_exame = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setInt(1, codigo);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                exameBean = new ExameBean();
                exameBean.setCodigo(rs.getInt(1));
                exameBean.setNome(rs.getString(2));
                exameBean.setDescricao(rs.getString(3));
                exameBean.setData(rs.getDate(4));
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
        return exameBean;
    }
    
    public List examePaciente(int codigo) throws SQLException{
        List <ExameBean> list = new ArrayList<ExameBean>(); 
        
        try{
            String sql = "select e.codigo_exame, e.nome_exame,med.nome_pessoa,pac.nome_pessoa,e.data, e.descricao_exame "+
                         "from exames as e inner join medico_exames as me inner join medicos as m on m.codigo_medico"
                    +"= me.codigo_medico on me.codigo_exame = e.codigo_exame inner join pessoas as med on med.codigo_pessoa"
                    +"= m.codigo_pessoa inner join paciente_exames as pe inner join pacientes as p on p.codigo_paciente"
                    +"= pe.codigo_paciente inner join pessoas as pac on pac.codigo_pessoa = p.codigo_pessoa "
                    +"on pe.codigo_exame = e.codigo_exame and p.codigo_pessoa = ?";
            
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement(sql);
            this.ptmt.setInt(1, codigo);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                list.add(new ExameBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)));
            }
            
        }catch(SQLException e){
            throw new SQLException(e);
            
        }finally{
            if (this.rs != null) rs.close();
           if(this.ptmt != null) ptmt.close();
           if(this.con != null) con.close();
        }
        return list;
    }
    
     public List pacientes(int codigo) throws SQLException{
        List pessoas = new ArrayList();
        String cod = String.valueOf(codigo);
        try{
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement("select codigo_paciente, nome_pessoa from pessoas as pe inner join pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa"
                    +" where pe.status_pessoa = 2 or pe.status_pessoa = 1 and pe.id_pessoa = '"+cod+"'");
            this.rs = this.ptmt.executeQuery();
            
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
           if(this.ptmt != null) ptmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
     
     public List medicos() throws SQLException{
        List pessoas = new ArrayList();
        
        try{
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement("select codigo_medico, nome_pessoa from pessoas as pe inner join medicos as pa on pa.codigo_pessoa = pe.codigo_pessoa");
            this.rs = this.ptmt.executeQuery();
            
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
           if(this.ptmt != null) ptmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
     public String ultimoRegistro() throws ExameDAOException, SQLException{
        
        try{
           
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement("SELECT last_value from exames_codigo_exame_seq");
            this.rs = this.ptmt.executeQuery();
            
            if(!rs.next() )
                throw new ExameDAOException("Não  foi encontrado nenhum registro"); 
            
            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new ExameDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.ptmt != null) ptmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
     public List codigoPaciente(int codigo) throws SQLException, ExameDAOException{
        List pessoas = new ArrayList();
        int paciente = 0;
        try{
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement("select codigo_paciente from paciente_exames where codigo_exame = ?");
            
            this.ptmt.setInt(1, codigo);
            this.rs = this.ptmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                paciente = rs.getInt(1); 
                pessoas.add(paciente);
                
            }
            
                      
        }catch(SQLException e){
            throw new ExameDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.ptmt != null) ptmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     
     public List codigoMedico(int codigo) throws SQLException, ExameDAOException{
        List pessoas = new ArrayList();
        int medico = 0;
        try{
            this.con = getConexao();
            this.ptmt = this.con.prepareStatement("select codigo_medico from medico_exames where codigo_exame = ?");
            
            this.ptmt.setInt(1, codigo);
            this.rs = this.ptmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                medico = rs.getInt(1); 
                pessoas.add(medico);
                
            }
            
                      
        }catch(SQLException e){
            throw new ExameDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.ptmt != null) ptmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     
    public List retornarExamesMedico(int codigo){
        
        List exame = new ArrayList();
        ExameBean exameBean = null;
            
        try{    
            String query = "select e.codigo_exame,e.nome_exame,e.descricao_exame,e.data,pm.nome_pessoa,pp.nome_pessoa from exames as e inner join"
                    +" paciente_exames as pe on pe.codigo_exame= e.codigo_exame inner join pacientes as pa on pa.codigo_paciente"
                    +"=pe.codigo_paciente inner join pessoas as pp on pp.codigo_pessoa = pa.codigo_pessoa inner join medico_exames"
                    +" as me on me.codigo_exame = e.codigo_exame inner join medicos as m on m.codigo_medico = me.codigo_medico inner"
                    +" join pessoas as pm on pm.codigo_pessoa = m.codigo_pessoa and pm.codigo_pessoa = ?";
            this.con = getConexao();
            this.ptmt = con.prepareStatement(query);
            this.ptmt.setInt(1,codigo);
            this.rs = this.ptmt.executeQuery();
            
            while(rs.next()){
                exameBean = new ExameBean();
                exameBean.setCodigo(rs.getInt(1));
                exameBean.setNome(rs.getString(2));
                exameBean.setDescricao(rs.getString(3));
                exameBean.setData(rs.getDate(4));
                exameBean.setMedico(rs.getString(5));
                exameBean.setPaciente(rs.getString(6));
                exame.add(exameBean);
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
        return exame;
    }
    
}
