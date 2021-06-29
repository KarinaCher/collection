<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <c:forEach var="postcard" items="${most}" varStatus="loop">
        <c:set var="isStartRow" value="${(loop.index % 2) eq 0}" />
        <c:if test="${isStartRow}">
            <div class="row">
        </c:if>
                <div class="col-sm-6">
                    <h4>
                        <fmt:message key="${postcard.nomination}" bundle="${texts}">
                            <fmt:param value="${postcard.value}"/>
                        </fmt:message>
                    </h4>
                    <a href="/postcard/${postcard.itemId}"><img src="../image/300/${postcard.image}" /></a>
                </div>
        <c:if test="${!isStartRow}">
            </div>
        </c:if>
    </c:forEach>
</div>