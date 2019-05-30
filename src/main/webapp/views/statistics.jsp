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
            <ul class="nav nav-tabs">
                <li <c:if test="${p eq 'sender'}">class="active"</c:if>>
                    <a href="/statistics/sender">by sender</a>
                </li>
                <li <c:if test="${p eq 'tag'}">class="active"</c:if>>
                    <a href="/statistics/tag">by tag</a>
                </li>
                <li <c:if test="${p eq 'country'}">class="active"</c:if>>
                    <a href="/statistics/country">by country</a>
                </li>
<!--                <li <c:if test="${p eq 'size'}">class="active"</c:if>>
                    <a href="/statistics/size">by size</a>
                </li>-->
            </ul>
            <div style="padding-top: 30px;">
                <c:choose>
                    <c:when test="${p eq 'tag'}">
                        <jsp:include page="parts/statisticsByTag.jsp" />
                    </c:when>

                    <c:when test="${p eq 'country'}">
                        <jsp:include page="parts/statisticsByCountry.jsp" />
                    </c:when>
                    
                    <c:when test="${p eq 'size'}">
                        <jsp:include page="parts/statisticsBySize.jsp" />
                    </c:when>

                    <c:otherwise>
                        <jsp:include page="parts/statisticsBySender.jsp" />
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
