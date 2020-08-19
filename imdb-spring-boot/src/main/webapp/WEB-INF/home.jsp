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
<title>Imdb Movie Search Page</title>
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
						<label for="fromYear">From year:</label> <input
							class="form-control" id="fromYear" type="text" name="fromYear"
							value="${param.fromYear}">
					</div>
					<div class="form-group">
						<label for="toYear">To year:</label> <input class="form-control"
							id="toYear" type="text" name="toYear" value="${param.toYear}">
					</div>
					<div class="form-group">
						<label for="genre">Genre:</label> <select class="form-control"
							id="genre" name="genre">
							<c:forEach items="${genres}" var="genre">
								<c:choose>
									<c:when test="${param.genre eq genre.id}">
										<option selected label="${genre.name}" value="${genre.id}">${genre.name}</option>
									</c:when>
									<c:otherwise>
										<option label="${genre.name}" value="${genre.id}">${genre.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<button class="btn btn-success" formaction="search" value="search">Search</button>
					</div>
				</form>

			</div>
		</div>
		<c:if test="${not empty movies}">
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
								<th>Title</th>
								<th>Year</th>
								<th>Imdb</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${movies}" var="mov" varStatus="loop">
								<tr>
									<td>${loop.index + 1}</td>
									<td>${mov.title}</td>
									<td>${mov.year}</td>
									<td><a href="http://www.imdb.com/title/${mov.imdb}"
										target="_blank">Visit me!</a></td>
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