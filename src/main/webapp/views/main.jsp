<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <meta http-equiv="Content-Type"
          content="text/html; charset=utf-8">
        <title>User Registration</title>
    </head>
    <body>
        <c:forEach var="postcard" items="${list}">
            id = ${postcard.id}
        </c:forEach>
    </body>
</html>