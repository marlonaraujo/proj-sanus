<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="UTF-8" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*" %>
<%
    HttpSession sessao = request.getSession();
    PessoaBean pessoa = null;
    try{
        pessoa = (PessoaBean)sessao.getAttribute("pessoaLogada");
        if(pessoa == null)
            response.sendError(403, "Você não tem permissão!");

    }catch(Exception exc){
        response.sendError(403, "Você não tem permissão!");
    }
%>
<div class="span3">
<div class="well sidebar-nav">
    <ul class="nav nav-list">
        
        <% if(pessoa.checkPerfil("administrador")){ %>  
      <li class="nav-header">Administrador</li>
      <li><a href="PessoaController?acao=dados">Dados Pessoais</a></li>
      <li><a href="HospitalController">Hospitais</a></li>
      <li><a href="PessoaController">Pessoas</a></li>
      <li><a href="MedicoController">M&eacute;dicos</a></li>
      <li><a href="PacienteController">Pacientes</a></li>
      <li><a href="PacienteController?acao=pre-cadastro-lista">Pacientes do Pré-Cadastro</a></li>
      <li><a href="EspecialidadeControl">Especialidades</a></li>
      <% } %>
      
      <% if(pessoa.checkPerfil("medico") && !pessoa.checkPerfil("administrador")){ %> 
      <li class="nav-header">M&eacute;dico</li>
      <li><a href="PessoaController?acao=dados">Dados Pessoais</a></li>
      <li><a href="PessoaController?acao=carregar">Pacientes</a></li>
      <li><a href="ConsultaControl?cmd=listar">Consultas</a></li>
      <li><a href="CirurgiaControl?cmd=ret">Cirurgias</a></li>
      <li><a href="ExameController">Exames</a></li> <!--?acao=Formulario-->
      <li><a href="MedicamentoController">Medicamentos</a></li>
      <li><a href="ReceitaControl?cmd=listar">Receitas</a></li>
      <li><a href="RestricaoControl?cmd=ret">Restrições</a></li>
      <% } %>
      
      <% if(pessoa.checkPerfil("paciente")&& !pessoa.checkPerfil("administrador")){ %> 
      <li class="nav-header">Paciente</li>
      <li><a href="PessoaController?acao=dados">Dados Pessoais</a></li>
      
      <li><a href="CirurgiaControl?cmd=listar">Cirurgias</a></li>
      <li><a href="ExameController?cmd=listar">Exames</a></li>
      <li><a href="">Receitas</a></li>
      <li><a href="RestricaoControl?cmd=listar">Restrições</a></li>
      <% } %>
    </ul>
</div><!--/.well -->
</div>
