<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        <title>Statistics</title>
    </head>
    <body>
        <jsp:include page="parts/menu.jsp" />
        <div class="container">
            <h1>Statistics</h1>
        </div>
        <div class="container">
            <h2>by sender:</h2>
            <table class="table table-hover">
                <c:forEach var="tag" items="${tagsBySender}">
                <tr>
                    <td>${tag.key}</td><td>${tag.value}</td>
                </tr> 
            </c:forEach>
            </table>
            <h2>by Country</h2>
            <table class="table table-hover">
                <c:forEach var="tag" items="${tagsByCountry}">
                <tr>
                    <td>${tag.key}</td><td>${tag.value}</td>
                </tr> 
            </c:forEach>
            </table>
        </div>
        
    </body>
</html>
