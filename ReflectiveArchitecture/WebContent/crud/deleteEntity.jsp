<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap-3.0.0/dist/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="bootstrap-3.0.0/examples/jumbotron-narrow/jumbotron-narrow.css">
<title>Delete Entity</title>
</head>
<body>
	<div class="container">
		
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="index.jsp">Início</a></li>
				<li class="disabled"><a href="#">Configurações</a></li>
				<li><a href="#">Fatec</a></li>
				<li><a href="#">Sobre o Projeto</a></li>
			</ul>
			<h3 class="text-muted">Reflective Architecture</h3>
		</div>
		
		<div class="form-horizontal">
			<form action="DeleteEntityServlet" method="post" id="deleteForm" onsubmit="return checkFields(this);">
				<div class="form-group">
					<label class="control-label" for="entityId">Inserir ID</label> 
					<input type="text" name="entityId" class="input-lg" id="entityId">					
				</div>
				
				<div class="form-group">					
					<label class="control-label" for="entityName">Inserir Nome da Entidade</label> 
					<input type="text" name="entityName" class="input-lg" id="entityName" />
				</div>
				
				<div class="button-group">
					<input type="submit" value="Delete" class="btn btn-lg btn-danger" />
				</div>
			</form>
		</div>
		
		<div class="footer">
			<p>&copy; Fatec 2013</p>
		</div>
	
	</div>
</body>
<!-- Função Javascript para teste de preenchimento dos campos do formulário -->
<script type="text/javascript">
	function checkFields(deleteForm){
		with(deleteForm){
			if(entityId.value ===""){
				alert("Campo Id deve ser preenchido!");
				entityId.focus();
				return false;
				}
			if(entityName.value ===""){
				alert("Campo Nome deve ser preenchido!");
				entityName.focus();
				return false;
				}			
			}
		}
	</script>
</html>