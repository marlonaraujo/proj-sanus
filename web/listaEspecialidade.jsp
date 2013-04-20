<%-- 
    Document   : listaEspecialidade
    Created on : Nov 4, 2012, 9:17:55 PM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="pessoa.*"
        import="especialidade.*"
        import="java.util.*"
        import="java.util.List"
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
    
<jsp:useBean id="especialidadesList" scope="request" class="List" />
    
<div class="span9" style="min-height: 500px">
        <h2>Especialidades</h2>
        <ul class="nav">
            <li><a href="EspecialidadeControl?cmd=ret">Nova Especialidade</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                
            </caption>
            
            <thead>
                <tr>
                    <th>Código</th>
                    
                    <th></th>
                    <th></th>
                    <th>Especialidade</th>
                    <th></th>
                    <th></th>
                    <th colspan="4">Opções</th>
                </tr>
            </thead>
            <tbody>
                <% 
 
                for(Iterator i = especialidadesList.iterator(); i.hasNext();) {
                    EspecialidadeBean e = (EspecialidadeBean)i.next(); 
             %>
                <tr>
                    <td><a href="EspecialidadeControl?cmd=atu&codigo=<%= e.getCodigo() %>"> <%= e.getCodigo()%></a></td>
                    
                    <td></td>
                    <td></td>
                    <td><%= e.getNome() %></td>
                    <td></td>
                    <td></td>
                    <th><a href="EspecialidadeControl?cmd=atu&codigo=<%= e.getCodigo() %>">Editar</a></th>
                    <th><a href="EspecialidadeControl?cmd=excluir&codigo=<%= e.getCodigo() %>">Excluir</a></th>
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


