<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
            <h1>Collection overview</h1>
        </div>
        
        <div class="container">
            <%--<fmt:setBundle basename = "com.tutorialspoint.Example" var = "lang"/>--%>

            <fmt:message key = "LV"/><br/>
      
            <c:set var="collCount" value="4" />
            <c:forEach var="postcard" items="${list}" varStatus="count">
                <c:if test="${(count.count mod collCount) eq 1}" >
                    <div class="row">
                </c:if>
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <a href="../postcard/${postcard.id}">
                              <img src="../image/${postcard.images[0]}" class="img-fluid" />
                              <div class="caption">
                                <a href="/tag/${postcard.country}">${postcard.country}</a>
                                <br />
                                sender <a href="/tag/${postcard.sender}">${postcard.sender} </a> 
                                <br />
                                <c:if test="${!empty postcard.tags}">
                                    <a href="/tag/${postcard.tags[0]}">${postcard.tags[0]}</a>
                                </c:if>
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