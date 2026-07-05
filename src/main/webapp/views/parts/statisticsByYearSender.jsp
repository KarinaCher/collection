<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="max" value="${maxCount}" />
<c:forEach var="year" items="${tagsBySender}">

    <div style="width: 80px; display: inline-block"><a href="/tag/${year.name}/page/1">${year.name}</a> (${year.count})</div>

    <c:forEach var="sender" items="${year.list}" varStatus="loop">
        <fmt:formatNumber var="width" type="percent" maxFractionDigits="0" value="${sender.count/max}"/>
        <div class="bg-info sender-${sender.name}" style="width: ${width}; display: inline-block; margin-left: -4px; padding-left: 3px;">${sender.count}</div>
    </c:forEach>

    <br />
</c:forEach>