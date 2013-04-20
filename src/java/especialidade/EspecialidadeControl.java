/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package especialidade;

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
@WebServlet(name = "EspecialidadeControl", urlPatterns = {"/EspecialidadeControl"})
public class EspecialidadeControl extends HttpServlet {

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
        
        
        String cmd = request.getParameter("cmd");
        
        if (cmd == null)
            cmd = "principal";
        
        EspecialidadeBean especialidade = new EspecialidadeBean();
        EspecialidadeDAO dao = new EspecialidadeDAO();
        HttpSession sessao = request.getSession();
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        
        
                
        if(cmd != null || ! cmd.equalsIgnoreCase("principal")){
            String codigo = request.getParameter("codigo");
            if(codigo==null)
                codigo="1";
            especialidade.setCodigo(Integer.parseInt(codigo));
            
            especialidade.setNome(request.getParameter("nome"));
            especialidade.setDescricao(request.getParameter("descricao"));
            
        }
               
        try{
            RequestDispatcher rd = null;
        
            if(cmd.equalsIgnoreCase("listar")){
                if(adm != null){
                    List todasEspecialidades = dao.retornaEspecialidades(); 
                    request.setAttribute("especialidadesList", todasEspecialidades); 
                    rd = request.getRequestDispatcher("listaEspecialidade.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("salvar")){ 
                if(adm != null){
                    dao.salvar(especialidade); 
                    rd = request.getRequestDispatcher("EspecialidadeControl?cmd=listar");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("excluir")){ 
                if(adm != null){
                    dao.excluir(especialidade); 
                    rd = request.getRequestDispatcher("EspecialidadeControl?cmd=listar");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("ret")){
                if(adm != null){
                    rd = request.getRequestDispatcher("especialidadeForm.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("atu")){
                if(adm != null){
                    especialidade = dao.codigoEspecialidade(especialidade.getCodigo());
                    request.setAttribute("especialidade",especialidade);
                    rd = request.getRequestDispatcher("especialidadeForm.jsp");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("atualizar")){
                if(adm != null){
                    dao.atualizar(especialidade); 
                    rd = request.getRequestDispatcher("EspecialidadeControl?cmd=listar"); 
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("principal")){ 
                rd = request.getRequestDispatcher("EspecialidadeControl?cmd=listar"); 
            }
                
            rd.forward(request, response); 
        
        }catch(Exception e){
           throw new ServletException(e);
        }
        
       
        
        
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EspecialidadeControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EspecialidadeControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
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
