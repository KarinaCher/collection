<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/css/test.css'>
        <link rel='stylesheet' href='/css/main.css'>
        <script src="/js/test.js"></script>

        <title>Collection overview</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        <div class="container">
            ${count} <fmt:message key="postcards" bundle="${texts}"/>

            <c:set var="descrMessage"><fmt:message key="${itemId}" bundle="${tagDescrMap}" /></c:set>
            <c:if test="${navPath eq 'tag' and !fn:startsWith(descrMessage,'???')}">
                <br />${descrMessage}
            </c:if>

            <jsp:include page="parts/navigation.jsp" />
            <c:set var="collCount" value="4" />
            <c:forEach var="postcard" items="${list}" varStatus="count">
                <c:if test="${(count.count mod collCount) eq 1}" >
                    <div class="row">
                    </c:if>
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <div class="caption"">
                                <a href="/postcard/${postcard.id}/${itemId}" style="display: inline;">
                                    <img src="/image/200/${postcard.images[0]}" id="${postcard.id}"/>
                                </a>

                                <!--fmt:message key="overview.from" bundle="${texts}"/-->
                                <c:forEach var="sender" items="${postcard.senders}">
                                    &#8226; <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>
                                </c:forEach>

                                <c:if test="${!empty postcard.originCountry}">
                                    &#8226; <a href="/country/${postcard.originCountry}/page/1"><fmt:message key="${postcard.originCountry}" bundle="${country}"/></a>, <fmt:message key="from" bundle="${texts}" />
                                    <fmt:message key="${postcard.country}" bundle="${country}"/>
                                </c:if>
                                <c:if test="${empty postcard.originCountry}">
                                    &#8226; <a href="/country/${postcard.country}/page/1"><fmt:message key="${postcard.country}" bundle="${country}"/></a>
                                </c:if>
                                <div>
                                    <c:if test="${!empty postcard.tags}">
                                        <c:forEach var="tag" items="${postcard.tags}">
                                            &#8226; <a href="/tag/${tag}/page/1"><fmt:message key="${tag}" bundle="${tagMap}"/></a>
                                        </c:forEach>
                                    </c:if>
                                </div>

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