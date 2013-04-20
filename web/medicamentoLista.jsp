<%@page import="medicamentos.MedicamentoBean"%>
<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"%>



<jsp:useBean id="medicamentoList" scope="request" class="List" />

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   


    <div class="span9" style="min-height: 500px">
        <h2>Medicamento</h2>     
        <ul class="nav">
            <li><a href="MedicamentoController?acao=formulario">Novo Medicamento</a></li>
        </ul>
        <table class="table table-hover">
            
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome do Medicamento</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody> 
<% 
    for(Iterator i = medicamentoList.iterator(); i.hasNext();) {
        MedicamentoBean p = (MedicamentoBean)i.next(); 
%>                
               <tr>
                    <td><a href=" MedicamentoController?acao=obterum&codigo=<%=p.getCodigo()%> "><%=p.getCodigo()%></a></td>
                    <td><%=p.getMedicamento()%></td>
                   <th><a href=" MedicamentoController?acao=obterum&codigo=<%=p.getCodigo()%> ">Editar</a></th>
                    <th><a href=" MedicamentoController?acao=excluir&codigo=<%=p.getCodigo()%> ">Excluir</a></th>
                </tr>
<% } %>
            </tbody>
            <tfoot>
                </tfoot>
        </table>
</div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>