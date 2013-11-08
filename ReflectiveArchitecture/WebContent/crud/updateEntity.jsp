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
<title>Update Entity</title>
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
			<form action="UpdateEntityServlet" method="post" id="form" onsubmit="return checkFields(this);">
				<table class="table">
					<thead>
						<tr>
							<c:forEach var="attribute" items="${attributeValues }">
								<th>${attribute.key }</th>
							</c:forEach>
						</tr>
					</thead>

					<tbody>
						<tr>
							<c:forEach var="attribute" items="${attributeValues }">
								<c:set var="isDate" value="0"></c:set>
								<c:forEach var="dateAttribute" items="${dateAttributes }">
									<c:if test="${attribute.key eq dateAttribute }">
										<c:set var="isDate" value="1"></c:set>
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${isDate eq 1 }">
										<td>
										<input type="text" name="${attribute.key }"	class="date" value="${attribute.value }" >
										</td>
									</c:when>
									<c:otherwise>
										<td>
										<input type="text" name="${attribute.key }" class="input" value="${attribute.value }" >
										</td>
									</c:otherwise>
								</c:choose>								
							</c:forEach>
							<td>
								<input type="hidden" name="updateType" value="update" />
								<input type="hidden" name="entityId" value="${entityId }" /> 
								<input type="hidden" name="entityName" value="${entityName }" /> 
								<input type="submit" value="Update ${entityName }" class="btn btn-lg btn-success" />
							</td>
					</tbody>
				</table>				
			</form>
		</div>

		<div class="footer">
			<p>&copy; Fatec 2013</p>
		</div>
	</div>
</body>

<script type="text/javascript">
	function checkFields(form){
		with(form){
			<c:forEach var="attribute" items="${attributeList }">
			if((${attribute}).value ===""){
				alert("Campo ${attribute } deve ser preenchido!");
				(${attribute}).focus();
				return false;
				}
			</c:forEach>
			}
		}
	</script>

</html>