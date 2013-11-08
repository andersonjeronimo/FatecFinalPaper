<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery-datepicker.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/jquery-ui-1.10.3.custom.css" type="text/css">
<link rel="stylesheet" href="bootstrap-3.0.0/dist/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="bootstrap-3.0.0/examples/jumbotron-narrow/jumbotron-narrow.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Entity</title>
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
			<form action="CreateEntityServlet" method="post" id="form" onsubmit="return checkFields(this);">
				<c:forEach var="attribute" items="${attributeList }">
					<c:set var="isDate" value="0"></c:set>
					<c:forEach var="dateAttribute" items="${dateAttributes }">
						<c:if test="${attribute eq dateAttribute }">
							<c:set var="isDate" value="1"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isDate eq 1 }">
							<div class="form-group">
								<label class="control-label" for=${attribute }>${attribute }</label> 
								<input type="text" name="${attribute }" class="date"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<label class="control-label" for=${attribute }>${attribute }</label> 
								<input type="text" name="${attribute }" class="input-lg" />
							</div>
						</c:otherwise>						
					</c:choose>
					<hr>
				</c:forEach>
				
				<input type="hidden" name="entityName" value="${entityName }" />				
				<input type="submit" value="Cadastrar ${entityName }" class="btn btn-lg btn-success"/>
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
			<c:forEach var="attribute" items="${attributeList }">
			if((${attribute}).value ===""){
				alert("Campo ${attribute} deve ser preenchido!");
				(${attribute}).focus();
				return false;
				}
			</c:forEach>
			}
		}
	</script>

</html>