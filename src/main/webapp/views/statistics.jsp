<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        <title>Statistics</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        <div class="container">
            <h1>Statistics</h1>
        </div>
        <div class="container">
            <h2>by tag</h2>
            <c:forEach var="tag" items="${tags}">
                <a href="/tag/${tag.name}/page/1"><span style="white-space: nowrap; padding-left: 10px">${tag.name}&nbsp;&mdash;&nbsp;${tag.count},</span></a> 
            </c:forEach>
            <h2>by country</h2>
            <c:forEach var="tag" items="${tagsByCountry}">
                <a href="/tag/${tag.name}/page/1"><span style="white-space: nowrap; padding-left: 10px"><fmt:message key="${tag.name}" bundle="${country}"/>&nbsp;&mdash;&nbsp;${tag.count},</span></a> 
            </c:forEach>
            <h2>by sender</h2>
            <table class="table table-hover">
                <c:forEach var="tag" items="${tagsBySender}">
                <tr>
                    <td><a href="/tag/${tag.name}/page/1">${tag.name}</a></td><td>${tag.count}</td>
                </tr> 
            </c:forEach>
            </table>
            
        </div>
        
    </body>
</html>
