<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table class="table table-hover" style="width: 700px; margin: 30px auto 0px auto">
    <tr>
        <td><fmt:message key="dateSent" bundle="${texts}"/></td>
        <td>${postcard.dateSentString}</td>
    </tr>
    <tr>
        <td><fmt:message key="dateReceived" bundle="${texts}"/></td>
        <td>${postcard.dateReceivedString}
            <c:if test="${postcard.travelDays ne -1}">
                (<fmt:message key="travel" bundle="${texts}"/>
                ${postcard.travelDays}
                <fmt:message key="days" bundle="${texts}"/>)
            </c:if>
        </td>
    </tr>
    <c:if test="${!empty postcard.originCountry}">
        <tr>
            <td><fmt:message key="originCountry" bundle="${texts}"/></td>
            <td><fmt:message key="${postcard.originCountry}" bundle="${country}"/></td>
        </tr>
    </c:if>
    <tr>
        <td><fmt:message key="from" bundle="${texts}"/></td>
        <td><fmt:message key="${postcard.country}" bundle="${country}"/>, ${postcard.city}</td>
    </tr>
    <tr>
        <td><fmt:message key="sender" bundle="${texts}"/></td>
        <td>
            <c:forEach var="sender" items="${postcard.senders}">
                <a href="/tag/${sender}/page/1"><fmt:message key="${sender}"
                                                             bundle="${senderMap}"/></a>,
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td><fmt:message key="size" bundle="${texts}"/></td>
        <td>${postcard.height} x ${postcard.width}</td>
    </tr>
    <c:if test="${!empty postcard.description}">
        <tr>
            <td><fmt:message key="notes" bundle="${texts}"/></td>
            <td>${fn:replace(postcard.description, '|', '<br />')}</td>
        </tr>
    </c:if>
    <c:if test="${!empty postcard.tags}">
        <tr>
            <td><fmt:message key="tags" bundle="${texts}"/></td>
            <td>
                <c:if test="${!empty postcard.tags}">
                    <c:forEach var="tag" items="${postcard.tags}">
                        <a href="/tag/${tag}/page/1"><fmt:message key="${tag}"
                                                                  bundle="${tagMap}"/></a>
                    </c:forEach>
                </c:if>
            </td>
        </tr>
    </c:if>

</table>