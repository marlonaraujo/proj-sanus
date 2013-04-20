/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factory.ConexaoFactory;
import java.util.Date;
import dao.*;
import pessoa.*;

/**
 *
 * @author emilianoeloi
 */
public class PacienteDAO extends GenericDAO {
    
    public PacienteDAO(){
        super();
    }
    
    private void obterProximoCodigo(PacienteBean paciente) throws SQLException{
        try{
            super.setSql("SELECT NEXTVAL('pacientes_codigo_paciente_seq') AS codigo");
            super.executarSelect();
            
            while(super.getResultSet().next()){
                paciente.setCodigo(super.getResultSet().getInt(1));
            }
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na recuperacao do proximo codigo"+e);
        }
    }

    public void cadastrar(PacienteBean paciente) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        this.obterProximoCodigo(paciente);
        
        String query = "update pessoas set rg_pessoa = ?,cpf_pessoa = ?,data_nascimento_pessoa = ? where codigo_pessoa = ?";
        
        try{
            super.setSql("INSERT INTO pacientes (Codigo_Paciente, Codigo_Pessoa) "
                          +"VALUES (?, ?)");

            super.setParametro(paciente.getCodigo());
            super.setParametro(paciente.getCodigoPessoa());
            
            super.executarInsert();
            
            super.setSql(query);
            super.setParametro(paciente.getRg());
            super.setParametro(paciente.getCpf());
            super.setParametro(paciente.getDataNascimento());
            super.setParametro(paciente.getCodigoPessoa());
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    public void salvar(PacienteBean paciente) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        this.obterProximoCodigo(paciente);
        
        try{
            super.setSql("INSERT INTO pacientes (Codigo_Paciente, Codigo_Pessoa) "
                          +"VALUES (?, ?)");

            super.setParametro(paciente.getCodigo());
            super.setParametro(paciente.getCodigoPessoa());
                      
            super.executarInsert();
                                 
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    
    public void alterar(PacienteBean paciente) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            
            super.setSql("UPDATE pacientes SET codigo_paciente = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getCodigo());
            super.setParametro(paciente.getCodigoPessoa());
            super.executarUpdate();
            
            
            super.setSql("UPDATE pessoas SET nome_pessoa = ?, cpf_pessoa = ?, email_pessoa = ?"
                    + ", rg_pessoa = ?, data_nascimento_pessoa = ?, status_pessoa = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getNome());
            super.setParametro(paciente.getCpf());
            super.setParametro(paciente.getEmail());
            super.setParametro(paciente.getRg());
            super.setParametro(paciente.getDataNascimento());
            super.setParametro(paciente.getStatus());
            super.setParametro(paciente.getCodigoPessoa());
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
    public void excluir(PacienteBean paciente) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            super.setSql("DELETE FROM pacientes WHERE Codigo_Paciente = ?");

            super.setParametro(paciente.getCodigo());
            
            super.executarDelete();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na exclusão"+e);
        }
    }
    
    public boolean existePeloCodigo(PacienteBean paciente) throws SQLException{
        Integer codigo = paciente.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Paciente FROM pacientes WHERE Codigo_Paciente = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            while(super.getResultSet().next()){
                return true;
            }
            
        }catch(SQLException e){
            throw new SQLException("Houve uma falha na Recuperação "+e);
        }
        return false;
    }
    
    public boolean existePeloCodigoPessoa(PessoaBean pessoa) throws SQLException{
        Integer codigo = pessoa.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Paciente FROM pacientes WHERE Codigo_Pessoa = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            while(super.getResultSet().next()){
                return true;
            }
            
        }catch(SQLException e){
            throw new SQLException("Houve uma falha na Recuperação "+e);
        }
        return false;
    }
    
