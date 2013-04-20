<%-- 
    Document   : boxcadastro
    Created on : Oct 21, 2012, 5:04:40 PM
    Author     : emilianoeloi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="span3 well" style="float: right">
    <h2>Cadastre-se</h2>
    <fieldset>
    <form id="box-cadastro" method="post" action="PessoaController" >
        <input type="hidden" id="acao" name="acao" value="primeiro-cadastro" />
        <label>
            E-mail:
            <input type="text" id="email" name="email" />
        </label>
        <label>
            Nome:
            <input type="text" id="nome" name="nome" />
        </label>
        <label>
            Perfil:
            <select id="perfil" name="perfil">
                <option value="">Escolha um perfil</option>
                <option value="medico">Médico</option>
                <option value="paciente">Paciente</option>
            </select>    
        </label>
        <div style="text-align: right">
        <input type="submit" value="Iniciar cadastro..." class="btn btn-primary" />
        </div>
    </form>
    </fieldset>
    <script>
    $().ready(function() {
        
        $('#box-cadastro').validate({
            rules:{
                email:{
                    required: true,
                    email:true,
                    verifyLogin: true
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
                    email: 'Inválido',
                    verifyLogin: 'já cadastrado'
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