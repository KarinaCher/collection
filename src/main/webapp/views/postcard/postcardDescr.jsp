<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div style="text-align: left; padding: 20px">
    <p><fmt:message key="dateSent" bundle="${texts}"/>: ${postcard.dateSentString}</p>
    <p><fmt:message key="dateReceived" bundle="${texts}"/>: ${postcard.dateReceivedString}
        <c:if test="${postcard.travelDays ne -1}">
            (<fmt:message key="travel" bundle="${texts}"/>
            ${postcard.travelDays}
            <fmt:message key="days" bundle="${texts}"/>)
        </c:if>
    </p>
    <c:if test="${!empty postcard.originCountry}">
        <p>
            <fmt:message key="originCountry" bundle="${texts}"/>:
            <fmt:message key="${postcard.originCountry}" bundle="${country}"/>
        </p>
    </c:if>
    <p>
        <fmt:message key="from" bundle="${texts}"/>:
        <fmt:message key="${postcard.country}" bundle="${country}"/>,
        <a href="/city/${postcard.city}/page/1">${postcard.city}</a>
    </p>
    <p>
        <fmt:message key="sender" bundle="${texts}"/>:
        <c:forEach var="sender" items="${postcard.senders}">
            <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>,
        </c:forEach>
    </p>
    <p>
        <fmt:message key="size" bundle="${texts}"/>:
        ${postcard.height} x ${postcard.width}
    </p>
    <c:if test="${!empty postcard.description}">
        <p>
            <fmt:message key="notes" bundle="${texts}"/>:
            ${fn:replace(postcard.description, '|', '<br />')}
        </p>
    </c:if>
    <c:if test="${!empty postcard.tags}">
        <p>
            <fmt:message key="tags" bundle="${texts}"/>:
            <c:if test="${!empty postcard.tags}">
                <c:forEach var="tag" items="${postcard.tags}">
                    <a href="/tag/${tag}/page/1"><fmt:message key="${tag}" bundle="${tagMap}"/></a>
                </c:forEach>
            </c:if>
        </p>
    </c:if>

</div>