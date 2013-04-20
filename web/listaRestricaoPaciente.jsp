<%-- 
    Document   : listaCirurgias
    Created on : Oct 12, 2012, 10:57:09 PM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="restricao.*"
        import="java.util.*"
%>


<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   

    
    <jsp:useBean id="data" scope="request" class="data.ConverteData" />
    <jsp:useBean id="listaRestricaoPaciente" scope = "request" class="List" />

    <div class="span9" style="min-height: 500px">
        <h2>Restrições</h2>
        <ul class="nav">
            
        </ul>
        
           <table class="table table-hover">
            <caption>
                Restrições
            </caption>
            
            <tbody>    
    
                <%  
                     for(Iterator i = listaRestricaoPaciente.iterator(); i.hasNext();){
                        RestricaoBean l = (RestricaoBean)i.next();
                %>
                <tr>
                   
                    <th>Restricao <%= l.getCodigo() %></th>
                   
                </tr><tr>
                <tr>
                    <th>Tipo Restricao</th>
                </tr><tr>    
                    <td><%= l.getTipo() %></td>
                </tr>
                
                <tr>
                    <th>Paciente</th>
                </tr><tr>    
                    <td><%= l.getCpf() %></td>
                    
                </tr>
                
                <tr>
                    <th>Descrição</th>
                </tr><tr>    
                    <td><%= l.getDescricao() %></td>
                </tr><tr>    
                    <td> <br></td>
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