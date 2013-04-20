/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

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
import paciente.PacienteBean;

/**
 *
 * @author Administrador
 */
//@WebFilter(filterName = "AdmFilter", urlPatterns = {"/*"})
public class AdmFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession sessao = req.getSession();
        String adm = (String) sessao.getAttribute("existeAdm");
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        
        //if((adm == null || pessoa == null)|| (medico != null || paciente != null)){
        if(adm == null){
            if(medico != null || paciente != null || pessoa == null){
                sessao.setAttribute("msg","Você não está logado no sistema!");
                ((HttpServletResponse)res).sendRedirect("erro403.jsp");
                
                
            }
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }



}