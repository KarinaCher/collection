<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='/css/main.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <title>Postcard</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />

        <c:if test="${!empty postcard}">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <c:forEach var="image" items="${postcard.images}" varStatus="loop">
                            <c:if test="${loop.index == 0}">
                                <img src="../image/lg/${image}"/>
                            </c:if>
                            <c:if test="${loop.index > 0}">
                                <a href="../image/lg/${image}" title="">
                                    <img src="../image/sm/${image}">
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>

                    <c:if test="${postcard.mine}">
                        <div class="col-sm-6">
                            <table class="table table-hover">
                                <tr>
                                    <td><fmt:message key="dateSent" bundle="${texts}"/></td>
                                    <td><fmt:formatDate value="${postcard.dateSent}" pattern="dd MMM yyyy"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="dateReceived" bundle="${texts}"/></td>
                                    <td><fmt:formatDate value="${postcard.dateReceived}" pattern="dd MMM yyyy"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="from" bundle="${texts}"/></td>
                                    <td><fmt:message key="${postcard.country}" bundle="${country}"/>, ${postcard.city}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="sender" bundle="${texts}"/></td>
                                    <td>
                                        <c:forEach var="sender" items="${postcard.senders}">
                                            <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>, 
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="size" bundle="${texts}"/></td>
                                    <td>${postcard.height} x ${postcard.width}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="notes" bundle="${texts}"/></td>
                                    <td>${fn:replace(postcard.description, '|', '<br />')}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="tags" bundle="${texts}"/></td>
                                    <td>
                                        <c:if test="${!empty postcard.tags}">
                                            <c:forEach var="tag" items="${postcard.tags}">
                                                <a href="/tag/${tag}/page/1"><fmt:message key="${tag}" bundle="${tagMap}"/></a> 
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                </tr>

                            </table>
                        </div>
                    </c:if>
                    <c:if test="${!postcard.mine}">
                        <div class="col-sm-6">
                            <table class="table table-hover">
                                <tr>
                                    <td><fmt:message key="sender" bundle="${texts}"/></td>
                                    <td>
                                        <c:forEach var="sender" items="${postcard.senders}">
                                            <fmt:message key="${sender}" bundle="${senderMap}"/>, 
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="size" bundle="${texts}"/></td>
                                    <td>${postcard.height} x ${postcard.width}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="notes" bundle="${texts}"/></td>
                                    <td>${fn:replace(postcard.description, '|', '<br />')}</td>
                                </tr>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </body>
</html>
