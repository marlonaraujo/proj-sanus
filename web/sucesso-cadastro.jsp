<%-- 
    Document   : faleconosco
    Created on : Oct 21, 2012, 4:23:26 PM
    Author     : emilianoeloi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="contato" />
</jsp:include>
<div class="row">
<div class="span7" style="min-height: 500px">
    <div class="hero-unit">
    <h1 align="center">Sucesso!</h1>
    <p></p>
    <p align="center">Seu cadastro foi realizado com sucesso. Aproveite o Sanus.</p>

  </div>
</div>
    
       <!-- BOX CADASTRO -->
       <jsp:include page="boxpublicidade.jsp" flush="true">
            <jsp:param name="pagina" value="info" />
        </jsp:include>
    <script type="text/javascript">

    $(document).ready(function() {
        console.log('ready contato');//      $("#commentForm").validate();
    });
    </script>        
</div>
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>