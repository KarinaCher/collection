<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<canvas id="myCanvas" width="700" height="700" style="border:1px solid #c3c3c3;">
Your browser does not support the canvas element.
</canvas>

<script>
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");
    <c:forEach var="row" items="${heights}" varStatus="loop">
        <c:forEach var="cell" items="${widths}" varStatus="innerLoop">
            <c:set var="count" value="${sizeMap[loop.index][innerLoop.index]}" />

            <c:if test="${count ne 0}">
                ctx.beginPath();
                ctx.arc(${row} * 3, ${cell} * 3, ${count}, 0, 2 * Math.PI);
                ctx.stroke();
            </c:if>
        </c:forEach>
    </c:forEach>
</script>

<table id="postcardsBySize">
    <c:forEach var="row" items="${heights}" varStatus="loop">
        <tr>
        <c:forEach var="cell" items="${widths}" varStatus="innerLoop">
            <c:set var="count" value="${sizeMap[loop.index][innerLoop.index]}" />

            <c:if test="${count eq 0}">
                <td>&nbsp;</td>
            </c:if>
            <c:if test="${count ne 0}">
                <td style="background-color: #e3f2fd">
                    <a href="" title="${row} x ${cell} count is ${count}">&nbsp;</a>
                </td>
            </c:if>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
