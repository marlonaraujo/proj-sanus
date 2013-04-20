<%-- 
    Document   : restricaoForm
    Created on : Oct 14, 2012, 10:15:12 AM
    Author     : Administrador
--%>

<!-- incluindo o cabecalho -->
<%@page  contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="pessoa.*"
        import="restricao.*"
        import="java.text.*"
        import="java.util.List"
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

<jsp:useBean id="pacientesLista" scope="request" class="List" />
 <jsp:useBean id="codigo" class="restricao.RestricaoDAO"/>       
        
    <div class="span9" style="min-height: 500px">
        <h2>Restrição</h2>
<form class="form-restricao" method="POST" action="RestricaoControl?cmd=salvar">
               
                    <fieldset>
                        <legend>Cadastro de Restrição</legend>
                                <label>Código<br />
                                    <input type="text" id="codigo" name="codigo" class="input-xxlarge" readonly="readonly" value="<%= codigo.ultimoRegistro() %>" />	
                                </label>
            
                                <label>Paciente<br />
                                    <select name="cpf" id="cpf" class="input-xxlarge">
                                            <option selected>Selecione um Paciente </option>
                                                  <%  
                                                
                                                 
                                                for(Iterator i = pacientesLista.iterator(); i.hasNext();) {
                                                    PessoaBean p = (PessoaBean)i.next(); 
                                            %>
        
                                            <option value="<%= p.getCodigo() %>" > <%= p.getNome() %>  </option>
                                        <%
                                                }
                                        %>
        
                                    </select>	
                                </label>
                                
                                <label> Tipo Restrição <br>
                                    <select name="tipo" id="tipo" class="input-xxlarge" >
                                        <option selected>Selecione uma opção</option> 
                                        <option> Alérgica </option>
                                        <option> Medicamento </option>
                                    </select>
                                </label>
                                <label>Descrição Restrição<br />
                                    <textarea id="descricao" name="descricao" class="input-xxlarge" style="resize:none;"> </textarea>	
                                </label>
				
			
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = 'RestricaoControl?cmd=ret' ">Cancelar</button>
				
                        </div>

                    </fieldset>
               
			</form>
    </div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>
