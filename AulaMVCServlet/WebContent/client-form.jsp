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
<title>Clientes</title>
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
	<div class="container col-md-5">
		<div class="card">
			<c:if test="${ client != null }">
				<h5 class="card-header">Editar Cliente</h5>
				<form action="update" method="post">
			</c:if>
			<c:if test="${ client == null }">
				<h5 class="card-header">Adicionar novo Cliente</h5>
				<form action="insert" method="post">
			</c:if>
			<div class="card-body">
				<c:if test="${ client != null }">
					<input type="hidden" name="id"
						value="<c:out value='${ client.id }' />" />
				</c:if>
				<div class="mb-3">
					<label for="name" class="form-label">Nome:</label> <input
						type="text" class="form-control" name="name"
						value="<c:out value='${ client.name }' />" />
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Matrícula:</label> <input
						type="number" class="form-control" name="registration"
						value="<c:out value='${ client.registration }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Modalidade:</label> <input
						type="text" class="form-control" name="modality"
						value="<c:out value='${ client.modality }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Bairro:</label> <input
						type="text" class="form-control" name="district"
						value="<c:out value='${ client.district }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Cidade:</label> <input
						type="text" class="form-control" name="city"
						value="<c:out value='${ client.city }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">CEP:</label> <input
						type="text" class="form-control" name="cep"
						value="<c:out value='${ client.cep }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Estado:</label> <input
						type="text" class="form-control" name="state"
						value="<c:out value='${ client.state }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Logradouro:</label> <input
						type="text" class="form-control" name="publicPlace"
						value="<c:out value='${ client.publicPlace }' />" />
				</div>
				<div class="mb-3">
					<label for="country" class="form-label">Telefone:</label> <input
						type="text" class="form-control" name="cellphone"
						value="<c:out value='${ client.cellphone }' />" />
				</div>
				<button type="submit" class="btn btn-success">Salvar</button>
			</div>


		</div>
	</div>
</body>
</html>