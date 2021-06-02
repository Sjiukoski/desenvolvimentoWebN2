<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">
<title>Lista de Clientes</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Academia</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page"
						href="<%=request.getContextPath()%>/list">Clientes</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<h3 class="text-center">Lista de Clientes</h3>
		<hr>
		<div class="container text-left">
			<a class="btn btn-success" href="<%=request.getContextPath()%>/new">Adicionar</a>
		</div>
		<br>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Matrícula</th>
					<th scope="col">Modalidade</th>
					<th scope="col">Bairro</th>
					<th scope="col">Cidade</th>
					<th scope="col">CEP</th>
					<th scope="col">Estado</th>
					<th scope="col">Logradouro</th>
					<th scope="col">Telefone</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="client" items="${ listClient }">
					<tr>
						<td><c:out value="${ client.name }"></c:out></td>
						<td><c:out value="${ client.registration }"></c:out></td>
						<td><c:out value="${ client.modality }"></c:out></td>
						<td><c:out value="${ client.district }"></c:out></td>
						<td><c:out value="${ client.city }"></c:out></td>
						<td><c:out value="${ client.cep }"></c:out></td>
						<td><c:out value="${ client.state }"></c:out></td>
						<td><c:out value="${ client.publicPlace }"></c:out></td>
						<td><c:out value="${ client.cellphone }"></c:out></td>
						<td><a href="edit?id=<c:out value='${ client.id }'/>"
							class="btn btn-primary">Editar</a> <a
							href="delete?id=<c:out value='${ client.id }'/>"
							class="btn btn-danger">Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>