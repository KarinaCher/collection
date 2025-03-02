<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>

<c:forEach var="countryItem" items="${tagsByCountry}">
    <div style="margin-bottom: 20px;">
        <b><a href="/country/${countryItem.name}/page/1"><fmt:message key="${countryItem.name}" bundle="${country}"/>&nbsp;&mdash;&nbsp;${countryItem.count}</a></b> 
        <br />
        <c:forEach var="city" items="${countryItem.list}">
            <c:if test="${!empty city.name}">
                <span style="padding-left: 10px; white-space: nowrap;"><a href="/city/${city.name}/page/1">${city.name}</a>&nbsp;-&nbsp;${city.count}</span>,
            </c:if>
        </c:forEach>
    </div>
</c:forEach>