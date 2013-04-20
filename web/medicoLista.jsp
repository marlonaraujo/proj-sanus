<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro403.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="medico.*"
        import="java.util.List"
        import="java.util.*"%>


<jsp:useBean id="medicosList" scope="request" class="List" />
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
        <h2>Médico</h2>     
        <ul class="nav">
            <li><a href="MedicoController?acao=escolher-medico">Novo Médico</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                Médicos
            </caption>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Crm</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Identidade</th>
                    <th>E-mail</th>
                    <th>Data nascimento</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
<% 
    for(Iterator i = medicosList.iterator(); i.hasNext();) {
        MedicoBean m = (MedicoBean)i.next(); 
%>                
                <tr>
                    <td><a href="MedicoController?acao=obterum&codigo=<%=m.getCodigo()%> "><%=m.getCodigo()%></a></td>
                    <td><%=m.getCrm()%></td>
                    <td><%=m.getNome()%></td>
                    <td><%=m.getCpf()%></td>
                    <td><%=m.getRg()%></td>
                    <td><%=m.getEmail()%></td>
                    <td><%=data.converteDataTexto(m.getDataNascimento())%></td>
                    <th><a href="MedicoController?acao=obterum&codigo=<%=m.getCodigo()%> ">Editar</a></th>
                    <th><a href="MedicoController?acao=excluir&codigo=<%=m.getCodigo()%> ">Excluir</a></th>
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