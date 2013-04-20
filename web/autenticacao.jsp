<%-- 
    Document   : autenticacao
    Created on : Oct 2, 2012, 10:41:40 AM
    Author     : emilianoeloi
--%>
<%@page contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prontuário Digital - Autenticação</title>
		<link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="mycss/style.css" />
    </head>
    <body>
        <div class="container">
            <h1>Protótipo - tela de autenticação</h1>
            <form method="POST">
                <fieldset>
                    <legend>Login</legend>
                    <label>Usuário
                        <input type="text" id="login" name="login" />
                    </label>
                    <label>Senha
                        <input type="password" id="password" name="password" />
                    </label>
                    <button type="submit" class="btn">Entrar</button>
            </form>
            
        </div>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
