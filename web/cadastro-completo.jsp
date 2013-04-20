<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" 
        import="pessoa.*" %>

<!-- CABECALHO -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="home" />
</jsp:include>

<jsp:useBean id="pessoaPrimeiroCadastro" scope="request" class="pessoa.PessoaBean" />

  <div class="row">
      <div class="span4">
          <h1>Cadastro Completo</h1>
    <form id="home-cadastro" method="post" action="PessoaController" >
        <input type="hidden" id="acao" name="acao" value="primeiro-cadastro" />
        <label>
            Código:
            <input type="text" value="<%=pessoaPrimeiroCadastro.getCodigo()%>" readonly="readonly" />
        </label>    
            
        <label>
            E-mail:
            <input type="text" id="email" name="email" value="<%=pessoaPrimeiroCadastro.getEmail()%>" />
        </label>
        <label>
            Nome:
            <input type="text" id="nome" name="nome" value="<%=pessoaPrimeiroCadastro.getNome()%>" />
        </label>
        <div style="text-align: right">
        <input type="submit" value="Cadastrar" class="btn btn-primary" />
        </div>
    </form>
      </div>
  </div>
<!-- RODAPE -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>