<%@page info="Cadastro de hospitais" contentType="text/html" errorPage="erro.jsp" pageEncoding="ISO-8859-1" 
        import="hospital.*" 
        import="javax.servlet.http.Cookie"
        import="javax.servlet.http.HttpSession"
        import="java.text.SimpleDateFormat"
        import="java.util.Date" %>
<!DOCTYPE HTML>

<html>
	<head>
		<link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="mycss/style.css" />
                <title>Prontuário Digital</title>
	</head>
            <body>
                    <%
                    /// Recuperar Dados de Acesso
                    Cookie ckUsuario = null;
                    Cookie listaCookies[] = request.getCookies();
                    if(listaCookies != null){
                        for(int i=0; i < listaCookies.length; i++){
                            if(listaCookies[i].getName().equals("usuario")){
                               ckUsuario = listaCookies[i]; 
                               break;
                            }
                        }
                    }
                    
                    /// Recuperando acesso da Sessão
                    HttpSession sessao = request.getSession();
                    String seUsuario = (String)sessao.getAttribute("usuario");
                    String dataLogin = "";
                    if(seUsuario != null){
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
                        dataLogin = formato.format(new Date(sessao.getCreationTime()));
                    }
                    if(request.getParameter("codigo") != null){
                        String codigo = request.getParameter("codigo");
                        String nome = request.getParameter("nome");

                        /// Construir objeto Hospital
                        HospitalBean h = new HospitalBean();
                        if(codigo != ""){
                            h.setCodigo( Integer.parseInt(codigo) );
                        }
                        h.setNome(nome);

                        /// Gravar no Banco
                        HospitalDAO dao = new HospitalDAO();
                        dao.cadastrar(h);
                    }
                    sessao.removeAttribute("usuario");
                    %>
                </div>
		<div class="container">
                    <%@include file="cabecalho.jsp" %>
                    <%@include file="menu.jsp" %>
                    <h1>Prontuário Digital | Usuário logado: 
                        <strong><% out.print(ckUsuario.getValue()); %></strong> |
                        <strong><% out.print(seUsuario); %> |
                        <strong><% out.print(dataLogin); %></h1>
			<legend>Cadastro Dados de Hospitais</legend>
			<form class="form-horizontal" method="post" action="?">
                            
                                <label>Código<br />
                                    <input readonly="readonly" type="text" id="codigo" name="codigo" class="input-xxlarge">	
                                </label>
				<label>Nome do Hospital<br />
                                    <input type="text" id="nome" name="nome" class="input-xxlarge">	
                                </label>
			
			<div style="text-align: center">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="button" class="btn btn-danger" onclick="window.location = '/ProntuarioDigital-master Novo/principal.jsp' ">Cancelar</button>
				
                        </div>
			</form>
		</div>
             
		<script src="js/bootstrap.js"> 
			
		
		</script>
	</body>
</html>