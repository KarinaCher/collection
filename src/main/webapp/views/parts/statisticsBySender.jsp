<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="max" value="${tagsBySender[0].count}" />
<c:forEach var="tag" items="${tagsBySender}">
    <fmt:formatNumber var="width" type="percent" 
                      maxFractionDigits="0" value="${tag.count/max}"/>
    <a href="/tag/${tag.name}/page/1"><fmt:message key="${tag.name}" bundle="${senderMap}"/></a> (${tag.count})
    <div class="bg-info" style="width: ${width}; border-left: #d9edf7 1px solid;">&nbsp;</div>
</c:forEach>