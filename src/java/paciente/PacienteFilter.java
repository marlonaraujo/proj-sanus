/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paciente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import medico.MedicoBean;
import pessoa.PessoaBean;

/**
 *
 * @author Administrador
 */
//@WebFilter(filterName = "PacienteFilter", urlPatterns = {"/*"}, servletNames = {"PessoaController", "ExameController"})
public class PacienteFilter implements Filter {

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
        //String med = filterConfig.getInitParameter("paciente");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession sessao = req.getSession();
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
       
        //if(paciente == null || pessoa == null || adm == null || medico == null){
        if(adm == null){
           if(medico == null){
                if(paciente == null){
                    if(pessoa == null){    
                        sessao.setAttribute("msg","Você não está logado no sistema!");
                        ((HttpServletResponse)res).sendRedirect("erro403.jsp");
                    }
                }
           }
       }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
