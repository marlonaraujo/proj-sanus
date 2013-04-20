<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"%>


<jsp:useBean id="pessoasList" scope="request" class="List" />
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
        <h2>Pessoa</h2>     
        <ul class="nav">
            <li><a href="PessoaController?acao=formulario">Nova Pessoa</a></li>
        </ul>
        <table class="table table-hover">
            <caption>
                Pessoas
            </caption>
            <thead>
                <tr>
                    <th>Código</th>
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
    for(Iterator i = pessoasList.iterator(); i.hasNext();) {
        PessoaBean p = (PessoaBean)i.next(); 
%>                
                <tr>
                    <td><a href=" PessoaController?acao=obterum&codigo=<%=p.getCodigo()%> "><%=p.getCodigo()%></a></td>
                    <td><%=p.getNome()%></td>
                    <td><%=p.getCpf()%></td>
                    <td><%=p.getRg()%></td>
                    <td><%=p.getEmail()%></td>
                    <td><%=data.converteDataTexto(p.getDataNascimento())%></td>
                    <th><a href=" PessoaController?acao=obterum&codigo=<%=p.getCodigo()%> ">Editar</a></th>
                    <th><a href=" PessoaController?acao=excluir&codigo=<%=p.getCodigo()%> ">Excluir</a></th>
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