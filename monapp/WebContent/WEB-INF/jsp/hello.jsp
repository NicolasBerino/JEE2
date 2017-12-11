<%@ include file="include.jsp" %>
<html>
<head><title>Hello :: Spring Application</title></head>
<body>
<h1>Hello - Spring Application</h1>
<p>Greetings, it is now <c:out value="${now}" default="None" /></p>
<p>Ceci est un test de string :<c:out value="${chaineTest}" default="None" /></p>

<a href="productsList.jsp">link text</a>

</body>
</html>