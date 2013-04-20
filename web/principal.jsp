<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*" 
        import="java.util.List"
        import="java.util.*"
%>
<%
    HttpSession sessao = request.getSession();
    PessoaBean pessoa = null;
    try{
        pessoa = (PessoaBean)sessao.getAttribute("pessoaLogada");
        if(pessoa == null)
            //response.sendError(403, "Você não tem permissão"); 
            response.sendRedirect("erro403.jsp");  
                  
            
    }catch(Exception exc){
        response.sendError(403, "Você não tem permissão!");
    }
%>
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>    
    
    <div class="span9" style="min-height: 500px">
        <h1>Sistema</h1>
        
         <% if(pessoa.checkPerfil("medico")){ %> 
        
            <ul class="nav">
            <li><a href="PacienteController?acao=pre-cadastro">Novo Paciente</a></li>
        </ul>
         <div>
            <jsp:include page="pessoaBusca.jsp" flush="true">
                <jsp:param name="busca" value="pagina" />
            </jsp:include>
            
        </div>
        <%}%>
    </div><!--/span-->
    
    
    
</div>        

<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>