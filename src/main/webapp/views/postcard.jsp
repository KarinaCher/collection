<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <title>Postcard</title>
    </head>
    <body>
        <jsp:include page="parts/bundles.jsp" />
        <jsp:include page="parts/menu.jsp" />
        
        <c:if test="${!empty postcard}">
            <div class="container">
                <div class="row">
                  <div class="col-sm-6">
                    <img src="../image/lg/${postcard.images[0]}"/>
                  </div>
                  <div class="col-sm-6">
                      <table class="table table-hover">
                          <tr>
                              <td>Date sent</td>
                              <td><fmt:formatDate value="${postcard.dateSent}" pattern="dd MMM yyyy"/></td>
                          </tr>
                          <tr>
                              <td>Date received</td>
                              <td><fmt:formatDate value="${postcard.dateReceived}" pattern="dd MMM yyyy"/></td>
                          </tr>
                          <tr>
                              <td>From</td>
                              <td><fmt:message key="${postcard.country}" bundle="${country}"/>, ${postcard.city}</td>
                          </tr>
                          <tr>
                              <td>Sender</td>
                              <td>
                                  <c:forEach var="sender" items="${postcard.senders}">
                                    <a href="/tag/${sender}/page/1"><fmt:message key="${sender}" bundle="${senderMap}"/></a>, 
                                  </c:forEach>
                              </td>
                          </tr>
                          <tr>
                              <td>Size</td>
                              <td>${postcard.height} x ${postcard.width}</td>
                          </tr>
                          <tr>
                              <td>Notes</td>
                              <td>${fn:replace(postcard.description, '|', '<br />')}</td>
                          </tr>
                      </table>
                  </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
