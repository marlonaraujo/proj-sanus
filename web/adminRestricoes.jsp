<%-- 
    Document   : adminRestricoes
    Created on : Oct 14, 2012, 10:16:32 AM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="restricao.*"
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
    
<jsp:useBean id="restricoesList" scope="request" class="List" />
    
<div class="span9" style="min-height: 500px">
        <h2>Restrições</h2>
        <ul class="nav">
            <li><a href="RestricaoControl?cmd=lista">Nova Restrição</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                
            </caption>
            
            <thead>
                <tr>
                    <th>Código</th>
                    
                    <th>Tipo</th>
                    <th></th>
                    <th>Paciente</th>
                    <th></th>
                    <th></th>
                    <th colspan="4">Opções</th>
                </tr>
            </thead>
            <tbody>
                <% 
 
                for(Iterator i = restricoesList.iterator(); i.hasNext();) {
                    RestricaoBean r = (RestricaoBean)i.next(); 
             %>
                <tr>
                    <td><a href="RestricaoControl?cmd=atu&codigo=<%= r.getCodigo() %>"><%= r.getCodigo()%></a></td>
                    
                    <td><%= r.getTipo() %></td>
                    <td></td>
                    <td><%= r.getDescricao() %></td>
                    <td></td>
                    <td></td>
                    <th><a href="RestricaoControl?cmd=atu&codigo=<%= r.getCodigo() %>">Editar</a></th>
                    <th><a href="RestricaoControl?cmd=excluir&codigo=<%= r.getCodigo() %>">Excluir</a></th>
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