    public List retornarTodos() throws SQLException{
        
        List pacientes = new ArrayList();
            
        try{    
        super.setSql("SELECT pa.Codigo_Paciente, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, pacientes pa WHERE p.Codigo_Pessoa = pa.Codigo_Pessoa"
                +" and p.Status_Pessoa = 2");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                PacienteBean paciente = new PacienteBean();
                paciente.setCodigo(rs.getInt(1));
                paciente.setCodigoPessoa(rs.getInt(2));
                paciente.setNome(rs.getString(3));
                paciente.setCpf(rs.getString(4));
                paciente.setEmail(rs.getString(5));
                paciente.setRg(rs.getString(6));
                paciente.setDataNascimento(rs.getDate(7));
                paciente.setSenha(rs.getString(8));
                pacientes.add(paciente);
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recupear uma lista de Pessoas "+e);
        }
        return pacientes;
    }
    
    public PacienteBean retornarPeloCodigo(PacienteBean paciente) throws SQLException{
        Integer codigo = paciente.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{    
            super.setSql("SELECT pa.Codigo_Paciente, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, pacientes pa WHERE p.Codigo_Pessoa = pa.Codigo_Pessoa AND Codigo_Paciente = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                paciente = new PacienteBean();
                paciente.setCodigo(rs.getInt(1));
                paciente.setCodigoPessoa(rs.getInt(2));
                paciente.setNome(rs.getString(3));
                paciente.setCpf(rs.getString(4));
                paciente.setEmail(rs.getString(5));
                paciente.setRg(rs.getString(6));
                paciente.setDataNascimento(rs.getDate(7));
                paciente.setSenha(rs.getString(8));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return paciente;
    }
    
    public PacienteBean procurarPeloEmail(PacienteBean paciente) throws SQLException{
        String email = paciente.getEmail();
        if(email.equals(""))
            throw new GenericDAOException("O valor precisa ser preenchido.");        
        
        try{    
            super.setSql("SELECT pa.Codigo_Paciente, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, pacientes pa WHERE p.Codigo_Pessoa = pa.Codigo_Pessoa AND Email_Pessoa = ?");
            super.setParametro(email);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                paciente = new PacienteBean();
                paciente.setCodigo(rs.getInt(1));
                paciente.setCodigoPessoa(rs.getInt(2));
                paciente.setNome(rs.getString(3));
                paciente.setCpf(rs.getString(4));
                paciente.setEmail(rs.getString(5));
                paciente.setRg(rs.getString(6));
                paciente.setDataNascimento(rs.getDate(7));
                paciente.setSenha(rs.getString(8));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return paciente;
    }
    
     public void alterarDados(PacienteBean paciente, PessoaBean pessoa) throws SQLException{
        
        if(paciente == null || pessoa == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
                    
            super.setSql("UPDATE pacientes SET codigo_paciente = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getCodigo());
            super.setParametro(pessoa.getCodigo());
            super.executarUpdate();
            
            
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
     
    
      public void alterarDadosSenha(PacienteBean paciente, PessoaBean pessoa) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
                    
            super.setSql("UPDATE pacientes SET codigo_paciente = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getCodigo());
            super.setParametro(pessoa.getCodigo());
            super.executarUpdate();
            
            
          
                super.setSql("UPDATE pessoas SET nome_pessoa = ?, cpf_pessoa = ?, email_pessoa = ?"
                    + ", rg_pessoa = ?, data_nascimento_pessoa = ?, senha_pessoa = MD5(?), status_pessoa = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(pessoa.getNome());
            super.setParametro(pessoa.getCpf());
            super.setParametro(pessoa.getEmail());
            super.setParametro(pessoa.getRg());
            super.setParametro(pessoa.getDataNascimento());
            super.setParametro(pessoa.getSenha());
            super.setParametro(pessoa.getStatus());
            super.setParametro(pessoa.getCodigo());
            super.executarUpdate();
            
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Falha na alteração"+e);
        }
    }
    public String ultimoRegistroPessoa() throws PacienteDAOException, SQLException{
        
        try{
            
            super.setSql("SELECT last_value from pessoas_codigo_pessoa_seq");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            if(! rs.next() ) 
                throw new PacienteDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new PacienteDAOException(e);
        }
        
    }
    public String ultimoRegistroPaciente() throws PacienteDAOException, SQLException{
        
        try{
            
            super.setSql("SELECT last_value from pacientes_codigo_paciente_seq");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            if(! rs.next() ) 
                throw new PacienteDAOException("Não  foi encontrado nenhum registro"); 

            int ultimo = rs.getInt(1);
            if(ultimo > 1)
            ultimo = ultimo + 1;
            String codigo = String.valueOf(ultimo);
            
            return codigo;
            
        }catch(SQLException e){
            throw new PacienteDAOException(e);
        }
        
    }
    public void preCadastro(PacienteBean paciente, int codigo) throws SQLException, PacienteDAOException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        String query = "insert into pessoas (nome_pessoa,rg_pessoa)values(?,?);";
        
        try{
            
            String strPessoa = this.ultimoRegistroPessoa();
            int codigoPessoa = (Integer.parseInt(strPessoa));
            
            String strPaciente = this.ultimoRegistroPaciente();
            int codigoPaciente = (Integer.parseInt(strPaciente));
            
            String cod = String.valueOf(codigo);
            
            super.setSql(query);
            super.setParametro(paciente.getNome());
            super.setParametro(cod);
            super.executarInsert();
            
            
            super.setSql("INSERT INTO pacientes (Codigo_Pessoa) "
                          +"VALUES (?)");

            //super.setParametro(codigoPaciente);
            super.setParametro(codigoPessoa);
            
            super.executarInsert();
            
            
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    
    public List consultaPreCadastro() throws SQLException, PacienteDAOException{
        
        List pacientes = new ArrayList();
            
        try{    
        super.setSql("SELECT pa.Codigo_Paciente, p.Codigo_Pessoa, p.Nome_Pessoa,p.Data_Cadastro_Pessoa FROM pessoas p, pacientes pa "
                    + "WHERE p.Codigo_Pessoa = pa.Codigo_Pessoa and p.Status_Pessoa = 1");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                PacienteBean paciente = new PacienteBean();
                paciente.setCodigo(rs.getInt(1));
                paciente.setCodigoPessoa(rs.getInt(2));
                paciente.setNome(rs.getString(3));
                paciente.setDataNascimento(rs.getDate(4));
                pacientes.add(paciente);
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recupear uma lista de Pessoas "+e);
        }
        return pacientes;
    }
    public PacienteBean preCadastroObterUm(PacienteBean paciente) throws SQLException{
        Integer codigo = paciente.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{    
            super.setSql("SELECT pa.Codigo_Paciente, p.Codigo_Pessoa, p.Nome_Pessoa,p.Data_Cadastro_Pessoa FROM "
                    + "pessoas p, pacientes pa WHERE p.Codigo_Pessoa = pa.Codigo_Pessoa AND Codigo_Paciente = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                paciente = new PacienteBean();
                paciente.setCodigo(rs.getInt(1));
                paciente.setCodigoPessoa(rs.getInt(2));
                paciente.setNome(rs.getString(3));
                paciente.setDataNascimento(rs.getDate(4));
              
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return paciente;
    }
    public void excluirPreCadastro(int codPaciente,int codPessoa) throws SQLException{
        
               
        try{
            super.setSql("DELETE FROM pacientes WHERE Codigo_Paciente = ?");

            super.setParametro(codPaciente);
            
            super.executarDelete();
            
            super.setSql("DELETE FROM pessoas WHERE Codigo_Pessoa = ?");

            super.setParametro(codPessoa);
            
            super.executarDelete();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na exclusão"+e);
        }
    }
    public void alterarPreCadastro(PacienteBean paciente) throws SQLException{
        
        if(paciente == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            
            super.setSql("UPDATE pacientes SET codigo_paciente = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getCodigo());
            super.setParametro(paciente.getCodigoPessoa());
            super.executarUpdate();
            
            
            super.setSql("UPDATE pessoas SET nome_pessoa = ?, data_cadastro_pessoa = ?, Status_pessoa = ?"
                    + "WHERE codigo_pessoa = ?");
            
            super.setParametro(paciente.getNome());
            super.setParametro(paciente.getDataNascimento());
            super.setParametro(1);
            super.setParametro(paciente.getCodigoPessoa());
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
}
