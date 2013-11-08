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
<title>Read Entity</title>
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

		<div class="bordered-table">			
				<table class="table">
					<thead>
						<tr>
						<th>Id</th>
							<c:forEach var="attribute" items="${attributeList }">
								<th>${attribute }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="map" items="${entityHashMap }">
							<c:set var="entityId" value="${map.key }"></c:set>
							<tr>
							<td>${map.key }</td>
								<c:forEach var="attribute" items="${map.value }">
									<td>${attribute.value }</td>
								</c:forEach>
								<td>
								<form action="UpdateEntityServlet" method="post" id="form">
									<input type="hidden" name="entityId" value="${entityId }" />
									<input type="hidden" name="updateType" value="preUpdate" /> 
									<input type="hidden" name="entityName" value="${entityName }" /> 
									<input type="submit" value="Update ${entityName }" class="btn btn-success" />
								</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>

		<div class="footer">
			<p>&copy; Fatec 2013</p>
		</div>
	</div>
</body>
</html>