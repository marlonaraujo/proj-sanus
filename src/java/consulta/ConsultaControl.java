/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

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
 * @author NewTech
 */
@WebServlet(name = "ConsultaControl", urlPatterns = {"/ConsultaControl"})
public class ConsultaControl extends HttpServlet {

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
        ConsultaBean consulta = new ConsultaBean();
        ConsultaDAO dao = new ConsultaDAO();
        MedicoDAO daoMedico = new MedicoDAO();
        
        String cmd = request.getParameter("cmd");
        
        if (cmd == null)
            cmd = "listar";
        
        ConverteData cd = new ConverteData();
        
        if (cmd != null || !cmd.equalsIgnoreCase("listar")){
            String codigo = request.getParameter("codigo");
            if(codigo == null)
                codigo = "1";
            consulta.setCodigo(Integer.parseInt(codigo)); 
            consulta.setDescricao(request.getParameter("desc")); 
            String data = request.getParameter("data");
            if(data==null || data == "")
                data="00/00/0000";
            consulta.setData(cd.converteEmData(data));
            String cpf = request.getParameter("cpf");
            if (cpf == null)
                cpf = "1";
            consulta.setCpf(Integer.parseInt(cpf));
            String crm = request.getParameter("crm");
           if(crm == null)
               crm = "1";
           consulta.setCrm(Integer.parseInt(crm));
            
        } 
        
         try{
            RequestDispatcher rd = null;
        
            if(cmd.equalsIgnoreCase("listar")){ 
                if(adm != null || medico != null){
                    //List consultaList = dao.retornarTodos();
                    List consultaList = dao.retornarConsultasMedico(pessoa.getCodigo());
                    request.setAttribute("consultasList", consultaList);
                    rd = request.getRequestDispatcher("listaConsultas.jsp");
               }else{
                   List listaConsultaPaciente = dao.consultaPaciente(paciente.getCodigo());
                   request.setAttribute("listaConsultaPaciente",listaConsultaPaciente);
                   rd = request.getRequestDispatcher("listaConsultaPaciente.jsp");
                    
                   //rd = request.getRequestDispatcher("erro403.jsp");
               }
            }else if(cmd.equalsIgnoreCase("lista")){
                    if((adm != null || medico != null)){
                        medico.setCodigo(daoMedico.existePeloCodigo(medico));
                        medico.setCodigoPessoa(pessoa.getCodigo());
                        medico.setNome(pessoa.getNome());
                        request.setAttribute("existeMedico", medico);
               
                        List medicosList = dao.medicos();
                        request.setAttribute("medicosLista",medicosList);
                        List codMedico = dao.codigoMedico(consulta.getCodigo());
                        request.setAttribute("codMedico",codMedico);

                        List pacientesList = dao.pacientes(pessoa.getCodigo());
                        request.setAttribute("pacientesLista", pacientesList);
                        List codPaciente = dao.codigoPaciente(consulta.getCodigo());
                        request.setAttribute("codPaciente", codPaciente);    
                        
                        rd = request.getRequestDispatcher("consultaForm.jsp");
           
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("salvar")){ 
                    if((adm != null || medico != null)){ 
                        dao.salvar(consulta); 
                        rd = request.getRequestDispatcher("ConsultaControl?cmd=listar");
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("excluir")){ 
                if((adm != null || medico != null)){ 
                    dao.excluir(consulta);
                    rd = request.getRequestDispatcher("ConsultaControl?cmd=listar");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("obterum")){
                if((adm != null || medico != null)){
                    consulta = dao.retornarPeloCodigo(consulta.getCodigo());
                    request.setAttribute("consultaSelecionado", consulta);
                    rd = request.getRequestDispatcher("ConsultaControl?cmd=lista");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            }else if(cmd.equalsIgnoreCase("alterar")){ 
                dao.atualizar(consulta);
                request.setAttribute("consultaSelecionado", consulta);
                rd = request.getRequestDispatcher("ConsultaControl?cmd=listar");
            }else if(cmd.equalsIgnoreCase("principal")){ 
                rd = request.getRequestDispatcher("ConsultaControl?cmd=lista"); 
            }else if(cmd.equalsIgnoreCase("busca")){
                List listaConsultaPaciente = dao.consultaPaciente(consulta.getCodigo());
                request.setAttribute("listaConsultaPaciente",listaConsultaPaciente);
                rd = request.getRequestDispatcher("listaConsultaPaciente.jsp");
                  
            }
                
            rd.forward(request, response); 
        
        }catch(Exception e){
           throw new ServletException(e);
        }
        
    }
}

    
