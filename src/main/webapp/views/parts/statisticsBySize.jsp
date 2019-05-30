<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<canvas id="myCanvas" width="700" height="500" style="border:1px solid #c3c3c3;">
Your browser does not support the canvas element.
</canvas>

<script>
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");
<c:forEach var="height" items="${sizeMap}">
    <c:forEach var="width" items="${height.value}">
        <c:if test="${width.value ne 0}">
            ctx.beginPath();
            ctx.arc(${width.key} * 3, ${height.key} * 3, ${width.value}, 0, 2 * Math.PI);
            ctx.stroke();
        </c:if>
    </c:forEach>
</c:forEach>
</script>

<!--<table>
    <c:forEach var="height" items="${sizeMap}">
        <tr>
            <c:forEach var="width" items="${height.value}">
                <td>
                    <c:if test="${width.value ne 0}">
                        ${width.value} (${height.key} X ${width.key})
                    </c:if>
                <td>
            </c:forEach>
        <tr>
    </c:forEach>
</table>-->
