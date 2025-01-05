<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel='stylesheet' href='/css/test.css'>
    <link rel='stylesheet' href='/css/main.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="/js/test.js"></script>
    <script src="/js/js.js"></script>

    <title>Postcard</title>
</head>
<body>
<jsp:include page="parts/bundles.jsp"/>
<jsp:include page="parts/menu.jsp"/>
<script>
    var imagesList = [
        <c:forEach var="image" items="${postcard.images}" varStatus="loop">
        "${image}",
        </c:forEach>
        ""];
</script>
<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal"><span id="span-close" aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <div id="modal-body-img" class="imagepreview">
                    <div class="pop" id="modal-body-prevImg" current-index=""></div>
                    <div class="pop" id="modal-body-nextImg" current-index=""></div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:set var="placeholder" value='<div style="width: 100px;">&nbsp;</div>' />

<%--<div style="width: 100%; text-align: center;"><a href="TODO">TODO Back to ${itemId}</a></div>--%>

<table style="width: 100%;">
    <tr>
        <td style="vertical-align: text-top; padding: 150px 0 0 30px;">
            <c:if test="${!empty prevPostcard}">
                <a href="/postcard/${prevPostcard.id}/${itemId}">
                    <img src="/image/100/${prevPostcard.images[0]}" id="${prevPostcard.id}"/>
                </a>
            </c:if>
            <c:if test="${empty prevPostcard}">${placeholder}</c:if>
        </td>
        <td>

            <c:if test="${!empty postcard}">
                <table style="width: 80%">
                    <tr>
                        <td>
                            <img src="../../image/lg/${postcard.images[0]}" class="pop" current-index="0"/>
                            <br/>
                            <c:forEach var="image" items="${postcard.images}" varStatus="loop">
                                <c:if test="${loop.index > 0}">
                                    <img src="../../image/100/${image}" class="pop img_thumb" current-index="${loop.index}"/>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:if test="${postcard.mine}">
                                <jsp:include page="postcard/postcardDescr.jsp" />
                            </c:if>
                            <c:if test="${!postcard.mine}">
                                <jsp:include page="postcard/otherPostcard.jsp" />
                            </c:if>
                        </td>
                    </tr>
                </table>
            </c:if>

        </td>
        <td style="vertical-align: text-top; padding: 150px 30px 0 0;">
            <c:if test="${!empty nextPostcard}">
                <a href="/postcard/${nextPostcard.id}/${itemId}">
                    <img src="/image/100/${nextPostcard.images[0]}" id="${nextPostcard.id}"/>
                </a>
            </c:if>
            <c:if test="${empty nextPostcard}">${placeholder}</c:if>
        </td>
    </tr>
</table>

</body>
</html>