<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="max" value="${maxCount}"/>
<c:forEach var="year" items="${tagsBySender}">

    <div style="width: 80px; display: inline-block"><a href="/tag/${year.name}/page/1">${year.name}</a> (${year.count})
    </div>
    <c:forEach var="sender" items="${year.list}" varStatus="loop">
        <%-- define the percentage for each sender --%>
        <fmt:formatNumber var="width" type="percent" maxFractionDigits="3" value="${sender.count/max}"/>
        <%-- set name only for those who have enough space for it --%>
        <c:choose>
            <c:when test="${0.1 < (sender.count/max)}">
                <c:set var="info" value="${sender.name} (${sender.count})"/>
            </c:when>
            <c:otherwise>
                <c:set var="info" value="${sender.count}"/>
            </c:otherwise>
        </c:choose>

        <div class="bg-info sender-${sender.name} sender"
             style="width: ${width}; display: inline-block; margin-left: -4px; padding-left: 3px;">${info}
        </div>
    </c:forEach>
    <br/>
</c:forEach>