<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*"
        import="java.util.List"
        import="java.util.*"%>


<jsp:useBean id="pessoaSelecionada" scope="request" class="pessoa.PessoaBean"></jsp:useBean>
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
        <form class="form-pessoa" method="POST" action="PessoaController">
        <input type="hidden" id="acao" name="acao" value="<% if(pessoaSelecionada.getCodigo() == 0 ) {%>cadastrar<%} else {%>alterar<%}%>" />
        <fieldset>
            <legend><% if(pessoaSelecionada.getCodigo() == 0 ) {%> Cadastrar <%} else {%> Editar <%}%> Pessoa</legend>
                    <label>C�digo<br />
                        <input readonly="readonly" type="text" id="codigo" name="codigo" class="input-xxlarge" 
                         value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(pessoaSelecionada.getCodigo()); } %>">	
                    </label>
                    <label>Nome<br />
                        <input type="text" id="nome" name="nome" class="input-xxlarge"
                        value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(pessoaSelecionada.getNome()); } %>">
                    </label>
                    <label>CPF<br />
                        <input type="text" id="cpf" name="cpf" class="input-xlarge campo-cpf" 
                        value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(pessoaSelecionada.getCpf()); } %>">
                    </label>
                    <label>E-mail<br />
                        <input type="text" id="email" name="email" class="input-xxlarge" 
                        value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(pessoaSelecionada.getEmail()); } %>">       
                    </label>
                    <label>RG<br />
                        <input type="text" id="id" name="id" class="input-xxlarge" value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(pessoaSelecionada.getRg()); } %>">	
                    </label>
                    <label>Data de nascimento<br />
                        <input type="text" id="data-nascimento" name="data-nascimento" class="input-xlarge campo-data" value="<% if(pessoaSelecionada.getCodigo() == 0 ){ out.print(""); } else { out.print(data.converteDataTexto(pessoaSelecionada.getDataNascimento()) ); } %>">	
                    </label>
                    <label>Senha<br />
                        
                        <% if(pessoaSelecionada.getCodigo() == 0 ) {%>
                        <input type="password" id="senha" name="senha" class="input-xxlarge" value="">
                    </label>
                    <label>Confirmar senha:<br >
                        <input type="password" id="confirmar-senha" name="confirmar-senha" class="input-xxlarge" value="">
                    <%} else {%>
                        <button type="button" class="btn btn-warning" onclick="alert('Alterar a senha em desenvolvimento.')">Alterar Senha</button>
                    <%}%>                    
                    </label>

                    <div style="text-align: center">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                    <button type="button" class="btn btn-danger" onclick="window.location = 'PessoaController'">Cancelar</button>

            </div>
        </fieldset>
        </form>
    </div> 
<!-- inclusao do rodape -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>