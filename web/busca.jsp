<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"
%>


<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<jsp:useBean id="pessoaBusca" scope="request" class="List" />

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>    
    
    <div class="span9" style="min-height: 500px">
        
        <h1>Sistema</h1>
        <br>
        <div>
        <form class="form-search" method="POST" action="PessoaController?acao=busca">
  <div class="input-append">
    <input type="text" name="nome" class="span2 search-query" placeholder="Pesquisa...">
    <button type="submit" class="btn btn-info">Pesquisar</button>
  </div>
      </form>
    
        
        <table class="table table-hover">
            
            
            <thead>
                <tr>
                
                    <th>Código</th>
                    <th></th>
                    <th>Paciente</th>
                    <th></th>
                    <th>Cirurgias</th>
                    <th>Restições</th>
                    <th>Exames</th>
                    
                    
                </tr>
                
            </thead>
            <tbody>
               
                 <% 
                 
                for(Iterator i = pessoaBusca.iterator(); i.hasNext();) {
                    PessoaBean p = (PessoaBean)i.next(); 
             %>
                
                <tr>
                    <td><% out.print(p.getCodigo()); %></td>
                    <td></td>
                    <td><% out.print(p.getNome());%></td>
                    <td></td>
                    <td><a href="CirurgiaControl?cmd=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    <td><a href="RestricaoControl?cmd=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    <td><a href="ExameController?acao=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    
                    
                </tr>
            </tbody>
            <tfoot>
                
            </tfoot>
            <%}%>
        </table>
        </div>
                    </div>
        
        
        
    </div>
 <jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>