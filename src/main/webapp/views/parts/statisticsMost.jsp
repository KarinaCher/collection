<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <c:forEach var="postcard" items="${most}">
        <div class="row">
            <div class="col-sm-6">
                <fmt:message key="${postcard.key}" bundle="${texts}"/>
            </div>
            <div class="col-sm-6">
                <a href="/postcard/${postcard.value.id}"><img src="../image/400/${postcard.value.images[0]}" /></a>
            </div>
        </div>
    </c:forEach>
</div>