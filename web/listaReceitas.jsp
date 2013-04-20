<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="receita.*"
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
    
<jsp:useBean id="receitasList" scope="request" class="List" />
    
<div class="span9" style="min-height: 500px">
        <h2>Receitas</h2>
        <ul class="nav">
            <li><a href="ReceitaControl?cmd=lista">Nova Receita</a></li>
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
                    <th>Medicamento</th>
                    <th colspan="4">Opções</th>
                </tr>
            </thead>
            <tbody>
                <% 
 
                for(Iterator i = receitasList.iterator(); i.hasNext();) {
                    ReceitaBean r = (ReceitaBean)i.next(); 
             %>
                <tr>
                    <td><a href="ReceitaControl?cmd=obterum&codigo=<%= r.getCodigo() %>"> <%= r.getCodigo() %></a></td>
                    
                    <td><%= r.getMedico() %></td>
                    <td></td>
                    <td><%= r.getPaciente() %></td>
                    <td></td>
                    <td><%= r.getRemedio() %></td>
                    <th><a href="ReceitaControl?cmd=obterum&codigo=<%= r.getCodigo() %>">Editar</a></th>
                    <th><a href="ReceitaControl?cmd=excluir&codigo=<%= r.getCodigo() %>">Excluir</a></th>
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