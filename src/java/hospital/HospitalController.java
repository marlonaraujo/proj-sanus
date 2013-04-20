/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import medico.MedicoBean;
import paciente.PacienteBean;
import pessoa.PessoaBean;

/**
 *
 * @author emilianoeloi
 */
@WebServlet(name = "HospitalController", urlPatterns = {"/HospitalController"})
public class HospitalController extends HttpServlet{ 

    protected void service (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HospitalDAO dao = new HospitalDAO();
        HospitalBean hospital = new HospitalBean();
        HttpSession sessao = request.getSession();
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        
         
        String acao = request.getParameter("acao");
        
        if (acao == null){
            acao = "listar";
        }
        
                
        if(acao != null){
           if (request.getParameter("codigo") != null && request.getParameter("codigo") !="" ){
               hospital.setCodigo(Integer.parseInt(request.getParameter("codigo"))); 
           }
           
           hospital.setNome(request.getParameter("nome"));

        }
        
       try {
           RequestDispatcher rd = null;
           if (acao.equalsIgnoreCase("cadastrar")){
               if(adm != null){
                    dao.cadastrar(hospital);
                    request.setAttribute("hospitalSelecionado", hospital);
                    rd = request.getRequestDispatcher("hospitalForm.jsp");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("listar")) {
               if(adm != null || medico != null){
                    List hospitalList = dao.retornarTodos();
                    request.setAttribute("hospitalList", hospitalList);
                    rd = request.getRequestDispatcher("hospitalLista.jsp");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if (acao.equalsIgnoreCase("excluir")){
               if(adm != null){
                    dao.excluir(hospital);
                    rd = request.getRequestDispatcher("HospitalController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if (acao.equalsIgnoreCase("obterum")){
               if(adm != null){
                    hospital = dao.retornarPeloCodigo(hospital.getCodigo());
                    request.setAttribute("hospitalSelecionado", hospital);
                    rd = request.getRequestDispatcher("HospitalController?acao=formulario");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("Formulario")) {
               rd = request.getRequestDispatcher("hospitalForm.jsp");
           
           } else if (acao.equalsIgnoreCase("alterar")){
               if(adm != null){
                    dao.alterar(hospital);
                    request.setAttribute("hospitalSelecionado", hospital);
                    rd = request.getRequestDispatcher("HospitalController?acao=formulario");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if (acao.equalsIgnoreCase("combo")){
               String selecionado = request.getParameter("codigo_hospital");
               List hospitalList = dao.retornarTodos();
               request.setAttribute("hospitalList", hospitalList);
               request.setAttribute("hospitalSelecionado", selecionado);
               rd = request.getRequestDispatcher("comboHospital.jsp");
           
           }
           
           
           rd.forward(request, response);
        } catch (Exception e) {            
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
