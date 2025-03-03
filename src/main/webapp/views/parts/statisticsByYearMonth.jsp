<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="max" value="110" />

<c:forEach var="yearItem" items="${tagsByYear}">
    <div style="margin-bottom: 20px;">
        <h3>${yearItem.name} - <a href="/tag/${yearItem.name}/page/1">${yearItem.count}</a></h3>
        <c:forEach var="month" items="${yearItem.list}" varStatus="loop">
            <c:set var="monthName" value="Jan" />
            <c:choose>
                <c:when test="${month.name.endsWith('01')}"><c:set var="monthName" value="Jan" /></c:when>
                <c:when test="${month.name.endsWith('02')}"><c:set var="monthName" value="Feb" /></c:when>
                <c:when test="${month.name.endsWith('03')}"><c:set var="monthName" value="Mar" /></c:when>
                <c:when test="${month.name.endsWith('04')}"><c:set var="monthName" value="Apr" /></c:when>
                <c:when test="${month.name.endsWith('05')}"><c:set var="monthName" value="May" /></c:when>
                <c:when test="${month.name.endsWith('06')}"><c:set var="monthName" value="Jun" /></c:when>
                <c:when test="${month.name.endsWith('07')}"><c:set var="monthName" value="Jul" /></c:when>
                <c:when test="${month.name.endsWith('08')}"><c:set var="monthName" value="Aug" /></c:when>
                <c:when test="${month.name.endsWith('09')}"><c:set var="monthName" value="Sep" /></c:when>
                <c:when test="${month.name.endsWith('10')}"><c:set var="monthName" value="Oct" /></c:when>
                <c:when test="${month.name.endsWith('11')}"><c:set var="monthName" value="Nov" /></c:when>
                <c:when test="${month.name.endsWith('12')}"><c:set var="monthName" value="Dec" /></c:when>
            </c:choose>
            <fmt:formatNumber var="width" type="percent"
                              maxFractionDigits="0" value="${month.count/max}"/>
<div class="bg-info${loop.index + 1}" style="width: ${width}; display: inline-block; margin-left: -4px; padding-left: 3px;"> <a href="/tag/${month.name}/page/1" title="${monthName}">${month.count}</a></div>
        </c:forEach>
    </div>
</c:forEach>