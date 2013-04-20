<%@page import="exame.ExameBean"%>
<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"%>



<jsp:useBean id="exameList" scope="request" class="List" />

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   


    <div class="span9" style="min-height: 500px">
        <h2>Exame</h2>     
        <ul class="nav">
            <li><a href="ExameController?acao=formulario">Novo Exame</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                
            </caption>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Paciente</th>
                    <th>Médico</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
<% 
    for(Iterator i = exameList.iterator(); i.hasNext();) {
        ExameBean p = (ExameBean)i.next(); 
%>                
                <tr>
                    <td><a href=" ExameController?acao=obterum&codigo=<%=p.getCodigo()%> "><%=p.getCodigo()%></a></td>
                    <td><%=p.getNome()%></td>
                    <td><%=p.getPaciente()%></td>
                    <td><%=p.getMedico()%></td>
                    
                    <th><a href=" ExameController?acao=obterum&codigo=<%=p.getCodigo()%> ">Editar</a></th>
                    <th><a href=" ExameController?acao=excluir&codigo=<%=p.getCodigo()%> ">Excluir</a></th>
                </tr>
<% } %>
            </tbody>
            <tfoot>
                
            </tfoot>
        </table>
</div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>