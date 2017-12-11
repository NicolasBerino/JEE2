<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:url var="edit" value="/actions/product/edit" />
<html>
<head>
<title>Hello :: Spring Application</title>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
<div class="container">
<h1>Products (bootstrap)</h1>
<table class="table table-hover">
<c:forEach items="${products}" var="prod">
<tr>
<td><a href="${edit}?id=${prod.number}">
<c:out value="${prod.name}" />
</a></td>
<td><i>$<c:out value="${prod.price}" /></i></td>
</tr>
</c:forEach>
</table>
<p>
<a class="btn btn-info" href="${edit}">Create new product</a>
</p>
</div>
</body>
</html>