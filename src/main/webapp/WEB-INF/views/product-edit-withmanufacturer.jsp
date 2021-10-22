<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Edition | Aswesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class = "pt-5 container">
			<h2>Product ${product.id}</h2>
			<hr>
		<div class = " row justify-content-center">

			<div class = "col-md-8">
			
			
			
			<form action = "${pageContext.request.contextPath}/products" method="POST">
				
					<div class = "form-group">
					  <label for="name">Name</label>
						<input type = "text" class = "form-control" name = "name" placeholder = "Enter Name" value = "${product.name}"/>
					</div>
					
					<div class = "form-group">
					  <label for="name">Description</label>
						<input type = "text" class = "form-control" name = "description" placeholder = "Enter Name" value = "${product.description}"/>
					</div>
				
					<div class = "form-group">
						<label for="price">Price</label>
						<input type="number" step="0.01" class = "form-control" name = "price" placeholder = "Enter Price" value = "${product.price}"/>
					</div>
				
					<div class="form-group">
						<label for="quantity">Quantity</label>
					  	<input type = "number" class = "form-control" name = "quantity" placeholder = "Enter Quantity" value = "${product.quantity}"/>
					</div>
					
					<input type = "hidden" name = "manufacturer.id" value = "${manufacturer.id}"/>
					<input type = "hidden" name = "id" value = "${product.id}"/>
				
					<a class = "btn btn-info" href = "${pageContext.request.contextPath}/products" >Back to list</a>
					<button class = "btn btn-primary" type = "submit" >Save</button>
				</form>
			
			
			
			
		
			</div>
		</div>
	</div>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>