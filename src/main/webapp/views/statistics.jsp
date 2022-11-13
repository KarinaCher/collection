<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/css/test.css'>
        <link rel='stylesheet' href='/css/main.css'>
        <script src="/js/test.js"></script>
        <title>Statistics</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        <div class="container">
            <ul class="nav nav-tabs">
                <li <c:if test="${p eq 'country'}">class="active"</c:if>>
                    <a href="/statistics/country"><fmt:message key="byCountry" bundle="${texts}"/></a>
                </li>
                <li <c:if test="${p eq 'tag'}">class="active"</c:if>>
                    <a href="/statistics/tag"><fmt:message key="byTag" bundle="${texts}"/></a>
                </li>
                <li <c:if test="${p eq 'year'}">class="active"</c:if>>
                    <a href="/statistics/year"><fmt:message key="byYear" bundle="${texts}"/></a>
                </li>
                <li <c:if test="${p eq 'sender'}">class="active"</c:if>>
                    <a href="/statistics/sender"><fmt:message key="bySender" bundle="${texts}"/></a>
                </li>
                <li <c:if test="${p eq 'most'}">class="active"</c:if>>
                    <a href="/statistics/most"><fmt:message key="most" bundle="${texts}"/></a>
                </li>
                <li <c:if test="${p eq 'size'}">class="active"</c:if>>
                    <a href="/statistics/size">by size</a>
                </li>
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
                    
                    <c:when test="${p eq 'year'}">
                        <jsp:include page="parts/statisticsByYear.jsp" />
                    </c:when>
                    
                    <c:when test="${p eq 'most'}">
                        <jsp:include page="parts/statisticsMost.jsp" />
                    </c:when>

                    <c:otherwise>
                        <jsp:include page="parts/statisticsBySender.jsp" />
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
