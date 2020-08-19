<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>World Countries</title>
</head>
<body>
	<p />
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h4 class="card-title">Search Panel</h4>
			</div>
			<div class="card-body">
				<form method="post">
					<div class="form-group">
						<label for="continent">Continent:</label> 
						<select class="form-control"
							id="continent" name="continent">
							<c:forEach items="${continents}" var="cont">
								<c:choose>
									<c:when test="${param.continent eq cont}">
										<option selected label="${cont}" value="${cont}">${cont}</option>
									</c:when>
									<c:otherwise>
										<option label="${cont}" value="${cont}">${cont}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<button class="btn btn-success" formaction="list" value="list">List</button>
					</div>
				</form>

			</div>
		</div>
		<c:if test="${not empty countries}">
			<p />
			<div class="card">
				<div class="card-header">
					<h4 class="card-title">Search Result Panel</h4>
				</div>
				<div class="card-body">

					<table
						class="table table-responsive table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								<th>Surface Area</th>
								<th>Population</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${countries}" var="country" varStatus="loop">
								<tr>
									<td>${loop.index + 1}</td>
									<td>${country.name}</td>
									<td>${country.surfaceArea}</td>
									<td>${country.population}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>