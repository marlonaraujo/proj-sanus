/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import java.util.List;
import dao.*;
import java.sql.SQLException;


/**
 *
 * @author emilianoeloi
 */
public interface InterfacePessoaDAO {
    
    public void cadastrar(PessoaBean pessoaBean) throws SQLException;
    
    public void alterar(PessoaBean pessoaBean) throws SQLException;
    
    public void excluir(PessoaBean pessoaBean) throws SQLException;
    
    public boolean existePeloCodigo(Integer codigo) throws SQLException;
    
    public List retornarTodos() throws SQLException;
    
    public PessoaBean retornarPeloCodigo(Integer codigo) throws SQLException;
    
    public PessoaBean recuperarPorUsuarioSenha(PessoaBean pessoa) throws SQLException;
    
    public PessoaBean procurarPeloEmail(String email) throws SQLException;
    
}
