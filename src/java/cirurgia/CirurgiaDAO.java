/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cirurgia;

import java.sql.Date;
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
public class CirurgiaDAO {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    public void salvar (CirurgiaBean cirurgia) throws CirurgiaDAOException, SQLException{
        
        try{
            CirurgiaDAO aux = new CirurgiaDAO();
            String str = aux.ultimoRegistro();
            int codigo = (Integer.parseInt(str));
            
            
            String sql = "INSERT INTO cirurgias (nome_cirurgia, descricao, data) VALUES (?,?,?)";
            String medico = "INSERT INTO medico_cirurgia (codigo_medico,codigo_cirurgia) VALUES (?,?)";
            String paciente = "INSERT INTO paciente_cirurgia (codigo_paciente, codigo_cirurgia) VALUES (?,?)";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setString(1, cirurgia.getCirurgia());
            this.stmt.setString(2, cirurgia.getDescricao());
            this.stmt.setDate(3, cirurgia.getData());
            this.stmt.executeUpdate();
            
            this.stmt = con.prepareStatement(medico);
            this.stmt.setInt(1, cirurgia.getCrm());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();
            
            this.stmt = con.prepareStatement(paciente);
            this.stmt.setInt(1, cirurgia.getCpf());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if(stmt != null) stmt.close();
           if(con != null) con.close();
        }
        
    }
    public void excluir(CirurgiaBean cirurgia)  throws CirurgiaDAOException, SQLException {
        if (cirurgia == null) 
            throw new CirurgiaDAOException ("O valor passado não pode ser nulo"); 
        try {
            String sql = "delete from cirurgias where codigo_cirurgia = ?";
            
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql); 
            this.stmt.setInt(1, cirurgia.getCodigo()); 
            this.stmt.executeUpdate(); 
        }catch (SQLException e) { 
            throw new CirurgiaDAOException(e);
            
        }finally { 
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
    
    public void atualizar(CirurgiaBean cirurgia)  throws CirurgiaDAOException, SQLException{ 
        try{ 
            String sql = "UPDATE  cirurgias SET nome_cirurgia = ?, " +  
                  "descricao = ?, data = ? where codigo_cirurgia = ?"; 
            String paciente = "UPDATE paciente_cirurgia set codigo_paciente = ? where codigo_cirurgia = ?";
            String medico = "UPDATE medico_cirurgia set codigo_medico = ? where codigo_cirurgia = ?";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql); 
            this.stmt.setString(1, cirurgia.getCirurgia());
            this.stmt.setString(2, cirurgia.getDescricao());
            this.stmt.setDate(3, cirurgia.getData());
            this.stmt.setInt(4, cirurgia.getCodigo());
            this.stmt.executeUpdate();
            
            this.stmt = con.prepareStatement(paciente);
            this.stmt.setInt(1, cirurgia.getCpf());
            this.stmt.setInt(2, cirurgia.getCodigo());
            this.stmt.executeUpdate();
            
            this.stmt = con.prepareStatement(medico);
            this.stmt.setInt(1, cirurgia.getCrm());
            this.stmt.setInt(2, cirurgia.getCodigo());
            this.stmt.executeUpdate();
            
            
        }catch (SQLException e){ 
            throw new CirurgiaDAOException(e);
            
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } 
    }

    
    public List retornaCirurgias() throws SQLException, CirurgiaDAOException{
        List <CirurgiaBean> cirurgias = new ArrayList <CirurgiaBean> ();
        
        try{
            String sql = "select * from cirurgias as ci inner join paciente_cirurgia as pc on ci.codigo_cirurgia = pc.codigo_cirurgia "
                    + "inner join medico_cirurgia as mc on ci.codigo_cirurgia = mc.codigo_cirurgia inner join medicos as me on "+
                    "mc.codigo_medico = me.codigo_medico inner join pessoas as pe on me.codigo_pessoa = pe.codigo_pessoa inner join "+
                    "pacientes as pa on pc.codigo_paciente = pa.codigo_paciente inner join pessoas as p on pa.codigo_pessoa = p.codigo_pessoa "+
                    "order by ci.codigo_cirurgia asc";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt(1);
                String cirurgia = rs.getString(2);
                String paciente = rs.getString(5);
                  
                cirurgias.add(new CirurgiaBean(codigo,cirurgia,paciente));
            }
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return cirurgias;
    }
    
