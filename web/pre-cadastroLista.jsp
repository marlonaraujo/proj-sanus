<%-- 
    Document   : pre-cadastroLista
    Created on : 14/04/2013, 16:54:41
    Author     : NewTech
--%>

<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro403.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="paciente.*"
        import="java.util.List"
        import="java.util.*"%>

<jsp:useBean id="pacientesList" scope="request" class="List" />
<jsp:useBean id="data" scope="request" class="data.ConverteData" />

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   


    <div class="span9" style="min-height: 500px">
        <h2>Paciente</h2>     
        <ul class="nav">
            <li><a href="PacienteController?acao=pre-cadastro">Novo Paciente</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                Paciente Pré-Cadastro
            </caption>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Data do cadastro</th>                 
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
<% 
    for(Iterator i = pacientesList.iterator(); i.hasNext();) {
        PacienteBean m = (PacienteBean)i.next(); 
%>                
                <tr>
                    <td><a href="PacienteController?acao=pre-cadastro-obterum&codigo=<%=m.getCodigo()%> "><%=m.getCodigo()%></a></td>
                    <td><%=m.getNome()%></td>
                    <td><%=data.converteDataTexto(m.getDataNascimento())%></td>
                    <th><a href="PacienteController?acao=pre-cadastro-obterum&codigo=<%=m.getCodigo()%> ">Editar</a></th>
                    <th><a href="PacienteController?acao=pre-cadastro-excluir&codigo=<%=m.getCodigo()%>&codigo-pessoa=<%=m.getCodigoPessoa() %> ">Excluir</a></th>
                </tr>
<% } %>
            </tbody>
            <tfoot>
                <tr>
                    <th>
                        
                    </th>
                </tr>
            </tfoot>
        </table>
</div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>