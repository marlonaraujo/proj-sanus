
<%@page  contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="pessoa.*"
        import="cirurgia.*"
        import="java.text.*"
        import="java.util.List"
        import="java.util.*"
        import="medico.MedicoBean"
%>


<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>


<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>

    <jsp:useBean id="medicosLista" scope="request" class="List" />
<jsp:useBean id="pacientesLista" scope="request" class="List" />
<jsp:useBean id="codigo" class="cirurgia.CirurgiaDAO"/>
<jsp:useBean id="existeMedico" scope="request" class="MedicoBean" />

    <div class="span9" style="min-height: 500px">
        <h2>Médico</h2>
<form class="form-cirurgia" method="POST" action="CirurgiaControl?cmd=salvar">
     
                    <fieldset>
                        <legend>Cadastro de Cirurgia</legend>
                                <label>Código<br />
                                    <input type="text" id="codigo" name="codigo" class="input-xxlarge" readonly="readonly" value="<%= codigo.ultimoRegistro() %>">	
                                </label>
                                
                                <label> Nome Cirurgia <br>
                                    <input type="text" id="nome" name="nome" class="input-xxlarge">
                                </label>
                
 
                                <label>Médico<br />
                                    <select name="crm" id="crm" class="input-xxlarge" readonly>
                                        <option selected value="<%= existeMedico.getCodigo() %>" readonly><%= existeMedico.getNome() %> </option>  
                                            
                                            
                                    </select>
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
        
                                <label>Data Cirurgia<br />
                                    <input type="text" id="data" name="data" class="input-xlarge campo-data">	
                                </label>
                                <label>Descrição da Cirurgia<br />
                                    <div class="controls">
                                    <textarea class="input-xxlarge" id="descricao" name="descricao" style="resize:none;"></textarea>	
                                    </div>
                                </label>
                        
			
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = 'CirurgiaControl?cmd=ret' ">Cancelar</button>
				
                        </div>
                    </fieldset>
			</form>
                                
     
    </div>

<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>