/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package receita;

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
@WebServlet(name = "ReceitaControl", urlPatterns = {"/ReceitaControl"})
public class ReceitaControl extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        HttpSession sessao = request.getSession(true);
        PessoaBean pessoa = (PessoaBean) sessao.getAttribute("pessoaLogada");
        PacienteBean paciente = (PacienteBean) sessao.getAttribute("existePaciente");
        String adm = (String) sessao.getAttribute("existeAdm");
        MedicoBean medico = (MedicoBean) sessao.getAttribute("existeMedico");
        ReceitaBean receita = new ReceitaBean();
        ReceitaDAO dao = new ReceitaDAO();
        MedicoDAO daoMedico = new MedicoDAO();
        String cmd = request.getParameter("cmd");
        
        if (cmd == null)
            cmd = "listar";
        
        ConverteData cd = new ConverteData();
        
        if (cmd != null || !cmd.equalsIgnoreCase("listar")){
            String codigo = request.getParameter("codigo");
            if(codigo == null)
                codigo = "1";
            receita.setCodigo(Integer.parseInt(codigo)); 
            receita.setDescricao(request.getParameter("desc")); 
            String data = request.getParameter("data");
            if(data==null || data == "")
                data="00/00/0000";
            receita.setData(cd.converteEmData(data));
            String cpf = request.getParameter("cpf");
            if (cpf == null)
                cpf = "1";
            receita.setCpf(Integer.parseInt(cpf));
            String crm = request.getParameter("crm");
           if(crm == null)
               crm = "1";
           receita.setCrm(Integer.parseInt(crm));
           String medicamento = request.getParameter("medicamento");
           if(medicamento == null)
               medicamento = "1";
           
           receita.setMedicamento(Integer.parseInt(medicamento));
            
        } 
        
         try{
            RequestDispatcher rd = null;
        
            if(cmd.equalsIgnoreCase("listar")){ 
                if((adm != null || medico != null)){
                    //List receitaList = dao.retornarTodos();
                    List receitaList = dao.retornarReceitasMedico(pessoa.getCodigo());
                    request.setAttribute("receitasList", receitaList);
                    rd = request.getRequestDispatcher("listaReceitas.jsp");
                }else{
                    List listaReceitaPaciente = dao.receitaPaciente(paciente.getCodigo());
                    request.setAttribute("listaReceitaPaciente",listaReceitaPaciente);
                    rd = request.getRequestDispatcher("listaReceitaPaciente.jsp");
                    
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
                        List codMedico = dao.codigoMedico(receita.getCodigo());
                        request.setAttribute("codMedico",codMedico);

                        List pacientesList = dao.pacientes(pessoa.getCodigo());
                        request.setAttribute("pacientesLista", pacientesList);
                        List codPaciente = dao.codigoPaciente(receita.getCodigo());
                        request.setAttribute("codPaciente", codPaciente); 
                        
                        List medicamentosList = dao.medicamentos();
                        request.setAttribute("medicamentosLista", medicamentosList);
                        
                        rd = request.getRequestDispatcher("receitaForm.jsp");
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("salvar")){ 
                    if((adm != null || medico != null)){ 
                        dao.salvar(receita); 
                        rd = request.getRequestDispatcher("ReceitaControl?cmd=listar");
                    }else{
                        rd = request.getRequestDispatcher("erro403.jsp");
                    }
            }else if(cmd.equalsIgnoreCase("excluir")){ 
                if((adm != null || medico != null)){ 
                    dao.excluir(receita);
                    rd = request.getRequestDispatcher("ReceitaControl?cmd=listar");
                }else{
                    rd = request.getRequestDispatcher("erro403.jsp");
                }
            
            }else if(cmd.equalsIgnoreCase("obterum")){
                if((adm != null || medico != null)){
                   receita = dao.retornarPeloCodigo(receita.getCodigo());
                   request.setAttribute("receitaSelecionado", receita);
                   rd = request.getRequestDispatcher("ReceitaControl?cmd=lista"); 
                }else{
                   rd = request.getRequestDispatcher("erro403.jsp");
                }
            
            }else if(cmd.equalsIgnoreCase("alterar")){ 
                dao.atualizar(receita);
                request.setAttribute("receitaSelecionado", receita);
                rd = request.getRequestDispatcher("ReceitaControl?cmd=listar");
            }else if(cmd.equalsIgnoreCase("principal")){ 
                rd = request.getRequestDispatcher("ReceitaControl?cmd=listar"); 
            }else if(cmd.equalsIgnoreCase("busca")){
                List listaReceitaPaciente = dao.receitaPaciente(receita.getCodigo());
                request.setAttribute("listaReceitaPaciente",listaReceitaPaciente);
                rd = request.getRequestDispatcher("listaReceitaPaciente.jsp");
            }
                
            rd.forward(request, response); 
        
        }catch(Exception e){
           throw new ServletException(e);
        }
        
    }
    
}
