<%-- 
    Document   : escolher-paciente
    Created on : 14/04/2013, 21:18:00
    Author     : NewTech
--%>

<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="paciente.*"
        import="java.util.List"
        import="java.util.*"%>


<jsp:useBean id="pessoasLista" scope="request" class="List" />

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   
    
    <div class="span9" style="min-height: 500px">
        <h2>Paciente</h2>
        <form class="form-pessoa" method="POST" action="PacienteController">
        <input type="hidden" id="acao" name="acao" value="pessoa-paciente" />
        
        <fieldset>
            <label>Pessoas<br />
                                    <select name="codigo-pessoa" id="codigo-pessoa" class="input-xxlarge">
                                            <option selected>Selecione uma Pessoa </option>
                                                  <%  
                                                
                                                 
                                                for(Iterator i = pessoasLista.iterator(); i.hasNext();) {
                                                    PessoaBean p = (PessoaBean)i.next(); 
                                            %>
        
                                            <option value="<%= p.getCodigo() %>" > <%= p.getNome() %>  </option>
                                        <%
                                                }
                                        %>
        
                                    </select>	
                                <br>

                    <div style="text-align: center">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                    <button type="button" class="btn btn-danger" onclick="window.location = 'PacienteController'">Cancelar</button>

            </div>
        </fieldset>
        </form>
    </div> 
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>
