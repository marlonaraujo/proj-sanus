<%-- 
    Document   : informacoes
    Created on : Oct 21, 2012, 1:29:51 PM
    Author     : emilianoeloi
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!-- CABECALHO -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="info" />
</jsp:include>

<!-- CONTEUDO -->
                   <div class="row">
            <div class="span12">
      <h1>Informa��es</h1>
      <p>O objetivo do nosso Sistema � centralizar informa��es sobre o hist�rico de sa�de do paciente e permitir o acompanhamento por qualquer m�dico.
O Prontu�rio Digital armazenar� informa��es �teis para o procedimento de atendimento, pois, disponibiliza dados como tipo sangu�neo do paciente, alergias, 
cirurgias dentre outras informa��es relevantes.
</p>
            </div>
        </div>
       <!-- Example row of columns -->
      <div class="row">
       <a name="comp"></a>               
        <div class="span8">
          
          <h2>Compartilhamento</h2>
          
          <p>Qualquer m�dico em qualquer lugar pode acessar os dados de um paciente espec�fico. Isto garante qualidade, agilidade e efici�ncia mesmo em consultas com m�dicos diferentes.</p>
          
        </div>
       
       <!-- BOX CADASTRO -->
       <jsp:include page="boxcadastro.jsp" flush="true">
            <jsp:param name="pagina" value="info" />
        </jsp:include>

          
        <div class="span8">
            <a name="hist"></a>
            <h2>Hist�rico</h2>
          <p>Al�m dos limites da cl�nica. Facilita o cruzamento de informa��es entre familiares auxiliando no tratamento de doen�as heredit�rias.</p>
          
        </div>
      </div>

<!-- RODAPE -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>