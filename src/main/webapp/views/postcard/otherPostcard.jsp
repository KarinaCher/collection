<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div style="text-align: left; padding: 20px">
    <p>
        <fmt:message key="sender" bundle="${texts}"/>:
        <c:forEach var="sender" items="${postcard.senders}">
            <fmt:message key="${sender}" bundle="${senderMap}"/>,
        </c:forEach>
    </p>
    <p>
        <fmt:message key="size" bundle="${texts}"/>:
        ${postcard.height} x ${postcard.width}
    </p>
    <p>
        <fmt:message key="notes" bundle="${texts}"/>:
        ${fn:replace(postcard.description, '|', '<br />')}
    </p>
</div>