<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body>
<div class="form-group">
<label for="type">Type:</label>
<form:select path="type" multiple="false" class="form-control">
<form:option value="" label="--- Select ---" />
<form:options items="${productTypes}" />
</form:select>
<form:errors path="type" cssClass="alert alert-warning"
element="div" />
</div>
</body>
</html>