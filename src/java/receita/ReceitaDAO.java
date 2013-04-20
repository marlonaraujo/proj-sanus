/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package receita;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import medicamentos.MedicamentoBean;
import pessoa.PessoaBean;

/**
 *
 * @author NewTech
 */
public class ReceitaDAO {
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public Connection getConexao() throws SQLException{
        
            Connection con;
            con = ConexaoFactory.getInstancia().getConexao();
            return con;
        
    }
    public void salvar (ReceitaBean receita) throws ReceitaDAOException, SQLException{
        
        try{
            
            
                        
            String str = this.ultimoRegistro();
            int codigo = (Integer.parseInt(str));
            
            String sql = "INSERT INTO receitas (descricao_receita, data) VALUES (?,?)";
            String query = "insert into paciente_receita(codigo_paciente, codigo_receita) values (?,?) ";  
            String med = "insert into medico_receita(codigo_medico,codigo_receita)values(?,?)";
            String rem = "insert into receitas_medicamentos(codigo_receita,codigo_medicamento)values(?,?)";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql);
            this.stmt.setString(1, receita.getDescricao());
            this.stmt.setDate(2, receita.getData());
            this.stmt.executeUpdate();
            
            this.stmt = this.con.prepareStatement(query);
            this.stmt.setInt(1, receita.getCpf());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();
            
            this.stmt = this.con.prepareStatement(med);
            this.stmt.setInt(1, receita.getCrm());
            this.stmt.setInt(2, codigo);
            this.stmt.executeUpdate();    
            
            this.stmt = this.con.prepareStatement(rem);
            this.stmt.setInt(1, codigo);
            this.stmt.setInt(2, receita.getMedicamento());
            this.stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new ReceitaDAOException(e);
        }finally{
           if(rs != null) rs.close();
           if(stmt != null) stmt.close();
           if(con != null) con.close();
        }
        
    }
    
    public void excluir(ReceitaBean receita)  throws ReceitaDAOException, SQLException {
        if (receita == null) 
            throw new ReceitaDAOException ("O valor passado não pode ser nulo"); 
        try {
            String sql = "delete from receitas where codigo_receita = ?";
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(sql); 
            this.stmt.setInt(1, receita.getCodigo()); 
            this.stmt.executeUpdate(); 
            
            
        }catch (SQLException e) { 
            throw new ReceitaDAOException(e);
            
        }finally { 
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }
    }
    
    public void atualizar(ReceitaBean receita)  throws ReceitaDAOException, SQLException{ 
        try{ 
            String sql = "UPDATE  receitas SET descricao_receita = ?, " +  
                    "data = ? where codigo_receita = ?"; 
            String query = "UPDATE paciente_receita set codigo_paciente = ? where codigo_receita = ?";
            String med = "update medico_receita set codigo_medico = ? where codigo_receita = ?";
            String rem = "update receitas_medicamentos set codigo_medicamento = ? where codigo_receita = ?";
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(sql); 
            this.stmt.setString(1, receita.getDescricao());
            this.stmt.setDate(2, receita.getData());
            this.stmt.setInt(3, receita.getCodigo());
            this.stmt.executeUpdate(); 
            
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, receita.getCpf());
            this.stmt.setInt(2, receita.getCodigo());
            this.stmt.executeUpdate();
            
            
            this.stmt = con.prepareStatement(med);
            this.stmt.setInt(1, receita.getCrm());
            this.stmt.setInt(2, receita.getCodigo());
            this.stmt.executeUpdate();
            
            this.stmt = con.prepareStatement(rem);
            this.stmt.setInt(1, receita.getMedicamento());
            this.stmt.setInt(2, receita.getCodigo());
            this.stmt.executeUpdate();
            
        }catch (SQLException e){ 
            throw new ReceitaDAOException(e);
        }finally{
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } 
    }
    
    
    
    public String ultimoRegistro() throws ReceitaDAOException, SQLException{
        
        try{
            this.con = getConexao();
            this.stmt = con.prepareStatement("SELECT last_value from receitas_codigo_receita_seq");
            this.rs = this.stmt.executeQuery();
            
            if(! rs.next() ) 
                throw new ReceitaDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new ReceitaDAOException(e);
        }finally{
            if(this.rs != null) rs.close();
            if(this.stmt != null) stmt.close();
            if (this.con != null) con.close();
        }
        
        
    }
    
    public List retornarTodos(){
        
        List receita = new ArrayList();
        ReceitaBean receitaBean = null;
            
        try{
            
            String query = "select r.codigo_receita, r.descricao_receita, r.data,pe.nome_pessoa,pm.nome_pessoa,m.bula_medicamento from receitas"
                    +" as r inner join paciente_receita as pc on pc.codigo_receita = r.codigo_receita inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_receita as mc on mc.codigo_receita = r.codigo_receita inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa = me.codigo_pessoa"
                    +" inner join receitas_medicamentos as rm on rm.codigo_receita = r.codigo_receita inner join medicamentos as m on"
                    +" m.codigo_medicamento = rm.codigo_medicamento ";
            
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                receitaBean = new ReceitaBean();
                receitaBean.setCodigo(rs.getInt(1));
                receitaBean.setDescricao(rs.getString(2));
                receitaBean.setData(rs.getDate(3));
                receitaBean.setPaciente(rs.getString(4));
                receitaBean.setMedico(rs.getString(5));
                receitaBean.setRemedio(rs.getString(6));
                
                receita.add(receitaBean);
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
        return receita;
    }
    
    public List receitaPaciente(int codigo) throws SQLException{
        List <ReceitaBean> list = new ArrayList<ReceitaBean>(); 
        
        try{
            String query = "select r.codigo_receita, pm.nome_pessoa, pe.nome_pessoa, r.data, r.descricao_receita,m.bula_medicamento from receitas"
                    +" as r inner join paciente_receita as pc on pc.codigo_receita = r.codigo_receita inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_receita as mc on mc.codigo_receita = r.codigo_receita inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa ="
                    +"me.codigo_pessoa inner join receitas_medicamentos as rm on rm.codigo_receita = r.codigo_receita inner join medicamentos as m on"
                    +" m.codigo_medicamento = rm.codigo_medicamento and pe.codigo_pessoa = ?";
            
            this.con = getConexao();
            this.stmt = this.con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                list.add(new ReceitaBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6)));
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
     public List codigoPaciente(int codigo) throws SQLException, ReceitaDAOException{
        List pessoas = new ArrayList();
        int paciente = 0;
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_paciente from paciente_receita where codigo_receita = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                paciente = rs.getInt(1); 
                pessoas.add(paciente);
                
            }
            
                      
        }catch(SQLException e){
            throw new ReceitaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     
     public List codigoMedico(int codigo) throws SQLException, ReceitaDAOException{
        List pessoas = new ArrayList();
        int medico = 0;
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_medico from medico_receita where codigo_receita = ?");
            
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            
            
            if(rs.next()){
                              
                medico = rs.getInt(1); 
                pessoas.add(medico);
                
            }
            
                      
        }catch(SQLException e){
            throw new ReceitaDAOException(e);
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return pessoas;
    }
     
     public ReceitaBean retornarPeloCodigo(Integer codigo){
        
        ReceitaBean receitaBean = null;
            
        try{    
            String query = "SELECT r.Codigo_receita, r.Descricao_receita,r.data,m.bula_medicamento,m.codigo_medicamento FROM receitas as r"
                    +" inner join receitas_medicamentos as rm on rm.codigo_receita = r.codigo_receita inner join"
                    +" medicamentos as m on m.codigo_medicamento = rm.codigo_medicamento where r.Codigo_receita = ?";
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                receitaBean = new ReceitaBean();
                receitaBean.setCodigo(rs.getInt(1));
                receitaBean.setDescricao(rs.getString(2));
                receitaBean.setData(rs.getDate(3));
                receitaBean.setRemedio(rs.getString(4));
                receitaBean.setMedicamento(rs.getInt(5));
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
        return receitaBean;
    }
     public List medicamentos() throws SQLException{
        List remedios = new ArrayList();
        
        try{
            this.con = getConexao();
            this.stmt = this.con.prepareStatement("select codigo_medicamento, bula_medicamento from medicamentos");
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                MedicamentoBean medicamento = new MedicamentoBean();
        
                medicamento.setCodigo(rs.getInt(1));
                medicamento.setMedicamento(rs.getString(2));
                remedios.add(medicamento);                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
           if (this.rs != null) rs.close();
           if(this.stmt != null) stmt.close();
           if(this.con != null) con.close();
        }
        return remedios;
        
    }
     
      public List retornarReceitasMedico(int codigo){
        
        List receita = new ArrayList();
        ReceitaBean receitaBean = null;
            
        try{
            
            String query = "select r.codigo_receita, r.descricao_receita, r.data,pe.nome_pessoa,pm.nome_pessoa,m.bula_medicamento from receitas"
                    +" as r inner join paciente_receita as pc on pc.codigo_receita = r.codigo_receita inner join pacientes"
                    +" as pa on pa.codigo_paciente = pc.codigo_paciente inner join pessoas as pe on pe.codigo_pessoa ="
                    +"pa.codigo_pessoa inner join medico_receita as mc on mc.codigo_receita = r.codigo_receita inner join"
                    +" medicos as me on me.codigo_medico = mc.codigo_medico inner join pessoas as pm on pm.codigo_pessoa = me.codigo_pessoa"
                    +" inner join receitas_medicamentos as rm on rm.codigo_receita = r.codigo_receita inner join medicamentos as m on"
                    +" m.codigo_medicamento = rm.codigo_medicamento and pm.codigo_pessoa = ?";
            
            
            this.con = getConexao();
            this.stmt = con.prepareStatement(query);
            this.stmt.setInt(1, codigo);
            this.rs = this.stmt.executeQuery();
            
            while(rs.next()){
                receitaBean = new ReceitaBean();
                receitaBean.setCodigo(rs.getInt(1));
                receitaBean.setDescricao(rs.getString(2));
                receitaBean.setData(rs.getDate(3));
                receitaBean.setPaciente(rs.getString(4));
                receitaBean.setMedico(rs.getString(5));
                receitaBean.setRemedio(rs.getString(6));
                
                receita.add(receitaBean);
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
        return receita;
    }
    
}
