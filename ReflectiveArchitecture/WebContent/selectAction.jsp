<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="bootstrap-3.0.0/dist/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="bootstrap-3.0.0/examples/jumbotron-narrow/jumbotron-narrow.css">

<title>Select action</title>
</head>
<body>
	<div class="form-horizontal">
	<p>Selecionar uma entidade e uma ação.</p>
		<form action="SelectAction" method="post">
			<div class="form-group">
				<label class="control-label" for="entitySelect">Selecione uma entidade: </label> 
				<select name="entityName" class="dropdown-toggle" id="entitySelect">
					<c:forEach var="entity" items="${entityList }">
						<option value="${entity }">${entity }</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label class="control-label" for="actionSelect">Selecione uma ação: </label> 
				<select name="action" class="dropdown-toggle" id="actionSelect" onchange="return verifySelection(this.value);">
					<option value="insert" id="insert">Adicionar</option>
					<option value="select" id="select">Selecionar</option>
					<option value="delete" id="delete">Remover</option>					
				</select>
			</div>
			
			<div id="insertIdField" class="form-group">					
			</div>			
			
			<div class="button-group">
				<input type="submit" value="Submeter Ação" class="btn btn-lg btn-success">
			</div>
		
		</form>
	</div>
</body>

<script type="text/javascript">
	function verifySelection(value) {
		with (value) {
			if (value === "select") {
				document.getElementById('insertIdField').innerHTML = "<label class='control-label' for='entityId'>Informe o Id da entidade:</label>"
						+ "<input type='text' name='entityId' class='input' id='entityId'/>";
				return false;
			
			}else{
				document.getElementById('insertIdField').innerHTML = "";
				return false;
			}
		}
	}
</script>

</html>