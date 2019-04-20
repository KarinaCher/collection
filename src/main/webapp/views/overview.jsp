<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <title>Collection overview</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        <div class="container">
            ${count} postcards
            <jsp:include page="parts/navigation.jsp" />
            <c:set var="collCount" value="4" />
            <c:forEach var="postcard" items="${list}" varStatus="count">
                <c:if test="${(count.count mod collCount) eq 1}" >
                    <div class="row">
                </c:if>
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <a href="/postcard/${postcard.id}">
                              <img src="/image/sm/${postcard.images[0]}" class="img-fluid" />
                            </a>
                            <div class="caption">
                              <a href="/tag/${postcard.country}/page/1"><fmt:message key="${postcard.country}" bundle="${country}"/></a>,
                              <c:if test="${!empty postcard.city}">
                                  ${postcard.city}
                              </c:if>
                              <br />
                              from  
                              <c:forEach var="sender" items="${postcard.senders}">
                                <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>, 
                              </c:forEach>
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