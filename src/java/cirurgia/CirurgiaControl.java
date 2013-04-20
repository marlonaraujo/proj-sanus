/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cirurgia;


import data.ConverteData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Administrador
 */
@WebServlet(name = "CirurgiaControl", urlPatterns = {"/CirurgiaControl"})
public class CirurgiaControl extends HttpServlet {

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
        HttpSession sessao = request.getSession(true);
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        
        String cmd = request.getParameter("cmd");
        
        if (cmd == null)
            cmd = "principal";
        
        CirurgiaBean cirurgia = new CirurgiaBean();
        CirurgiaDAO dao = new CirurgiaDAO();
        ConverteData cd = new ConverteData();
        MedicoDAO daoMedico = new MedicoDAO();
        
        if(cmd != null || ! cmd.equalsIgnoreCase("principal")){
            String codigo = request.getParameter("codigo");
            if(codigo==null)
                codigo="1";
            cirurgia.setCodigo(Integer.parseInt(codigo));
            String cpf = request.getParameter("cpf");
            if(cpf==null)
                cpf="1";
            cirurgia.setCpf(Integer.parseInt(cpf));
            String crm = request.getParameter("crm");
            if(crm==null)
                crm="1";
            cirurgia.setCrm(Integer.parseInt(crm));
            cirurgia.setCirurgia(request.getParameter("nome"));
            cirurgia.setDescricao(request.getParameter("descricao"));
            String data = request.getParameter("data");
            if(data==null || data == "")
                data="00/00/0000";
            cirurgia.setData(cd.converteEmData(data));
        
        }
               
        try{
            RequestDispatcher rd = null;
        
            if(cmd.equalsIgnoreCase("listar")){ 
                if(adm != null || medico != null){
                    List todasCirurgias = dao.retornaTodasCirurgias(); 
                    request.setAttribute("todasCirurgias", todasCirurgias); 
                    rd = request.getRequestDispatcher("listaCirurgias.jsp");
                }else{
                    List listaCirurgiaPaciente = dao.cirurgiaPaciente(paciente.getCodigo());
                    request.setAttribute("listaCirurgiaPaciente",listaCirurgiaPaciente);
                    rd = request.getRequestDispatcher("listaCirurgiaPaciente.jsp");
                    //rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("lista")){
                if(adm != null || medico != null){
                    medico.setCodigo(daoMedico.existePeloCodigo(medico));
                    medico.setCodigoPessoa(pessoa.getCodigo());
                    medico.setNome(pessoa.getNome());
                    request.setAttribute("existeMedico", medico);
                                                
                    List pacientesList = dao.pacientes(pessoa.getCodigo());
                    request.setAttribute("pacientesLista", pacientesList);
                    List medicosList = dao.medicos();
                    request.setAttribute("medicosLista", medicosList);
                    rd = request.getRequestDispatcher("cirurgiaForm.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("salvar")){
                if(adm != null || medico != null){
                    dao.salvar(cirurgia); 
                    rd = request.getRequestDispatcher("CirurgiaControl?cmd=ret");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("excluir")){
                if(adm != null || medico != null){
                    dao.excluir(cirurgia); 
                    rd = request.getRequestDispatcher("CirurgiaControl?cmd=ret");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("ret")){
                if(adm != null || medico != null){
                    
                    //List cirurgiasList = dao.retornaCirurgias();
                    List cirurgiasList = dao.retornaCirurgiasMedicos(pessoa.getCodigo());
                    request.setAttribute("cirurgiasList", cirurgiasList); 
                    rd = request.getRequestDispatcher("medicoCirurgias.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("atu")){
                if(adm != null || medico != null){
                    List medicosList = dao.medicos();
                    request.setAttribute("medicosLista", medicosList);
                    List pacientesList = dao.pacientes(pessoa.getCodigo());
                    request.setAttribute("pacientesLista", pacientesList);

                    List codMedico = dao.codigoMedico(cirurgia.getCodigo());
                    request.setAttribute("codMedico",codMedico);
                    List codPaciente = dao.codigoPaciente(cirurgia.getCodigo());
                    request.setAttribute("codPaciente",codPaciente);
                    cirurgia = dao.medicoCirurgias(cirurgia.getCodigo()); 

                    request.setAttribute("cirurgia",cirurgia); 
                    rd = request.getRequestDispatcher("cirurgiaSelecionada.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }   
            }else if(cmd.equalsIgnoreCase("atualizar")){ 
                dao.atualizar(cirurgia); 
                rd = request.getRequestDispatcher("CirurgiaControl?cmd=ret"); 
            }else if(cmd.equalsIgnoreCase("principal")){ 
                rd = request.getRequestDispatcher("CirurgiaControl?cmd=ret"); 
            }else if(cmd.equalsIgnoreCase("busca")){
                    List listaCirurgiaPaciente = dao.cirurgiaPaciente(cirurgia.getCodigo());
                    request.setAttribute("listaCirurgiaPaciente",listaCirurgiaPaciente);
                    rd = request.getRequestDispatcher("listaCirurgiaPaciente.jsp");
                    //rd = request.getRequestDispatcher("erro403.jsp");
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