    //para medico
    public CirurgiaBean medicoCirurgias(int codigo) throws CirurgiaDAOException, SQLException{ 
        try{ 
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select * from cirurgias where codigo_cirurgia = ?");
            this.stmt.setInt(1, codigo); 
            this.rs = this.stmt.executeQuery( ); 
            if(! rs.next() ) 
                throw new CirurgiaDAOException( "Não  foi encontrado nenhum registro com:  " + codigo); 

            String nome = rs.getString(2); 
            String descricao = rs.getString(3);
            Date data = rs.getDate(4);
            
            return new CirurgiaBean(codigo, nome, descricao, data);
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
            
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        } 
        
    }
    
       
    //para paciente
      public List pacienteCirurgias() throws CirurgiaDAOException, SQLException{
        
        List <CirurgiaBean> cirurgias = new ArrayList <CirurgiaBean> ();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente, nome_pessoa from pessoas as pe inner join pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa");
            
            this.rs = this.stmt.executeQuery();
            
            while(rs.next() ){ 
                
            int codigo = rs.getInt(1);
            
            String nome = rs.getString(2);
            cirurgias.add(new CirurgiaBean(codigo,nome));
            
            }
            return cirurgias;
                               
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    
    public String ultimoRegistro() throws CirurgiaDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select last_value from cirurgias_codigo_cirurgia_seq");
            this.rs = this.stmt.executeQuery();
            
            if(! rs.next() ) 
                throw new CirurgiaDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
                ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    
    public List medicos() throws SQLException, CirurgiaDAOException{
        List pessoas = new ArrayList ();
        
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
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
            
            
    } 
    public List pacientes(int codigo) throws SQLException, CirurgiaDAOException{
        List pessoas = new ArrayList();
        String cod = String.valueOf(codigo);
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente, nome_pessoa from pessoas as pe inner join pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa"
                    +" where pe.status_pessoa = 2 or pe.status_pessoa = 1 and pe.rg_pessoa ='"+cod+"'");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
                
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
    public List codigoMedico(int codigo) throws SQLException, CirurgiaDAOException{
        List pessoas = new ArrayList();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_medico from medico_cirurgia where codigo_cirurgia = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            if(rs.next()){
                                
                int medico = rs.getInt(1);
                pessoas.add(medico);
                
            }
                         
                      
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
    public List codigoPaciente(int codigo) throws SQLException, CirurgiaDAOException{
        List pessoas = new ArrayList();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente from paciente_cirurgia where codigo_cirurgia = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            if(rs.next()){
                                
                int medico = (rs.getInt("codigo_paciente"));
                pessoas.add(medico);
                
            }
                         
                      
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
    
    public List retornaTodasCirurgias() throws SQLException, CirurgiaDAOException{
        List <CirurgiaBean> cirurgias = new ArrayList <CirurgiaBean> ();
        
        try{
            String sql = "select * from cirurgias as ci inner join paciente_cirurgia as pc on ci.codigo_cirurgia = pc.codigo_cirurgia "
                    + "inner join medico_cirurgia as mc on ci.codigo_cirurgia = mc.codigo_cirurgia inner join medicos as me on "+
                    "mc.codigo_medico = me.codigo_medico inner join pessoas as pe on me.codigo_pessoa = pe.codigo_pessoa inner join "+
                    "pacientes as pa on pc.codigo_paciente = pa.codigo_paciente inner join pessoas as p on pa.codigo_pessoa = p.codigo_pessoa "+
                    "order by ci.codigo_cirurgia asc";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt(1);
                String cirurgia = rs.getString(2);
                String descricao = rs.getString(3);
                Date data = rs.getDate(4);
                String paciente = rs.getString(23);
                String medico = rs.getString(13);
                
                cirurgias.add(new CirurgiaBean(codigo,cirurgia, medico, paciente, data, descricao));
            }
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return cirurgias;
    }
    
    public List cirurgiaPaciente(int codigo) throws SQLException,CirurgiaDAOException{
        List <CirurgiaBean> list = new ArrayList<CirurgiaBean>(); 
        
        try{
            String sql = "select ci.codigo_cirurgia, ci.nome_cirurgia, pes.nome_pessoa, pe.nome_pessoa, ci.data, ci.descricao "+
                         "from cirurgias as ci inner join medico_cirurgia as mc inner join medicos as m on m.codigo_medico = "
                    +    "mc.codigo_medico on mc.codigo_cirurgia = ci.codigo_cirurgia inner join pessoas as pes on "
                    +"pes.codigo_pessoa = m.codigo_pessoa inner join paciente_cirurgia as pc inner join pacientes as p on "
                    +"p.codigo_paciente =pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa = p.codigo_pessoa "
                    +"on pc.codigo_cirurgia = ci.codigo_cirurgia and p.codigo_pessoa = ?";
            
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                list.add(new CirurgiaBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)));
            }
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
            
        }finally{
            if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return list;
    }
    
    public List Buscapacientes(int codigo) throws SQLException, CirurgiaDAOException{
        
        List pessoas = new ArrayList();
        String cod = String.valueOf(codigo);
        
        
        
        try{
            this.con = getConexao();
            
            this.stmt = this.con.prepareStatement("select pe.codigo_pessoa,nome_pessoa from pessoas as pe inner join "
                    + "pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa where pe.status_pessoa =1 and pe.rg_pessoa like '"+cod+"'");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();             
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
            
            this.stmt = this.con.prepareStatement("select pe.codigo_pessoa,nome_pessoa from pessoas as pe inner join "
                    + "pacientes as pa on pa.codigo_pessoa = pe.codigo_pessoa where pe.status_pessoa =2");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();            
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoas.add(pessoa);                
            }
            
            
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
        
    }
    public List retornaCirurgiasMedicos(int codigo) throws SQLException, CirurgiaDAOException{
        List <CirurgiaBean> cirurgias = new ArrayList <CirurgiaBean> ();
        
        try{
            String sql = "select * from cirurgias as ci inner join paciente_cirurgia as pc on ci.codigo_cirurgia = pc.codigo_cirurgia "
                    + "inner join medico_cirurgia as mc on ci.codigo_cirurgia = mc.codigo_cirurgia inner join medicos as me on "+
                    "mc.codigo_medico = me.codigo_medico inner join pessoas as pe on me.codigo_pessoa = pe.codigo_pessoa inner join "+
                    "pacientes as pa on pc.codigo_paciente = pa.codigo_paciente inner join pessoas as p on pa.codigo_pessoa = p.codigo_pessoa "+
                    " and pe.codigo_pessoa = ? order by ci.codigo_cirurgia asc";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                
                String cirurgia = rs.getString(2);
                String paciente = rs.getString(5);
                  
                cirurgias.add(new CirurgiaBean(codigo,cirurgia,paciente));
            }
            
        }catch(SQLException e){
            throw new CirurgiaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return cirurgias;
    }
     
}
