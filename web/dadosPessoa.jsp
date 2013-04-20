<%-- 
    Document   : dadosPessoa
    Created on : 25/03/2013, 11:48:44
    Author     : NewTech
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        
%>

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

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>


<jsp:useBean id="data" scope="request" class="data.ConverteData" />
<jsp:useBean id="pessoaDados" scope="request" class="pessoa.PessoaBean" />
<jsp:useBean id="crm" scope="request" class="medico.MedicoBean" />

<div class="span9" style="min-height: 500px">
    <form class="frm-dados-alt" method="POST" action="<% if(pessoa.checkPerfil("medico")){%>MedicoController<% }else{ %>PacienteController<%}%>">
    <input type="hidden" value="dadosAtu" id="acao" name="acao" />
                    
                    <fieldset>
                        <legend>Dados Pessoais</legend>
                                <label>Código<br />
                                    <input type="text" id="codigo" name="codigo" class="input-xlarge" value="<% out.print(pessoaDados.getCodigo()); %>" readonly="readonly">	
                                </label>

        <div class="control-group">
            <label class="control-label" for="nome">
                Nome: 
            </label>  
            <div class="controls">    
                <input type="text" value="<% out.print(pessoaDados.getNome());%>" class="input-xlarge" placeholder="Digite seu nome..." id="nome" name="nome" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="email">
                E-mail: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%out.print(pessoaDados.getEmail());%>" class="input-xlarge" placeholder="Digite seu e-mail..." id="email" name="email"  />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="senha">
                Senha: 
            </label>  
            <div class="controls">    
                <input type="password" value="<% out.print(pessoaDados.getSenha()); %>" class="input-xlarge" placeholder="Digite nova senha para alterar..." id="senha" name="senha" />
            </div>
      </div>
            
      <div class="control-group">
            <label class="control-label" for="cpf">
                CPF: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%out.print(pessoaDados.getCpf());%>" class="input-xlarge campo-cpf" placeholder="Digite seu CPF..." id="cpf" name="cpf" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="id">
                RG: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%out.print(pessoaDados.getRg());%>" class="input-xlarge" placeholder="Digite seu RG..." id="id" name="id" />
            </div>
       </div>
            <% if(pessoa.checkPerfil("medico") &&  !pessoa.checkPerfil("administrador")){%>
       <div class="control-group">
            <label class="control-label" for="crm">
                CRM: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%out.print(crm.getCrm());%>" class="input-xlarge" placeholder="Digite seu CRM..." id="crm" name="crm" />
            </div>
       </div>    
       <%}%>
      <div class="control-group">
            <label class="control-label" for="data-nascimento">
                Data de Nascimento: 
            </label>  
            <div class="controls">    
                <input type="text" value="<% out.print(data.converteDataTexto(pessoaDados.getDataNascimento()));%>" class="input-xlarge campo-data" placeholder="Digite sua data de nascimento?" id="data-nascimento" name="data-nascimento" />
            </div>
       </div>
     <div class="control-group">
            <div class="controls">
                <input type="submit" class="btn btn-primary" value="Atualizar" />
                <input type="button" class="btn btn-danger" onclick="window.location = 'PessoaController?acao=carregar' " value="Cancelar" />
            </div>
        </div>
                    </fieldset>
            
</form>
</div>
            </div>

<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>
