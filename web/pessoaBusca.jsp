<%-- 
    Document   : pessoaBusca
    Created on : 25/03/2013, 14:47:20
    Author     : NewTech
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"
%>



<jsp:useBean id="pessoaBusca" scope="request" class="pessoa.PessoaBean" />
<jsp:useBean id="pacientesLista" scope="request" class="List" />
     
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
                    <th>Restrições</th>
                    <th>Exames</th>
                    <th>Receitas</th>
                    <th>Consultas</th>
                    
                    
                </tr>
                
            </thead>
            <tbody>
                <% 
                 
                for(Iterator i = pacientesLista.iterator(); i.hasNext();) {
                    PessoaBean p = (PessoaBean)i.next(); 
             %>
                
                <tr>
                    <td><% out.print(p.getCodigo()); %></td>
                    <td></td>
                    <td><% out.print(p.getNome());%></td>
                    <td></td>
                    <td><a href="CirurgiaControl?cmd=busca&codigo=<%= p.getCodigo()%>">Listar</a></td>
                    <td><a href="RestricaoControl?cmd=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    <td><a href="ExameController?acao=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    <td><a href="ReceitaControl?cmd=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    <td><a href="ConsultaControl?cmd=busca&codigo=<%=p.getCodigo()%>">Listar</a></td>
                    
                    
                </tr>
            </tbody>
            <tfoot>
                
            </tfoot>
            <%}%>
        </table>
    
    
    
    
        


