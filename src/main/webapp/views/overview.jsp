<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <link rel='stylesheet' href='/css/main.css'>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        <script src="/js/js.js"></script>

        <title>Collection overview</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        <div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">              
                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal"><span id="span-close" aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <div style="text-align: center;"><img src="" class="imagepreview" /></div>
                        <div class="captionModal">
                            <table class="modalCaption">
                                <tr>
                                    <th><fmt:message key="dateSent" bundle="${texts}"/></th>
                                    <td id="modalDateSent"></td>
                                </tr>
                                <tr>
                                    <th><fmt:message key="dateReceived" bundle="${texts}"/></th>
                                    <td id="modalDateReceived"></td>
                                </tr>
                                <tr>
                                    <th><fmt:message key="from" bundle="${texts}"/></th>
                                    <td id="modalCountryCity"></td>
                                </tr>
                                <tr>
                                    <th><fmt:message key="size" bundle="${texts}"/></th>
                                    <td id="modalSize"></td>
                                </tr>
                                <tr>
                                    <th><fmt:message key="notes" bundle="${texts}"/></th>
                                    <td id="modalDescription"></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <a id="modalDirectLink" href="#"><fmt:message key="directLink" bundle="${texts}"/></a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            ${count} <fmt:message key="postcards" bundle="${texts}"/>
            <jsp:include page="parts/navigation.jsp" />
            <c:set var="collCount" value="4" />
            <c:forEach var="postcard" items="${list}" varStatus="count">
                <c:if test="${(count.count mod collCount) eq 1}" >
                    <div class="row">
                    </c:if>
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <a href="#" class="pop">
                                <img src="/image/sm/${postcard.images[0]}" class="img-fluid" id="${postcard.id}"/>
                            </a>
                            <div id="postcard${postcard.id}" class="hidden"
                                 postcardDateSend="<fmt:formatDate value="${postcard.dateSent}" pattern="dd MMM yyyy"/>"
                                 postcardDateReceived="<fmt:formatDate value="${postcard.dateReceived}" pattern="dd MMM yyyy"/>"
                                 postcardCountryCity="<fmt:message key="${postcard.country}" bundle="${country}"/>, ${postcard.city}"
                                 postcardSize="${postcard.height} x ${postcard.width}"
                                 postcardOwner="${postcard.mine}">
                            </div>
                            <div id="postcardDescription${postcard.id}" class="hidden">
                                ${fn:replace(postcard.description, '|', '<br />')}
                            </div>
                            <div class="caption">
                                <fmt:message key="overview.from" bundle="${texts}"/>  
                                <c:forEach var="sender" items="${postcard.senders}">
                                    <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>, 
                                </c:forEach>
                                (<a href="/country/${postcard.country}/page/1"><fmt:message key="${postcard.country}" bundle="${country}"/></a>)
                                <br />
                                <c:if test="${!empty postcard.tags}">
                                    <c:forEach var="tag" items="${postcard.tags}">
                                        <a href="/tag/${tag}/page/1"><fmt:message key="${tag}" bundle="${tagMap}"/></a> 
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <c:if test="${(count.count mod collCount) eq 0}" >
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="container">
            <jsp:include page="parts/navigation.jsp" />
        </div>
    </body>
</html>