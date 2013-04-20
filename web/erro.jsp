<%-- 
    Document   : erro
    Created on : Oct 2, 2012, 1:40:19 PM
    Author     : emilianoeloi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prontuário Digital - Erro</title>
    </head>
    <body>
        <h1>Erro!</h1>
        <strong><%=exception.toString()%></strong>
    </body>
</html>
