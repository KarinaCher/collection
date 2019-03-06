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
        <jsp:include page="parts/menu.jsp" />
        
        <div class="container">
            ${count} postcards <c:if test="${!empty tag}">with tag name "${tag}"</c:if>
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
                              <a href="/tag/${postcard.country}/page/1">${postcard.country}</a>
                              <br />
                              sender <a href="/tag/${postcard.sender}/page/1">${postcard.sender} </a> 
                              <br />
                              <c:if test="${!empty postcard.tags}">
                                  <a href="/tag/${postcard.tags[0]}/page/1">${postcard.tags[0]}</a>
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