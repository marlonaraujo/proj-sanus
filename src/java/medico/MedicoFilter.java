package medico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import medico.MedicoBean;
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paciente.PacienteBean;
import pessoa.PessoaBean;
 
//@WebFilter(filterName = "MedicoFilter", urlPatterns = {"/*"})
public class MedicoFilter implements Filter {
    

    public void init(FilterConfig config)throws ServletException { 
      
        //String med = config.getInitParameter("tipo");
      
    }
        
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException,ServletException{
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession sessao = req.getSession();
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        String adm = (String) sessao.getAttribute("existeAdm");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");      
        //if((medico == null || pessoa == null)||(adm != null)){
        if(adm == null){
           if(medico == null){
                if(paciente != null || pessoa == null){
                    sessao.setAttribute("msg","Você não está logado no sistema!");
                    ((HttpServletResponse)res).sendRedirect("erro403.jsp");
                    
                }
           }
       }
        
        chain.doFilter(request, response);
    }
    
    public void destroy(){
        
    }
 
}