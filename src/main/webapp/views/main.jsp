<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <meta http-equiv="Content-Type"
          content="text/html; charset=utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <title>Collection overview</title>
    </head>
    <body>
        <table class="table table-bordered">
            <tr>
                <c:forEach var="postcard" items="${list}">
                    <td>
                        id: ${postcard.id}<br />
                        date sent: ${postcard.dateSent}<br />
                        sender: ${postcard.sender}
                    </td>
                </c:forEach>
            </tr>
        </table>
        
    </body>
</html>