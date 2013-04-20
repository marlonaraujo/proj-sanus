<%-- 
    Document   : restricaoSelecionada
    Created on : Oct 14, 2012, 10:15:45 AM
    Author     : Administrador
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="restricao.*"
        import="java.util.*"
%>

<!-- incluindo o cabecalho -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="principal" />
</jsp:include>


<div class="row">
    <!-- MENU -->
    <jsp:include page="menu.jsp" flush="true">
        <jsp:param name="pagina" value="<%=request.getParameter("pagina")%>" />
    </jsp:include>

<jsp:useBean id="restricao" scope="request" class="restricao.RestricaoBean" />
<jsp:useBean id="pacientesLista" scope="request" class="List" />
<jsp:useBean id="codPaciente" scope="request" class="List" />

<%
    String tipo = restricao.getTipo();
%>


    <div class="span9" style="min-height: 500px">
        <h2>Restrição</h2>
<form class="frm-cirurgia" method="POST" action="RestricaoControl?cmd=atualizar">
                    
                    <fieldset>
                        <legend>Cadastro de Restrição</legend>
                                <label>Código<br />
                                    <input type="text" id="codigo" name="codigo" class="input-xxlarge" value="<% out.print(restricao.getCodigo()); %>" readonly="readonly">	
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
                                
                                
                                <label> Tipo Restrição <br>
                                    <select name="tipo" id="tipo" class="input-xxlarge" >
                                        <%
                                            if(tipo.equals("Alérgica")){
                                        %>
                                        <option selected>Alérgica</option> 
                                        <option> Medicamento </option>
                                        <%
                                            }else{
                                        %>
                                        <option selected>Medicamento</option> 
                                        <option> Alérgica </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </label>
                                <label>Descrição Restrição<br />
                                    <textarea id="descricao" name="descricao" class="input-xxlarge" style="resize:none;"><% out.print(restricao.getDescricao()); %></textarea>	
                                </label>
				
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = '/ProntuarioDigital-master Novo/principal.jsp' ">Cancelar</button>
				
                        </div>
                    </fieldset>
			</form>
    </div>
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>