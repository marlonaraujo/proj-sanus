/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package exame;

import data.ConverteData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import medico.MedicoBean;
import medico.MedicoDAO;
import paciente.PacienteBean;
import pessoa.PessoaBean;

/**
 *
 * @author Vilmar
 */
@WebServlet(name = "ExameController", urlPatterns = {"/ExameController"})
public class ExameController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ExameDAO dao = new ExameDAO();
        ExameBean exame = new ExameBean();
        HttpSession sessao = request.getSession(true);
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        ConverteData cd = new ConverteData();
        MedicoDAO daoMedico = new MedicoDAO();
         
        String acao = request.getParameter("acao");
        
        if (acao == null){
            acao = "listar";
        }
        
                
        if(acao != null){
           if (request.getParameter("codigo") != null && request.getParameter("codigo") !="" ){
               exame.setCodigo(Integer.parseInt(request.getParameter("codigo"))); 
           }
           String codigo = request.getParameter("codigo");
            if(codigo == null)
                codigo = "1";
            exame.setCodigo(Integer.parseInt(codigo)); 
           String crm = request.getParameter("crm");
           if(crm == null)
               crm = "1";
           String cpf = request.getParameter("cpf");
            if (cpf == null)
                cpf = "1";
           exame.setCpf(Integer.parseInt(cpf));
           exame.setCrm(Integer.parseInt(crm));
           exame.setNome(request.getParameter("nome"));
           exame.setDescricao(request.getParameter("desc"));
           String data = request.getParameter("data");
            if(data==null || data == "")
                data="00/00/0000";
           exame.setData(cd.converteEmData(data));
        }
        
       try {
           RequestDispatcher rd = null;
           if (acao.equalsIgnoreCase("cadastrar")){
               if(adm != null || medico != null){
                    List pacientesList = dao.pacientes(pessoa.getCodigo());
                    request.setAttribute("pacientesLista", pacientesList);
                    
                    dao.cadastrar(exame);
                    request.setAttribute("exameSelecionado", exame);
                    rd = request.getRequestDispatcher("ExameController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("listar")) {
               if(adm != null || medico != null){
                    //List exameList = dao.retornarTodos();
                    List exameList = dao.retornarExamesMedico(pessoa.getCodigo());
                    request.setAttribute("exameList", exameList);
                    rd = request.getRequestDispatcher("exameLista.jsp");
               }else{
                   List listaExamePaciente = dao.examePaciente(paciente.getCodigo());
                   request.setAttribute("listaExamePaciente",listaExamePaciente);
                   rd = request.getRequestDispatcher("listaExamePaciente.jsp");
                    
                   //rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if (acao.equalsIgnoreCase("excluir")){
               if(adm != null || medico != null){
                    dao.excluir(exame);
                    rd = request.getRequestDispatcher("ExameController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }     
           } else if (acao.equalsIgnoreCase("obterum")){
               if(adm != null || medico != null || paciente != null){
                    exame = dao.retornarPeloCodigo(exame.getCodigo());
                    request.setAttribute("exameSelecionado", exame);
                    rd = request.getRequestDispatcher("ExameController?acao=formulario");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           } else if(acao.equalsIgnoreCase("Formulario")) {
               medico.setCodigo(daoMedico.existePeloCodigo(medico));
               medico.setCodigoPessoa(pessoa.getCodigo());
               medico.setNome(pessoa.getNome());
               request.setAttribute("existeMedico", medico);
               
               List medicosList = dao.medicos();
               request.setAttribute("medicosLista",medicosList);
               List codMedico = dao.codigoMedico(exame.getCodigo());
               request.setAttribute("codMedico",codMedico);
               
               List pacientesList = dao.pacientes(pessoa.getCodigo());
               request.setAttribute("pacientesLista", pacientesList);
               List codPaciente = dao.codigoPaciente(exame.getCodigo());
               request.setAttribute("codPaciente", codPaciente);    
               rd = request.getRequestDispatcher("exameForm.jsp");
           
           } else if (acao.equalsIgnoreCase("alterar")){
               if(adm != null || medico != null){
                    dao.alterar(exame);
                    request.setAttribute("exameSelecionado", exame);
                    rd = request.getRequestDispatcher("ExameController?acao=listar");
               }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
               }
           }else if(acao.equalsIgnoreCase("busca")){
                   List listaExamePaciente = dao.examePaciente(exame.getCodigo());
                   request.setAttribute("listaExamePaciente",listaExamePaciente);
                   rd = request.getRequestDispatcher("listaExamePaciente.jsp");
                  
            }
           rd.forward(request, response);
        } catch (Exception e) {            
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


}
