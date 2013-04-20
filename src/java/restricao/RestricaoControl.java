/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restricao;

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
import paciente.PacienteBean;
import pessoa.PessoaBean;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "RestricaoControl", urlPatterns = {"/RestricaoControl"})
public class RestricaoControl extends HttpServlet {

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
    
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {   
        
        RestricaoBean restricao = new RestricaoBean();
        
        RestricaoDAO dao = new RestricaoDAO();
        HttpSession sessao = request.getSession();
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        
        
        String cmd = request.getParameter("cmd");
        
        if (cmd == null)
            cmd = "principal";
            
        if (cmd != null || !cmd.equalsIgnoreCase("principal")){
            String codigo = request.getParameter("codigo");
            if(codigo == null)
                codigo = "1";
            restricao.setCodigo(Integer.parseInt(codigo)); 
            restricao.setTipo(request.getParameter("tipo")); 
            restricao.setDescricao(request.getParameter("descricao"));
            String cpf = request.getParameter("cpf");
            if (cpf == null)
                cpf = "1";
            restricao.setCpf(Integer.parseInt(cpf));
            
        } 
        
        try{
            RequestDispatcher rd = null;
        
            if(cmd.equalsIgnoreCase("listar")){ 
                if((adm != null || medico != null)){
                    List restricoesList = dao.retornaRestricoes(); 
                    request.setAttribute("restricoesList", restricoesList); 
                    rd = request.getRequestDispatcher("adminRestricoes.jsp");
                }else{
                    List listaRestricaoPaciente = dao.restricaoPaciente(paciente.getCodigo());
                    request.setAttribute("listaRestricaoPaciente",listaRestricaoPaciente);
                    rd = request.getRequestDispatcher("listaRestricaoPaciente.jsp");
                    
                    //rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("lista")){
                    if((adm != null || medico != null)){
                        List pacientesList = dao.pacientes();
                        request.setAttribute("pacientesLista", pacientesList);
                        rd = request.getRequestDispatcher("restricaoForm.jsp");
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("salvar")){ 
                    if((adm != null || medico != null)){ 
                        dao.salvar(restricao); 
                        rd = request.getRequestDispatcher("RestricaoControl?cmd=listar");
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("excluir")){ 
                if((adm != null || medico != null)){ 
                    dao.excluir(restricao); 
                    rd = request.getRequestDispatcher("RestricaoControl?cmd=ret"); 
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("ret")){
                if((adm != null || medico != null)){
                    List restricoesList = dao.retornaRestricoes(); 
                    request.setAttribute("restricoesList", restricoesList); 
                    rd = request.getRequestDispatcher("adminRestricoes.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("atu")){
                if((adm != null || medico != null)){
                    List pacientesList = dao.pacientes();
                    request.setAttribute("pacientesLista", pacientesList);
                    List codPaciente = dao.codigoPaciente(restricao.getCodigo());
                    request.setAttribute("codPaciente", codPaciente);
                    restricao = dao.adminRestricoes(restricao.getCodigo()); 
                    request.setAttribute("restricao",restricao); 
                    rd = request.getRequestDispatcher("restricaoSelecionada.jsp");
                }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                }
            
            }else if(cmd.equalsIgnoreCase("atualizar")){ 
                dao.atualizar(restricao); 
                rd = request.getRequestDispatcher("RestricaoControl?cmd=ret"); 
            }else if(cmd.equalsIgnoreCase("principal")){ 
                rd = request.getRequestDispatcher("RestricaoControl?cmd=lista"); 
            }else if(cmd.equalsIgnoreCase("busca")){
                List listaRestricaoPaciente = dao.restricaoPaciente(restricao.getCodigo());
                request.setAttribute("listaRestricaoPaciente",listaRestricaoPaciente);
                rd = request.getRequestDispatcher("listaRestricaoPaciente.jsp");
            }
                
            rd.forward(request, response); 
        
        }catch(Exception e){
           throw new ServletException(e);
        }
        
        
       
        
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
