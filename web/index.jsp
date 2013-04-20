<%-- 
    Document   : INDEX
    Created on : Oct 21, 2012, 1:29:51 PM
    Author     : emilianoeloi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- CABECALHO -->
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="home" />
</jsp:include>

<!-- CONTEUDO -->

  <!-- Main hero unit for a primary marketing message or call to action -->
  <div class="hero-unit">
    <h1>Sanus - Saúde no controle</h1>
    <p>O objetivo do nosso Sistema é centralizar informações sobre o histórico de saúde do paciente e permitir o acompanhamento por qualquer médico.
A rede armazenará informações úteis para o procedimento de atendimento, disponibilizando dados como tipo sanguíneo do paciente, alergias,
cirurgias dentre outras informações relevantes.</p>
    <p><a class="btn btn-primary btn-large" href="informacoes.jsp">Saiba mais</a></p>
  </div>

         <!-- BOX CADASTRO -->
       <jsp:include page="boxcadastro.jsp" flush="true">
            <jsp:param name="pagina" value="info" />
        </jsp:include>
     

  <!-- Example row of columns -->
  <div class="row">
    <div class="span4">
      <h2>Compartilhamento</h2>
      <p>Qualquer médico em qualquer lugar pode acessar os dados de um paciente específico. Isto garante qualidade, agilidade e eficiência mesmo em consultas 
          com médicos diferentes.</p>
      <p><a class="btn" href="informacoes.jsp#comp">Mais sobre compartilhamento &raquo;</a></p>
    </div>
       <div class="span4">
      <h2>Tecnologia</h2>
      <p>A programação orientada por objetos, o modelagem em banco de dados, a análise e desenvolvimento de sistemas e o desenvolvimento em Web são
         o carro chefe do desenvolvimento do nosso projeto</p>
      <p><a class="btn" href="informacoes.jsp#hist">Mais sobre tecnologia &raquo;</a></p>
    </div>  
    <div class="span4">
      <h2>Saúde</h2>
      <p>Esse foi o eixo tranversal escolhido para o Projeto e tem como objetivo principal oferecer uma ferramenta barada para controle da saúde das pessoas.</p>
      <p><a class="btn" href="informacoes.jsp#hist">Mais sobre tecnologia &raquo;</a></p>
    </div>
    <div class="span4">
      <h2>Histórico</h2>
      <p>Além dos limites da clínica. Facilita o cruzamento de informações entre familiares auxiliando no tratamento de doenças hereditárias.</p>
      <p><a class="btn" href="informacoes.jsp#hist">Mais sobre histórico &raquo;</a></p>
    </div>
   
   <jsp:include page="boxpublicidade.jsp" flush="true">
            <jsp:param name="pagina" value="info" />
        </jsp:include>
     
  </div>

<!-- RODAPE -->
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>