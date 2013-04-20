/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;


import cirurgia.CirurgiaDAO;
import java.io.IOException;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;
import medico.MedicoBean;
import medico.MedicoDAO;
import paciente.PacienteBean;
import paciente.PacienteDAO;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "PessoaValida", urlPatterns = {"/PessoaValida"})
public class PessoaValida extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        /// sessao
        HttpSession sessao = request.getSession(true);
        
        /// Receber variavel de acao
        String acao = request.getParameter("acao");
        
        RequestDispatcher rd = null;
        
        PessoaBean pessoa = new PessoaBean();
        PessoaDAO dao = new PessoaDAO();
        MedicoDAO   daoMedico = new MedicoDAO();
        PacienteDAO   daoPaciente = new PacienteDAO();
        CirurgiaDAO daoCirurgia = new CirurgiaDAO();
        
        if( request.getParameter("codigo") != null &&
                request.getParameter("codigo") != "" )
                pessoa.setCodigo(Integer.parseInt(request.getParameter("codigo")));
            pessoa.setNome(request.getParameter("nome"));
            //pessoa.setCpf(request.getParameter("cpf"));
            //pessoa.setId(request.getParameter("id"));
            pessoa.setSenha(request.getParameter("senha"));
            pessoa.setEmail(request.getParameter("email"));
            //pessoa.setDataNascimento(convData.converteEmData(request.getParameter("data-nascimento")));
        
        try{
        
            if(acao.equalsIgnoreCase("autenticar")){
                
                    pessoa = dao.recuperarPorUsuarioSenha(pessoa);
                
                    if(pessoa != null && pessoa.getStatus()==2){
                        /// Verificar PErfis
                        ArrayList<String> perfis = new ArrayList<String>();
                        sessao.setMaxInactiveInterval(3600);

                        if(pessoa.getEmail().equalsIgnoreCase("adm@sanus.br")){
                            //String tipo = getServletContext().getInitParameter("administrador");
                            perfis.add("administrador");
                            String adm = pessoa.getEmail();
                            sessao.setAttribute("existeAdm", adm);
                        }    
                        if(daoMedico.existePeloCodigoPessoa(pessoa)){
                            //String tipo = getServletContext().getInitParameter("medico");
                            MedicoBean med = new MedicoBean();
                            med.setCodigoPessoa(pessoa.getCodigo());
                            sessao.setAttribute("existeMedico", med);
                            perfis.add("medico");
                            pessoa.setPerfis(perfis);
                        }
                        if(daoPaciente.existePeloCodigoPessoa(pessoa)){
                            //String tipo = getServletContext().getInitParameter("paciente");
                            PacienteBean pac = new PacienteBean();
                            pac.setCodigo(pessoa.getCodigo());
                            sessao.setAttribute("existePaciente", pac);
                            perfis.add("paciente");
                            pessoa.setPerfis(perfis);
                        }
                        sessao.setAttribute("pessoaLogada", pessoa);
                        List pacientesList = daoCirurgia.Buscapacientes(pessoa.getCodigo());
                        request.setAttribute("pacientesLista", pacientesList);
                                            
                    }
                    rd = request.getRequestDispatcher("principal.jsp"); 
                    
            }
        
        rd.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

