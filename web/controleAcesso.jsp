<%-- 
    Document   : controleAcesso
    Created on : Oct 2, 2012, 12:44:17 PM
    Author     : emilianoeloi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    /// Recuperr Dados Postados
    Boolean autenticado = false;
    String usuario = request.getParameter("login");
    String senha = request.getParameter("password");
    
    /// Verificar Usuário e Senha
    if(usuario == "vilmar" && senha=="B1shop"){
        autenticado = false;
    }else{
        autenticado = false;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
