<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="max" value="110" />

<div class="bg-info1" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Jan</div>
<div class="bg-info2" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Feb</div>
<div class="bg-info3" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Mar</div>
<div class="bg-info4" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Apr</div>
<div class="bg-info5" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">May</div>
<div class="bg-info6" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Jun</div>
<div class="bg-info7" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Jul</div>
<div class="bg-info8" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Aug</div>
<div class="bg-info9" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Sep</div>
<div class="bg-info10" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Oct</div>
<div class="bg-info11" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Nov</div>
<div class="bg-info12" style="width: 70px; display: inline-block; margin-left: -4px; padding-left: 3px">Dec</div>
<br />
<br />

<c:forEach var="yearItem" items="${tagsByYear}">
    <div style="margin-bottom: 20px;">
        <h3>${yearItem.name} - <a href="/tag/${yearItem.name}/page/1">${yearItem.count}</a></h3>
        <c:forEach var="month" items="${yearItem.list}" varStatus="loop">
            <fmt:formatNumber var="width" type="percent"
                              maxFractionDigits="0" value="${month.count/max}"/>
<div class="bg-info${loop.index + 1}" style="width: ${width}; display: inline-block; margin-left: -4px">&nbsp;${month.count}</div>
        </c:forEach>
    </div>
</c:forEach>