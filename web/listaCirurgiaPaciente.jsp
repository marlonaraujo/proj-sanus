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
        import="cirurgia.*"
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
    <jsp:useBean id="listaCirurgiaPaciente" scope = "request" class="List" />

    <div class="span9" style="min-height: 500px">
        <h2>Cirurgias</h2>
        <ul class="nav">
            
        </ul>
        
           <table class="table table-hover">
            <caption>
                Cirurgias
            </caption>
            
            <tbody>    
    
                <%  
                     for(Iterator i = listaCirurgiaPaciente.iterator(); i.hasNext();){
                        CirurgiaBean l = (CirurgiaBean)i.next();
                %>
                <tr>
                   
                    <th>Cirurgia <%= l.getCodigo() %></th>
                   
                </tr><tr>
                <tr>
                    <th>Nome Cirurgia</th>
                </tr><tr>    
                    <td><%= l.getCirurgia() %></td>
                </tr>
                <tr>
                    <th>Médico</th>
                </tr><tr> 
                    <td><%= l.getMedico() %></td>
                    
                </tr>
                <tr>
                    <th>Paciente</th>
                </tr><tr>    
                    <td><%= l.getPaciente() %></td>
                    
                </tr>
                <tr>
                    <th>Data Cirurgia</th>
                </tr><tr>    
                    <td><%= data.converteDataTexto(l.getData()) %></td>
                    
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