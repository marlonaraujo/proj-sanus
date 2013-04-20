<%-- 
    Document   : pre-cadastro
    Created on : 14/04/2013, 16:22:12
    Author     : NewTech
--%>

<%@page  contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="pessoa.*"
        import="java.text.*"
        import="java.util.List"
        import="java.util.*"
        import="paciente.*"
%>




<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>

 <jsp:useBean id="codigo" class="paciente.PacienteDAO"/>   

 
 <%
    String dataAtual = "";
    Date date = new Date(System.currentTimeMillis());    
    SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
    dataAtual = formatarDate.format(date);

%>
 
    <div class="span9" style="min-height: 500px">
        <h2>Paciente</h2>
<form class="form-pre-paciente" method="POST" action="PacienteController?acao=salvar-pre-cadastro">
               
                    <fieldset>
                        <legend>Pré-Cadastro de Pacientes</legend>
                                <label>Código<br />
                        <input readonly="readonly" type="text" id="codigo" name="codigo" class="input-xxlarge" 
                         value="<%=codigo.ultimoRegistroPaciente()%>">	
                    </label>
            
                                <label>Paciente<br />
                                    <input type="text" id="nome" name="nome" class="input-xxlarge" >
                                </label>
                                
                                <label>Data de cadastro<br />
                                <input type="text" id="data-nascimento" name="data-nascimento" class="input-xlarge campo-data" value="<% out.print(dataAtual);%>">	
                                </label>
                                
			
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = 'PessoaController?acao=carregar' ">Cancelar</button>
				
                        </div>

                    </fieldset>
               
			</form>
    </div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>
