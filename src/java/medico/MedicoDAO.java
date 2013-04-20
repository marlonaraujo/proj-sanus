/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medico;

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
public class MedicoDAO extends GenericDAO {
    
    public MedicoDAO(){
        super();
    }
    
    private void obterProximoCodigo(MedicoBean medico) throws SQLException{
        try{
            super.setSql("SELECT NEXTVAL('medicos_codigo_medico_seq') AS codigo");
            super.executarSelect();
            
            while(super.getResultSet().next()){
                medico.setCodigo(super.getResultSet().getInt(1));
            }
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na recuperacao do proximo codigo"+e);
        }
    }

    public void cadastrar(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        this.obterProximoCodigo(medico);
        
        String query = "update pessoas set rg_pessoa = ?,cpf_pessoa = ?,data_nascimento_pessoa = ? where codigo_pessoa = ?";
        
        try{
            super.setSql("INSERT INTO medicos (Codigo_Medico, Codigo_Pessoa, Crm_Medico) "
                          +"VALUES (?, ?, ?)");

            super.setParametro(medico.getCodigo());
            super.setParametro(medico.getCodigoPessoa());
            super.setParametro(medico.getCrm());
            
            super.executarInsert();
            
            super.setSql(query);
            super.setParametro(medico.getRg());
            super.setParametro(medico.getCpf());
            super.setParametro(medico.getDataNascimento());
            super.setParametro(medico.getCodigoPessoa());
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    
    public void salvar(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        this.obterProximoCodigo(medico);
        
        try{
            super.setSql("INSERT INTO medicos (Codigo_Medico, Codigo_Pessoa, Crm_Medico) "
                          +"VALUES (?, ?, ?)");

            super.setParametro(medico.getCodigo());
            super.setParametro(medico.getCodigoPessoa());
            super.setParametro(medico.getCrm());
            
            super.executarInsert();
                                 
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    
    public void alterarHospitalMedico(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            super.setSql("UPDATE Medico_Hospital SET Codigo_Hospital = ? WHERE Codigo_Medico = ?");

            super.setParametro(medico.getCodigoHospital());    
            super.setParametro(medico.getCodigo());
            
            super.executarInsert();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    public void cadastrarHospitalMedico(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            super.setSql("INSERT INTO Medico_Hospital (Codigo_Medico, Codigo_Hospital) "
                          +"VALUES (?, ?)");

            super.setParametro(medico.getCodigo());
            super.setParametro(medico.getCodigoHospital());
            
            super.executarInsert();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha no cadastro"+e);
        }
    }
    public boolean existeHospitalMedico(MedicoBean medico) throws SQLException{
        Integer codigo = medico.getCodigo();
        Integer codigoHospital = medico.getCodigoHospital();
        if(codigo == null || codigo == 0 || codigoHospital == null || codigoHospital == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Medico FROM Medico_Hospital WHERE Codigo_Medico = ? AND Codigo_Hospital = ?");
            super.setParametro(codigo);
            super.setParametro(codigoHospital);
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
    
    public void alterar(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            
            super.setSql("UPDATE medicos SET Crm_Medico = ? WHERE Codigo_Medico = ?");
            super.setParametro(medico.getCrm());
            super.setParametro(medico.getCodigo());
            
            super.executarUpdate();
            
            super.setSql("UPDATE pessoas SET nome_pessoa = ?, cpf_pessoa = ?, email_pessoa = ?"
                    + ", rg_pessoa = ?, data_nascimento_pessoa = ?, status_pessoa = ? WHERE codigo_pessoa = ?");
            
            super.setParametro(medico.getNome());
            super.setParametro(medico.getCpf());
            super.setParametro(medico.getEmail());
            super.setParametro(medico.getRg());
            super.setParametro(medico.getDataNascimento());
            super.setParametro(medico.getStatus());
            super.setParametro(medico.getCodigoPessoa());
            super.executarUpdate();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na alteração"+e);
        }
    }
    
    public void excluir(MedicoBean medico) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
            super.setSql("DELETE FROM medicos WHERE Codigo_Medico = ?");

            super.setParametro(medico.getCodigo());
            
            super.executarDelete();
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha na exclusão"+e);
        }
    }
    
    public int existePeloCodigo(MedicoBean medico) throws SQLException{
        Integer codigo = medico.getCodigoPessoa();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Medico FROM medicos WHERE Codigo_Pessoa = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            
            if(rs.next()){
                medico.setCodigo(rs.getInt(1));
            }
            
        }catch(SQLException e){
            throw new SQLException("Houve uma falha na Recuperação "+e);
        }
        return medico.getCodigo();
    }
    
    public boolean existePeloCodigoPessoa(PessoaBean pessoa) throws SQLException{
        Integer codigo = pessoa.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{
            super.setSql("SELECT Codigo_Medico FROM medicos WHERE Codigo_Pessoa = ?");
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
        
        List medicos = new ArrayList();
            
        try{    
        super.setSql("SELECT m.Codigo_Medico, m.Crm_Medico, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, medicos m WHERE p.Codigo_Pessoa = m.Codigo_Pessoa");
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                MedicoBean medico = new MedicoBean();
                medico.setCodigo(rs.getInt(1));
                medico.setCrm(rs.getString(2));
                medico.setCodigoPessoa(rs.getInt(3));
                medico.setNome(rs.getString(4));
                medico.setCpf(rs.getString(5));
                medico.setEmail(rs.getString(6));
                medico.setRg(rs.getString(7));
                medico.setDataNascimento(rs.getDate(8));
                medico.setSenha(rs.getString(9));
                medicos.add(medico);
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recupear uma lista de Pessoas "+e);
        }
        return medicos;
    }
    
    public MedicoBean retornarPeloCodigo(MedicoBean medico) throws SQLException{
        Integer codigo = medico.getCodigo();
        if(codigo == null || codigo == 0)
            throw new GenericDAOException("O Código precisa ser válido.");
        
        try{    
            super.setSql("SELECT m.Codigo_Medico, m.Crm_Medico, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, medicos m WHERE p.Codigo_Pessoa = m.Codigo_Pessoa AND Codigo_Medico = ?");
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                medico = new MedicoBean();
                medico.setCodigo(rs.getInt(1));
                medico.setCrm(rs.getString(2));
                medico.setCodigoPessoa(rs.getInt(3));
                medico.setNome(rs.getString(4));
                medico.setCpf(rs.getString(5));
                medico.setEmail(rs.getString(6));
                medico.setRg(rs.getString(7));
                medico.setDataNascimento(rs.getDate(8));
                medico.setSenha(rs.getString(9));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return medico;
    }
    
    public MedicoBean procurarPeloEmail(MedicoBean medico) throws SQLException{
        String email = medico.getEmail();
        if(email.equals(""))
            throw new GenericDAOException("O valor precisa ser preenchido.");        
        
        try{    
            super.setSql("SELECT m.Codigo_Medico, m.Crm_Medico, p.Codigo_Pessoa, p.Nome_Pessoa, p.Cpf_Pessoa, p.Email_Pessoa, p.Rg_Pessoa, "
                    + "p.Data_Nascimento_Pessoa, p.Senha_Pessoa FROM pessoas p, medicos m WHERE p.Codigo_Pessoa = m.Codigo_Pessoa AND Email_Pessoa = ?");
            super.setParametro(email);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            while(rs.next()){
                medico = new MedicoBean();
                medico.setCodigo(rs.getInt(1));
                medico.setCrm(rs.getString(2));
                medico.setCodigoPessoa(rs.getInt(3));
                medico.setNome(rs.getString(4));
                medico.setCpf(rs.getString(5));
                medico.setEmail(rs.getString(6));
                medico.setRg(rs.getString(7));
                medico.setDataNascimento(rs.getDate(8));
                medico.setSenha(rs.getString(9));
            }
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Houve uma falha ao tentar recuperar "+e);
        }
        return medico;
    }
    
     public void alterarDados(MedicoBean medico, PessoaBean pessoa) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
                    
            super.setSql("UPDATE medicos SET Crm_Medico = ? WHERE Codigo_Pessoa = ?");
            super.setParametro(medico.getCrm());
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
            throw new GenericDAOException("Falha na alteração"+e);
        }
    }
      public void alterarDadosSenha(MedicoBean medico, PessoaBean pessoa) throws SQLException{
        
        if(medico == null)
            throw new GenericDAOException("O objeto passado não pode ser nulo.");
        
        try{
                    
            super.setSql("UPDATE medicos SET Crm_Medico = ? WHERE Codigo_Pessoa = ?");
            super.setParametro(medico.getCrm());
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
     
    public MedicoBean medicoCrm(int codigo) throws SQLException{
        
        MedicoBean medico = null;
       
        String sql = "select crm_medico from medicos where codigo_pessoa = ?";
                
        try{
            super.setSql(sql);
            
            super.setParametro(codigo);
            super.executarSelect();
            
            ResultSet rs = super.getResultSet();
            
            if(rs.next()){
                 medico = new MedicoBean();
                 medico.setCrm(rs.getString(1));
                 
                 
            }
        
            return medico;
            
        }catch(GenericDAOException e){
            throw new GenericDAOException("Erro ao executar ação "+e);
        }
        
    } 
    
    
}
