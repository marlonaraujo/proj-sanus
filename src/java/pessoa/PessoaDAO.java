/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factory.ConexaoFactory;
import java.util.Date;
import dao.*;

/**
 *
 * @author emilianoeloi
 */
public class PessoaDAO extends GenericDAO implements InterfacePessoaDAO {
    
    public PessoaDAO(){
        super();
    }
    
    private void obterProximoCodigo(PessoaBean pessoa) throws SQLException{
        try{
            super.setSql("SELECT NEXTVAL('pessoas_codigo_pessoa_seq') AS codigo");
            super.executarSelect();
            
            while(super.getResultSet().next()){
                pessoa.setCodigo(super.getResultSet().getInt(1));
            }
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na recuperacao do proximo codigo"+e);
        }finally{
            super.fecharConexao();
        }
    }

    public void cadastrar(PessoaBean pessoa) throws SQLException{
        
        if(pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        this.obterProximoCodigo(pessoa);
        
        try{
            String sql = "INSERT INTO pessoas (Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, Data_Nascimento_Pessoa, Senha_Pessoa"
                         +",Status_Pessoa) VALUES (?,?,?,?,?,?,MD5('senha_padrao'),2)";
            
            super.setSql(sql);

            super.setParametro(pessoa.getCodigo());
            super.setParametro(pessoa.getNome());
            super.setParametro(pessoa.getCpf());
            super.setParametro(pessoa.getEmail());
            super.setParametro(pessoa.getRg());
            super.setParametro(pessoa.getDataNascimento());
            //super.setParametro(pessoa.getSenha());
           
            super.executarInsert();
                       
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }finally{
            super.fecharConexao();
        }
    }
    
    public void alterar(PessoaBean pessoa) throws SQLException{
        
        if(pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
               
        try{
            
            super.setSql("UPDATE pessoas SET nome_pessoa = ?, cpf_pessoa = ?, email_pessoa = ?"
                    + ", rg_pessoa = ?, data_nascimento_pessoa = ?, status_pessoa = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(pessoa.getNome());
            super.setParametro(pessoa.getCpf());
            super.setParametro(pessoa.getEmail());
            super.setParametro(pessoa.getRg());
            super.setParametro(pessoa.getDataNascimento());
            super.setParametro(pessoa.getStatus());
            super.setParametro(pessoa.getCodigo());
            super.executarUpdate();
            
         
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
    public void definirSenha(PessoaBean pessoa) throws SQLException{
        
        if(pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            
            super.setSql("UPDATE pessoas SET Senha_Pessoa = MD5(?)  WHERE Codigo_Pessoa = ?");
            
            super.setParametro(pessoa.getSenha());
            super.setParametro(pessoa.getCodigo());
            
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
    public void alterarStatus(PessoaBean pessoa) throws SQLException{
        
        if(pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            
            super.setSql("UPDATE pessoas SET Status_Pessoa = ?  WHERE Codigo_Pessoa = ?");
            
            super.setParametro(pessoa.getStatus());
            super.setParametro(pessoa.getCodigo());
            
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
    public void excluir(PessoaBean pessoa) throws SQLException{
        
        if(pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            super.setSql("DELETE FROM pessoas WHERE Codigo_Pessoa = ?");

            super.setParametro(pessoa.getCodigo());
            
            super.executarDelete();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na exclusão"+e);
        }
    }
    
    @Override
    public boolean existePeloCodigo(Integer codigo) throws SQLException{
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Pessoa FROM pessoas WHERE Codigo_Pessoa = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            while(super.getResultSet().next()){
                return true;
            }
            
        }catch(SQLException e){
            throw new SQLException("Houve uma falha na Recuperação "+e);
        }finally{
            super.fecharConexao();
        }
        return false;
    }
    
    public List retornarTodos() throws SQLException{
        
        List pessoas = new ArrayList();
            
        try{    
            super.setSql("SELECT Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, "
                    + "Data_Nascimento_Pessoa, Senha_Pessoa FROM pessoas where Status_Pessoa = 2");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEmail(rs.getString(4));
                pessoa.setRg(rs.getString(5));
                pessoa.setDataNascimento(rs.getDate(6));
                pessoa.setSenha(rs.getString(7));
                pessoas.add(pessoa);
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recupear uma lista de Pessoas "+e);
        }finally{
            super.fecharConexao();
        }
        return pessoas;
    }
    
    public PessoaBean retornarPeloCodigo(Integer codigo) throws SQLException{
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        PessoaBean pessoa = null;
            
        try{    
            super.setSql("SELECT Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, "
                    + "Data_Nascimento_Pessoa, Senha_Pessoa FROM pessoas WHERE Codigo_Pessoa = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                pessoa = new PessoaBean();
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEmail(rs.getString(4));
                pessoa.setRg(rs.getString(5));
                pessoa.setDataNascimento(rs.getDate(6));
                pessoa.setSenha(rs.getString(7));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }finally{
            super.fecharConexao();
        }
        return pessoa;
    }
    
    public PessoaBean procurarPeloEmail(String email) throws SQLException{
        if(email == "")
            throw new GenericDAOException("O valor precisa ser preenchido.");        
        
        PessoaBean pessoa = null;
            
        try{    
            super.setSql("SELECT Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, "
                    + "Data_Nascimento_Pessoa, Senha_Pessoa FROM pessoas WHERE Email_Pessoa = ?");
            super.setParametro(email);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                pessoa = new PessoaBean();
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEmail(rs.getString(4));
                pessoa.setRg(rs.getString(5));
                pessoa.setDataNascimento(rs.getDate(6));
                pessoa.setSenha(rs.getString(7));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }finally{
            super.fecharConexao();
        }
        return pessoa;
    }
    
    public PessoaBean procurarPeloCpf(PessoaBean pessoa) throws SQLException{
        if(pessoa.getCpf().equals(""))
            throw new GenericDAOException("O valor precisa ser preenchido.");        
        
        try{    
            super.setSql("SELECT Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, "
                    + "Data_Nascimento_Pessoa, Senha_Pessoa FROM pessoas WHERE Cpf_Pessoa = ?");
            super.setParametro(pessoa.getCpf());
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                pessoa = new PessoaBean();
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEmail(rs.getString(4));
                pessoa.setRg(rs.getString(5));
                pessoa.setDataNascimento(rs.getDate(6));
                pessoa.setSenha(rs.getString(7));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }finally{
            super.fecharConexao();
        }
        return pessoa;
    }
    
    public PessoaBean recuperarPorUsuarioSenha(PessoaBean pessoa) throws SQLException{
        
            
        try{    
            super.setSql("SELECT Codigo_Pessoa, Nome_Pessoa, Cpf_Pessoa, Email_Pessoa, RG_Pessoa, "
                    + "Data_Nascimento_Pessoa, Senha_Pessoa, Status_Pessoa FROM pessoas "
                    + "WHERE Email_Pessoa = ? AND Senha_Pessoa = MD5(?) ");

            super.setParametro(pessoa.getEmail());
            super.setParametro(pessoa.getSenha());
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                pessoa = new PessoaBean();
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setCpf(rs.getString(3));
                pessoa.setEmail(rs.getString(4));
                pessoa.setRg(rs.getString(5));
                pessoa.setDataNascimento(rs.getDate(6));
                pessoa.setSenha(rs.getString(7));
                pessoa.setStatus(rs.getInt(8));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return pessoa;
    }
    
    public PessoaBean pessoaDados(int codigo) throws SQLException,PessoaDAOException{
        
        PessoaBean pessoa = null;
        
        try{
            super.setSql("select * from pessoas where codigo_pessoa = ?");
            
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            
            while(rs.next()){
                
                 pessoa = new PessoaBean();
                 pessoa.setCodigo(rs.getInt(1));
                 pessoa.setNome(rs.getString(2));
                 pessoa.setRg(rs.getString(3));
                 pessoa.setCpf(rs.getString(4));
                 pessoa.setDataNascimento(rs.getDate(5));
                 pessoa.setEmail(rs.getString(6));
                 pessoa.setSenha(rs.getString(7));
                 
            }
        
            return pessoa;
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Erro ao executar ação "+e);
        }
        
        
    }
    
    
    public List pessoaBusca(String nome) throws SQLException,GenericDAOException{
        
        List <PessoaBean> list = new ArrayList<PessoaBean>(); 
         
        // ~* - ignora letras maiusculas ou minusculas
        String sql = "select * from pessoas inner join pacientes on "
                + "pessoas.codigo_pessoa = pacientes.codigo_pessoa "
                + "where nome_pessoa ~* '"+nome+"'";
        
        try{
            super.setSql(sql);
            
            
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            
            while(rs.next()){
                
                list.add(new PessoaBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7)));
                                  
            }
        
            return list;
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Erro ao executar ação "+e);
        }
        
        
    }
    public List pessoas() throws SQLException{
        List pessoas = new ArrayList();
               
        try{
            
            super.setSql("select * from pessoas where not exists(select * from medicos where medicos.codigo_pessoa"
                    +" = pessoas.codigo_pessoa)and not exists (select * from pacientes where pacientes.codigo_pessoa"
                    +" = pessoas.codigo_pessoa) and status_pessoa = 2");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            
            while(rs.next()){
                PessoaBean pessoa = new PessoaBean();
        
                pessoa.setCodigo(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setRg(rs.getString(3));
                pessoa.setCpf(rs.getString(4));
                pessoa.setDataNascimento(rs.getDate(5));
                pessoa.setEmail(rs.getString(6));
                pessoa.setSenha(rs.getString(7));
                pessoa.setStatus(rs.getInt(9));
                pessoas.add(pessoa);                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pessoas;
        
    }
    
}
