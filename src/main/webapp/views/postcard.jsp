<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/css/test.css'>
        <link rel='stylesheet' href='/css/main.css'>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="/js/test.js"></script>
        <script src="/js/js.js"></script>

        <title>Postcard</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
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
                        <button type="button" class="close" data-dismiss="modal"><span id="span-close" aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <div id="modal-body-img" class="imagepreview">
                            <div class="pop" id="modal-body-prevImg" current-index=""></div>
                            <div class="pop" id="modal-body-nextImg" current-index=""></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${!empty postcard}">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <img src="../image/lg/${postcard.images[0]}" class="pop" current-index="0" />
                    </div>

                    <c:if test="${postcard.mine}">
                        <div class="col-sm-6">
                            <table class="table table-hover">
                                <tr>
                                    <td><fmt:message key="dateSent" bundle="${texts}"/></td>
                                    <td>${postcard.dateSentString}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="dateReceived" bundle="${texts}"/></td>
                                    <td>${postcard.dateReceivedString}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="from" bundle="${texts}"/></td>
                                    <td><fmt:message key="${postcard.country}" bundle="${country}"/>, ${postcard.city}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="sender" bundle="${texts}"/></td>
                                    <td>
                                        <c:forEach var="sender" items="${postcard.senders}">
                                            <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>, 
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="size" bundle="${texts}"/></td>
                                    <td>${postcard.height} x ${postcard.width}</td>
                                </tr>
                                <c:if test="${!empty postcard.description}">
                                    <tr>
                                        <td><fmt:message key="notes" bundle="${texts}"/></td>
                                        <td>${fn:replace(postcard.description, '|', '<br />')}</td>
                                    </tr>
                                </c:if>
                                <c:if test="${!empty postcard.tags}">
                                    <tr>
                                        <td><fmt:message key="tags" bundle="${texts}"/></td>
                                        <td>
                                            <c:if test="${!empty postcard.tags}">
                                                <c:forEach var="tag" items="${postcard.tags}">
                                                    <a href="/tag/${tag}/page/1"><fmt:message key="${tag}" bundle="${tagMap}"/></a> 
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>

                            </table>
                        </div>
                    </c:if>
                    <c:if test="${!postcard.mine}">
                        <div class="col-sm-6">
                            <table class="table table-hover">
                                <tr>
                                    <td><fmt:message key="sender" bundle="${texts}"/></td>
                                    <td>
                                        <c:forEach var="sender" items="${postcard.senders}">
                                            <fmt:message key="${sender}" bundle="${senderMap}"/>, 
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="size" bundle="${texts}"/></td>
                                    <td>${postcard.height} x ${postcard.width}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="notes" bundle="${texts}"/></td>
                                    <td>${fn:replace(postcard.description, '|', '<br />')}</td>
                                </tr>
                            </table>
                        </div>
                    </c:if>
                </div>
                <div>
                    <c:forEach var="image" items="${postcard.images}" varStatus="loop">
                        <c:if test="${loop.index > 0}">
                            <img src="../image/100/${image}" class="pop img_thumb" current-index="${loop.index}" />
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </body>
</html>
