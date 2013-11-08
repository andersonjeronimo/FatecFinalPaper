<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>

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
		<jsp:include page="listDatabaseTables.jsp"></jsp:include>
		<hr>
		<br>
		<jsp:include page="listPersistentObjects.jsp"></jsp:include>
		<hr>
		<br>
		<jsp:include page="selectAction.jsp"></jsp:include>	
		<hr>
		<br>
		<div class="footer">
			<p>&copy; Fatec 2013</p>
		</div>
	</div>
</body>
</html>