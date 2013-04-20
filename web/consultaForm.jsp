<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="consulta.*"
        import="java.text.*"
        import="java.util.List"
        import="java.util.*"
        import="medico.MedicoBean"
        
%>



<jsp:useBean id="consultaSelecionado" scope="request" class="consulta.ConsultaBean"></jsp:useBean>

<jsp:useBean id="pacientesLista" scope="request" class="List" />

<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>

<%
    HttpSession sessao = request.getSession();
    PessoaBean pessoa = null;
    String cmd = "";
    try{
        pessoa = (PessoaBean)sessao.getAttribute("pessoaLogada");
        if(pessoa == null)
            response.sendError(403, "Você não tem permissão!");

    }catch(Exception exc){
        response.sendError(403, "Você não tem permissão!");
    }
%>

<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>   
    
    <jsp:useBean id="codigo" class="consulta.ConsultaDAO"/>
    <jsp:useBean id="codPaciente" scope="request" class="List" />
    <jsp:useBean id="existeMedico" scope="request" class="MedicoBean" />
    <jsp:useBean id="data" scope="request" class="data.ConverteData" />
    <jsp:useBean id="medicosLista" scope="request" class="List" />
    <jsp:useBean id="codMedico" scope="request" class="List" />
    
    <div class="span9" style="min-height: 500px">
        <h2>Consulta</h2>
                <form class="form-hospital" method="POST" action="ConsultaControl">
                    <input type="hidden" id="cmd" name="cmd" value="<% if(consultaSelecionado.getCodigo() == 0 ) { cmd = "salvar";%>salvar<%} else {cmd="alterar";%>alterar<%}%>" /> 
                    <fieldset>
                        <legend><% if(consultaSelecionado.getCodigo() == 0 ) {%> Cadastrar <%} else {%> Editar <%}%> Consulta</legend>
                                <label>Código<br />
                                    <input readonly="readonly" type="text" id="codigo" name="codigo" class="input-xxlarge" value="<% if(cmd.equalsIgnoreCase("salvar")){ out.print(codigo.ultimoRegistro()); } else { out.print(consultaSelecionado.getCodigo()); } %>">	
                                </label>
				                                
                                
                                <% if(pessoa.checkPerfil("medico")){ %> 
                                
                                    <label>Médico<br />
                                        <select name="crm" id="crm" class="input-xxlarge" readonly>
                                            <% 
                                                if(consultaSelecionado.getCodigo() == 0 ){
                                            %>
                                            <option selected value="<%= existeMedico.getCodigo() %>" readonly><%= existeMedico.getNome() %> </option>  
                                            
                                            <%  }else{
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
                                                    
                                              }}
                                        %>  
                                            
                                         </select>
                                    </label>
                                    
                                    <label>Paciente<br />
                                          
                                        
                                    <select name="cpf" id="cpf" class="input-xxlarge">
                                        <% if(cmd.equalsIgnoreCase("alterar")){ %>
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
        
                                    
                                        <%}else{%>
                                        
                                        <option selected>Selecione um Paciente </option>
                                                  <%  
                                                
                                                 
                                                for(Iterator i = pacientesLista.iterator(); i.hasNext();) {
                                                    PessoaBean p = (PessoaBean)i.next(); 
                                            %>
        
                                            <option value="<%= p.getCodigo() %>" > <%= p.getNome() %>  </option>
                                        <%
                                                }
                                        %>
                                        
                                

                                
                                <%} } %>
                                </select>
                                </label>
                                
                                
                                <label>Data Consulta<br />
                                    <input type="text" id="data" name="data" class="input-xlarge campo-data" value="<% if(consultaSelecionado.getCodigo() == 0 ){ out.print(""); } else { out.print(data.converteDataTexto(consultaSelecionado.getData())); } %>">	
                                </label>
                                <div class="control-group">
                                    <label class="control-label" for="home-contato-texto">
                                  Descrição da Consulta
                                 </label>
                                         <div class="controls">
                                           <textarea class="input-xxlarge" id="desc" name="desc" ><% if(consultaSelecionado.getCodigo() == 0 ){ out.print(""); } else { out.print(consultaSelecionado.getDescricao()); } %></textarea>
                                             </div>
                                 </div>
			
			<div style="text-align: center">
                       
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = 'ConsultaControl?cmd=listar' ">Cancelar</button>
				
                        </div>
                    </fieldset>
                    
			</form>
               </div> 
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>