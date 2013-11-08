<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Database Connection Configuration</title>
<link rel="stylesheet" href="bootstrap-3.0.0/dist/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="bootstrap-3.0.0/examples/jumbotron-narrow/jumbotron-narrow.css">

</head>
<body>
	<div class="container">
		
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="index.jsp">Início</a></li>
				<li><a href="connectionConfiguration.jsp">Configurações</a></li>
				<li><a href="#">Fatec</a></li>
				<li><a href="#">Sobre o Projeto</a></li>
			</ul>
			<h3 class="text-muted">Reflective Architecture</h3>
		</div>

		<div class="form-horizontal">
		
		<form action="ConnectionConfiguration" method="post" onsubmit="return checkFields(this);">

				<div class="form-group">
					<label class="control-label" for="server">Servidor</label> 
					<input type="text" name="server" id="server" value="localhost" class="input-lg" />
				</div>
				
				<div class="form-group">
					<label class="control-label" for="port">Porta</label> 
					<input type="text" name="port" id="port" value="3306" class="input-lg" />
				</div>
				
				<div class="form-group">
					<label class="control-label" for="schema">Esquema</label> 
					<input type="text" name="schema" id="schema" class="input-lg" />
				</div>
				
				<div class="form-group">
					<label class="control-label" for="username">Usuário</label> 
					<input type="text" name="username" id="username" value="root" class="input-lg" />
				</div>
				
				<div class="form-group">
					<label class="control-label" for="password">Senha</label> 
					<input type="password" name="password" id="password" value="mysql" class="input-lg" />
				</div>

				<input type="submit" value="Configurar Conexão" class="btn btn-lg btn-success">
		
		</form>			
		</div>

		<div class="footer">
			<p>&copy; Fatec 2013</p>
		</div>
	</div>
</body>

<!-- Função Javascript para teste de preenchimento dos campos do formulário -->
<script type="text/javascript">
	function checkFields(form){
		with(form){
			if(server.value ===""){
				alert("Campo 'Servidor' deve ser preenchido!");
				server.focus();
				return false;
				}
			if(port.value ===""){
				alert("Campo 'Porta' deve ser preenchido!");
				port.focus();
				return false;
				}			
			if(schema.value ===""){
				alert("Campo 'Esquema' deve ser preenchido!");
				schema.focus();
				return false;
				}			
			if(username.value ===""){
				alert("Campo 'Usuário' deve ser preenchido!");
				username.focus();
				return false;
				}
			if(password.value ===""){
				alert("Campo 'Senha' deve ser preenchido!");
				password.focus();
				return false;
				}			
			}
		}
	</script>
</html>