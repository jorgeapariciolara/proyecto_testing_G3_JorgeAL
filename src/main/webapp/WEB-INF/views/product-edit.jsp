<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Edition | Aswesome App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
	
	<div class = "pt-5 container">
			<h2>Product ${product.id}</h2>
			<hr>
		<div class = " row justify-content-center">

			<div class = "col-md-8">
			
			
			
				<form:form action = "${pageContext.request.contextPath}/products" method="POST" modelAttribute="product">
				
					<div class="form-group">
						<label for="name">Name</label>
						<form:input path="name" class="form-control" />
					</div>
					
					<div class="form-group">
						<label for="description">Descripción</label>
						<form:textarea path="description" class="form-control"/>
					</div>
					
					<div class="form-group">
						<label for="price">Precio</label>
						<form:input path="price" class="form-control" />
					</div>
					
					<div class="form-group">
						<label for="quantity">Cantidad</label>
						<form:input path="quantity" class="form-control" />
					</div>
					
					<h3 class="mt-3 mb-3">Fabricantes disponibles</h3>
					<div class="form-group">
						<label for="manufacturer">Fabricante</label>
						<form:select class="form-select form-select-lg mb-3" path="manufacturer" items="${manufacturers}" itemLabel="name" itemValue="id"></form:select>
					</div>
					
					<h3 class="mt-3 mb-3">Categorías disponibles</h3>
					<div class="form-group">
						<label for="categories">Categorías</label>
						<form:select class="form-select form-select-lg mb-3" path="categories" items="${categories}" itemLabel="name" itemValue="id"></form:select>
					</div>
					
					<form:hidden path="id"/>
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