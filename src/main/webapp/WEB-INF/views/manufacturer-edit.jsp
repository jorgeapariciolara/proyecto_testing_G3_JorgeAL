<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manufacturer Edition | Aswesome App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>

	<div class="pt-5 container">
		<h2>Fabricante ${manufacturer.id}</h2>
		<hr>
		<div class=" row justify-content-center">

			<div class="col-md-8">

				<form:form action="${pageContext.request.contextPath}/manufacturers"
					method="POST" modelAttribute="manufacturer">
					<h3 class="mt-3 mb-3">Datos de fabricante</h3>
					<div class="form-group mb-3">
						<label for="name">Name</label>
						<form:input path="name" class="form-control" />
					</div>
					
					<div class="form-group mb-3">
						<label for="cif">CIF</label>
						<form:input path="cif" class="form-control" />
					</div>

					<div class="form-group mb-3">
						<label for="numEmployees">Nº empleados</label>
						<form:input path="numEmployees" class="form-control" />
					</div>
					
					<div class="form-group mb-3">
						<label for="year">Año fundación</label>
						<form:input path="year" class="form-control" />
					</div>
					
					<h3 class="mt-3 mb-3">Datos de dirección</h3>
					<div class="form-group mb-3">
						<label for=direction.street>Calle</label>
						<form:input path="direction.street" class="form-control" />
					</div>
					
					<div class="form-group mb-3">
						<label for=direction.postalCode>Código Postal</label>
						<form:input path="direction.postalCode" class="form-control" />
					</div>
					
					<div class="form-group mb-3">
						<label for=direction.city>Ciudad</label>
						<form:input path="direction.city" class="form-control" />
					</div>
					
					<div class="form-group mb-3">
						<label for=direction.country>País</label>
						<form:input path="direction.country" class="form-control" />
					</div>
					<h3 class="mt-3 mb-3">Productos disponibles</h3>
					
					<div class="form-group mb-3">
					<label for="products">Productos</label>
					<form:select class="form-select form-select-lg mb-3" path="products" items="${products}" itemLabel="name" itemValue="id"></form:select>
					</div>
					
					<form:hidden path="id" />
				
					<div class="d-grid gap-2 mt-5">
					<button class = "btn btn-success btn-lg" type = "submit" >Guardar</button>

					</div>
					
				</form:form>


			</div>
		</div>
	</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>