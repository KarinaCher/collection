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
                <a href="/tag/${tag.name}/page/1"><span style="white-space: nowrap; padding-left: 10px"><fmt:message key="${tag.name}" bundle="${tagMap}"/>&nbsp;&mdash;&nbsp;${tag.count},</span></a> 
            </c:forEach>
            <h2>by country</h2>
            <c:forEach var="tag" items="${tagsByCountry}">
                <a href="/tag/${tag.name}/page/1"><span style="white-space: nowrap; padding-left: 10px"><fmt:message key="${tag.name}" bundle="${country}"/>&nbsp;&mdash;&nbsp;${tag.count},</span></a> 
            </c:forEach>
            <h2>by sender</h2>
            <table class="table table-hover">
                <c:set var="max" value="${tagsBySender[0].count}" />
                <c:forEach var="tag" items="${tagsBySender}">
                <tr>
                    <td>
                        <fmt:formatNumber var="width" type="percent" 
                                          maxFractionDigits="0" value="${tag.count/max}"/>
                        <a href="/tag/${tag.name}/page/1"><fmt:message key="${tag.name}" bundle="${senderMap}"/></a> (${tag.count})
                        <div class="bg-info" style="width: ${width}; border-left: #d9edf7 1px solid;">&nbsp;</div>
                    </td>
                </tr> 
                </c:forEach>
            </table>
            
        </div>
        
    </body>
</html>
