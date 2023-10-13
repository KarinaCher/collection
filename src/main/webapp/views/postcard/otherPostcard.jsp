<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table class="table table-hover" style="width: 700px; margin: 30px auto 0px auto">
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