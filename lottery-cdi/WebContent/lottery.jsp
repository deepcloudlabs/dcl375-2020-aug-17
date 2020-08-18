<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
tbody>tr:nth-child(2n) {
	background-color: #EFEFEF;
}

tbody>tr:nth-child(2n+1) {
	background-color: #DFDFDF;
}

tbody>tr:hover {
	background-color: lightyellow;
}
</style>
<meta charset="ISO-8859-1">
<title>Lottery Page v3</title>
</head>
<body>
	<form action="draw" method="post">
		<label for="column">Column:</label> <input id="column" type="number"
			value="${param.column}" min="1" name="column">
		<button name="action" value="draw">Draw</button>
		<button name="action" value="reset">Reset</button>
	</form>
	<c:if test="${not empty lottery.numbers}">
		<table>
			<thead>
				<tr>
					<th>No</th>
					<c:forEach begin="1" end="6" var="col">
						<th>Number #${col}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lottery.numbers}" var="numbers" varStatus="loop">
					<tr>
						<td>${loop.index + 1}</td>
						<c:forEach items="${numbers}" var="num">
							<td>${num}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>