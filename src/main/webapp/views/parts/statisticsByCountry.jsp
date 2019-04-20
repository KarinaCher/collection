<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="countryItem" items="${tagsByCountry}">
    <a href="/tag/${countryItem.name}/page/1"><fmt:message key="${countryItem.name}" bundle="${country}"/>&nbsp;&mdash;&nbsp;${countryItem.count}</a> 
    <br />
    <c:forEach var="city" items="${countryItem.list}">
        <span style="padding-left: 10px">${city.name} - ${city.count}</span>,
        <br />
    </c:forEach>
</c:forEach>