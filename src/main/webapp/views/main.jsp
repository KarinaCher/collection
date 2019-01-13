<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type"
          content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <title>Collection overview</title>
    </head>
    <body>
        <div class="container">
            <h1>Collection overview</h1>

            <c:forEach var="tag" items="${tags}">
                <a href="/tag/${tag.key}" class="btn btn-outline-info" role="button">
                    ${tag.key} <span class="badge badge-light">${tag.value}</span>
                </a> 
            </c:forEach>
        </div>
        
        <div class="container">
            <c:set var="collCount" value="4" />
            <c:forEach var="postcard" items="${list}" varStatus="count">
                <c:if test="${(count.count mod collCount) eq 1}" >
                    <div class="row">
                </c:if>
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <a href="../image/${postcard.images[0]}">
                              <img src="../image/${postcard.images[0]}" class="img-fluid">
                              <div class="caption">
                                <p>
                                    ${postcard.description}
                                </p>
                              </div>
                            </a>
                        </div>
                    </div>
                <c:if test="${(count.count mod collCount) eq 0}" >
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </body>
</html>