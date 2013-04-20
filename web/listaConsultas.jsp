<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="consulta.*"
        import="java.util.List"
        import="java.util.*"
        import="pessoa.*"
%>




<!-- incluindo o cabecalho -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>  
    
<jsp:useBean id="consultasList" scope="request" class="List" />
    
<div class="span9" style="min-height: 500px">
        <h2>Consultas</h2>
        <ul class="nav">
            <li><a href="ConsultaControl?cmd=lista">Nova Consulta</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                
            </caption>
            
            <thead>
                <tr>
                    <th>Código</th>
                    
                    <th>Médico</th>
                    <th></th>
                    <th>Paciente</th>
                    <th></th>
                    <th>Especialidade</th>
                    <th colspan="4">Opções</th>
                </tr>
            </thead>
            <tbody>
                <% 
 
                for(Iterator i = consultasList.iterator(); i.hasNext();) {
                    ConsultaBean r = (ConsultaBean)i.next(); 
             %>
                <tr>
                    <td><a href="ConsultaControl?cmd=obterum&codigo=<%= r.getCodigo() %>"><%= r.getCodigo()%></a></td>
                    
                    <td><%= r.getMedico() %></td>
                    <td></td>
                    <td><%= r.getPaciente() %></td>
                    <td></td>
                    <td></td>
                    <th><a href="ConsultaControl?cmd=obterum&codigo=<%= r.getCodigo() %>">Editar</a></th>
                    <th><a href="ConsultaControl?cmd=excluir&codigo=<%= r.getCodigo() %>">Excluir</a></th>
                </tr>
            </tbody>
            <tfoot>
                
            </tfoot>
            <% }
                %>
        </table>
</div>

<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>