<%-- 
    Document   : faleconosco
    Created on : Oct 21, 2012, 4:23:26 PM
    Author     : emilianoeloi
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="perfil" scope="request" class="String"></jsp:useBean> 
<jsp:useBean id="pessoaPrimeiroCadastro" scope="request" class="pessoa.PessoaBean" />
<jsp:useBean id="data" scope="request" class="data.ConverteData" />
<jsp:include page="cabecalho.jsp" flush="true">
    <jsp:param name="pagina" value="contato" />
</jsp:include>
<div class="row">
    <div class="span7" style="min-height: 500px">
    <h1>Escolher Perfil</h1>
    <form class="form-horizontal" id="cadastro-completo" name="cadastro-completo" method="post" onsubmit="enviarDados();" action="PessoaController">
        <input type="hidden" value="completarcadastro" id="acao" name="acao" />
        <input type="hidden" value="<%=perfil%>" id="perfil" name="perfil" />
        <input type="hidden" value="<%=pessoaPrimeiroCadastro.getCodigo()%>" id="codigo" name="codigo" />
        <input type="hidden" value="<%=pessoaPrimeiroCadastro.getCodigo()%>" id="codigo-pessoa" name="codigo-pessoa" />
    <fieldset>

        <legend>Escolha seu perfil para continuar o cadastro</legend>
<ul class="nav nav-tabs">
  <li <% if (perfil.equalsIgnoreCase("medico")){ %> class="active"<% } %> >
      <a href="javascript://" onclick="trocarPerfil('medico');"><i class="icon-user"></i>Médico</a>
  </li>
  <li <% if (perfil.equalsIgnoreCase("paciente")){ %> class="active"<% } %> >
      <a href="javascript://" onclick="trocarPerfil('paciente');"><i class="icon-user"></i>Paciente</a>
  </li>
</ul>
      <div class="control-group">
            <label class="control-label" for="nome">
                Nome: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%=pessoaPrimeiroCadastro.getNome()%>" class="input-xlarge" placeholder="Digite seu nome…" id="nome" name="nome" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="email">
                E-mail: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%=pessoaPrimeiroCadastro.getEmail()%>" class="input-xlarge" placeholder="Digite seu e-mail…" id="email" name="email" readonly />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="senha">
                Senha: 
            </label>  
            <div class="controls">    
                <input type="password" value="" class="input-xlarge" placeholder="Digite sua senha..." id="senha" name="senha" />
            </div>
      </div>
      <div class="control-group">
            <label class="control-label" for="confirmaca-senha">
                Confirmação da Senha: 
            </label>  
            <div class="controls">    
                <input type="password" value="" class="input-xlarge" placeholder="Confirme sua senha" id="confirmaca-senha" name="confirmaca-senha"/>
            </div>
      </div>      
      <div class="control-group">
            <label class="control-label" for="cpf">
                CPF: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%=pessoaPrimeiroCadastro.getCpf()%>" class="input-xlarge campo-cpf" placeholder="Digite seu CPF…" id="cpf" name="cpf" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="id">
                RG: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%//=pessoaPrimeiroCadastro.getRg()%>" class="input-xlarge" placeholder="Digite seu RG…" id="rg" name="rg" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="data-nascimento">
                Data de Nascimento: 
            </label>  
            <div class="controls">    
                <input type="text" value="<%=data.converteDataTexto(pessoaPrimeiroCadastro.getDataNascimento())%>" class="input-xlarge campo-data" placeholder="Digite sua data de nascimento…" id="data-nascimento" name="data-nascimento" />
            </div>
       </div>
            
      <% if (perfil.equalsIgnoreCase("medico")){ %>

      <div class="control-group">
            <label class="control-label" for="crm">
                CRM: 
            </label>  
            <div class="controls">    
                <input type="text" value="" class="input-xlarge" placeholder="Digite seu CRM…" id="crm" name="crm" />
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="nome">
                Hospital: 
            </label>  
            <div class="controls">    
                <jsp:include page="hospitalCombo.jsp" flush="true">
                    <jsp:param name="codigo_hospital" value="" />
                </jsp:include>
            </div>
       </div>
      <div class="control-group">
            <label class="control-label" for="nome">
                Especialidade 
            </label>  
            <div class="controls">    
                <jsp:include page="especialidadeCombo.jsp" flush="true">
                    <jsp:param name="codigo_hospital" value="" />
                </jsp:include>
            </div>
       </div>
      <% } %>
        <div class="control-group">
            <div class="controls">
                <input type="submit" class="btn btn-primary" value="Cadastrar" />
                <input type="button" class="btn btn-danger" onclick="window.location = 'PessoaController?acao=carregar' " value="Cancelar" />
            </div>
        </div>
    </fieldset>
    </form>    
    </div>
         <!-- BOX CADASTRO -->
       <jsp:include page="boxpublicidade.jsp" flush="true">
            <jsp:param name="pagina" value="info" />
        </jsp:include>
    <script type="text/javascript">

        function trocarPerfil(perfil){
            $("#cadastro-completo #acao").val('escolherperfil');
            $("#cadastro-completo #perfil").val(perfil);
            window.location = "PessoaController?" + $("#cadastro-completo").serialize();
            //$("#cadastro-completo").submit();
        }
        function enviarDados(){
            switch($("#cadastro-completo #perfil").val()){
                case 'paciente':
                    $("#cadastro-completo").attr('action','PacienteController');
                    break;
                case 'medico':
                    $("#cadastro-completo").attr('action','MedicoController');
                    break;
                case 'pessoa':
                default:    
                    $("#cadastro-completo").attr('action','PessoaController');
                    break;
            }
        }

$().ready(function() {
    
        
        
        $('#cadastro-completo').validate({
            rules:{
                email:{
                    required: true,
                    email:true
                },
                nome:{
                    required: true
                },
                senha:{
                    required: true
                }
            },
            messages:{
                email:{
                    required: 'Obrigatório',
                    email: 'Inválido'
                },
                nome:{
                    required: 'Obrigatório'
                },
                senha: {
                    required: 'Obrigatório'
                }
            }
        });
    }); 
    </script>        
</div>
<jsp:include page="rodape.jsp" flush="true">
    <jsp:param name="login" value="generico" />
</jsp:include>