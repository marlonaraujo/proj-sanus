/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package medicamentos;

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
 * @author vilmar
 */
@WebServlet(name = "MedicamentoController", urlPatterns = {"/MedicamentoController"})
public class MedicamentoController extends HttpServlet{ 

    protected void service (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicamentoDAO dao = new MedicamentoDAO();
        MedicamentoBean medicamento = new MedicamentoBean();
        HttpSession sessao = request.getSession();
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
         
        
        String acao = request.getParameter("acao");
        
        if (acao == null){
            acao = "listar";
        }
        
                
        if (acao != null || !acao.equalsIgnoreCase("listar")){
            String codigo = request.getParameter("codigo");
            if(codigo == null)
                codigo = "1";
           medicamento.setCodigo(Integer.parseInt(codigo));            
           medicamento.setMedicamento(request.getParameter("nome"));

        }
        
       try {
           RequestDispatcher rd = null;
           if (acao.equalsIgnoreCase("cadastrar")){
               if(adm != null){
                    dao.cadastrar(medicamento);
                    request.setAttribute("medicamentoSelecionado", medicamento);
                    rd = request.getRequestDispatcher("MedicamentoController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("listar")) {
               if(adm != null || medico != null){
                    List medicamentoList = dao.retornarTodos();
                    request.setAttribute("medicamentoList", medicamentoList);
                    rd = request.getRequestDispatcher("medicamentoLista.jsp");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
                              
           } else if (acao.equalsIgnoreCase("excluir")){
               if(adm != null){
                    dao.excluir(medicamento);
                    rd = request.getRequestDispatcher("MedicamentoController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if (acao.equalsIgnoreCase("obterum")){
               if(adm != null){
                    medicamento = dao.retornarPeloCodigo(medicamento.getCodigo());
                    request.setAttribute("medicamentoSelecionado", medicamento);
                    rd = request.getRequestDispatcher("MedicamentoController?acao=formulario");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("Formulario")) {
               rd = request.getRequestDispatcher("medicamentoForm.jsp");
           
           } else if (acao.equalsIgnoreCase("alterar")){
               if(adm != null){
                    dao.alterar(medicamento);
                    request.setAttribute("medicamentoSelecionado", medicamento);
                    rd = request.getRequestDispatcher("MedicamentoController?acao=formulario");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           }
           rd.forward(request, response);
        } catch (Exception e) {            
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
