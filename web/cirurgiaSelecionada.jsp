<%-- 
    Document   : cirurgiaSelecionada
    Created on : Oct 13, 2012, 8:20:34 PM
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


<!-- incluindo o cabecalho -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>


    
<jsp:useBean id="cirurgia" scope="request" class="cirurgia.CirurgiaBean" />
<jsp:useBean id="data" scope="request" class="data.ConverteData" />

<jsp:useBean id="medicosLista" scope="request" class="List" />
<jsp:useBean id="pacientesLista" scope="request" class="List" />
<jsp:useBean id="codMedico" scope="request" class="List" />
<jsp:useBean id="codPaciente" scope="request" class="List" />


<div class="row">
    <!-- MENU -->
<jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>
    <div class="span9" style="min-height: 500px">
        <h2>Médico</h2>
<form class="frm-cirurgia-alt" method="POST" action="CirurgiaControl?cmd=atualizar">
                    
                    <fieldset>
                        <legend>Cadastro de Cirurgia</legend>
                                <label>Código<br />
                                    <input type="text" id="codigo" name="codigo" class="input-xxlarge" value="<% out.print(cirurgia.getCodigo()); %>" readonly="readonly">	
                                </label>
                                
                                <label> Nome Cirurgia <br>
                                    <input type="text" id="nome" name="nome" class="input-xxlarge" value="<% out.print(cirurgia.getCirurgia()); %>">
                                </label>
                                
                                
                                <label>Médico<br />
                                     <select id="crm" name="crm" class="input-xxlarge">
                                         
                                     <%  
                                             
                                              for(Iterator i = medicosLista.iterator(); i.hasNext();) {
                                                    PessoaBean p = (PessoaBean)i.next();
                                                    for(int j = 0; j < codMedico.size(); j ++){
                                                        if (p.getCodigo() == codMedico.get(j)){
                                            %>
        
                                            <option value="<% out.print(codMedico.get(j)); %>" selected> <% out.print(p.getNome()); %>  </option>
                                        <%
                                                        break;
                                                        }else{
                                                    
                                        %>
                                            <option value="<%= p.getCodigo() %>"> <%= p.getNome() %>  </option>
                                        <%              }      
                                                    }
                                                    
                                              }
                                        %>  
                                    </select>
                                    </label>
				<label>Paciente<br />
                                    <select id="cpf" name="cpf" class="input-xxlarge">
                                         
                                      <%  
                                             
                                              for(Iterator i = pacientesLista.iterator(); i.hasNext();) {
                                                    PessoaBean p = (PessoaBean)i.next();
                                                    for(int j = 0; j < codPaciente.size(); j ++){
                                                        if (p.getCodigo() == codPaciente.get(j)){
                                            %>
        
                                            <option value="<% out.print(codPaciente.get(j)); %>" selected> <% out.print(p.getNome()); %>  </option>
                                        <%
                                                        break;
                                                        }else{
                                                    
                                        %>
                                            <option value="<%= p.getCodigo() %>"> <%= p.getNome() %>  </option>
                                        <%              }      
                                                    }
                                                    
                                              }
                                        %>
                                    </select>
                                    </label>
                                
                                
                                <label>Data Cirurgia<br />
                                    <input type="text" id="data" name="data" class="input-xxlarge" value="<% out.print(data.converteDataTexto(cirurgia.getData())); %>">	
                                </label>
                                <label>Descrição da Cirurgia<br />
                                    <div class="controls">
                                    <textarea class="input-xxlarge" id="descricao" name="descricao" style="resize:none;"><% out.print(cirurgia.getDescricao()); %></textarea>	
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
