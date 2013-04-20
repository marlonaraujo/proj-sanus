<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="receita.*"
        import="java.util.*"
        import="data.*"
%>


<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   

    
    <jsp:useBean id="listaReceitaPaciente" scope = "request" class="List" />
    <jsp:useBean id="data" scope="request" class="data.ConverteData" />
    
    <div class="span9" style="min-height: 500px">
        <h2>Receitas</h2>
        <ul class="nav">
            
        </ul>
        
           <table class="table table-hover">
            <caption>
                Receitas
            </caption>
            
            <tbody>    
    
                <%  
                     for(Iterator i = listaReceitaPaciente.iterator(); i.hasNext();){
                        ReceitaBean l = (ReceitaBean)i.next();
                %>
                <tr>
                   
                    <th>Receita <%= l.getCodigo() %></th>
                   
                </tr><tr>
                <tr>
                    <th>Medicamento</th>
                </tr><tr>    
                    <td><%= l.getRemedio() %></td>
                </tr>
                
                <tr>
                    <th>Medico</th>
                </tr><tr>    
                    <td><%= l.getMedico() %></td>
                    
                </tr>
                
                <tr>
                    <th>Paciente</th>
                </tr><tr>    
                    <td><%= l.getPaciente() %></td>
                    
                </tr>
                
                <tr>
                    <th>Data</th>
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