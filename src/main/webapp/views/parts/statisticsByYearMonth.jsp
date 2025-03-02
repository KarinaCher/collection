<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="max" value="110" />

<c:forEach var="yearItem" items="${tagsByYear}">
    <div style="margin-bottom: 20px;">
        <h3>${yearItem.name} - <a href="/tag/${yearItem.name}/page/1">${yearItem.count}</a></h3>
        <c:forEach var="month" items="${yearItem.list}" varStatus="loop">
            <fmt:formatNumber var="width" type="percent"
                              maxFractionDigits="0" value="${month.count/max}"/>
<div class="bg-info${loop.index + 1}" style="width: ${width}; display: inline-block; margin-left: -4px; padding-left: 3px;"> <a href="/tag/${month.name}/page/1">${month.count}</a></div>
        </c:forEach>
    </div>
</c:forEach>