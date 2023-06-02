<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel='stylesheet' href='/css/test.css'>
    <link rel='stylesheet' href='/css/main.css'>

    <title>Library</title>
</head>
<body>
<jsp:include page="parts/bundles.jsp"/>
<jsp:include page="parts/menu.jsp"/>
<div class="container">
    <c:forEach var="book" items="${list}" varStatus="count">
        <div>
            <div style="margin-top: 10px">
                <c:if test="${not empty book.author}">${book.author}</c:if>
                <c:if test="${not empty book.originalAuthor}">(${book.originalAuthor})</c:if>
                <div style="margin-left: 15px">
                    ${book.title}
                    <c:if test="${not empty book.originalTitle}">(${book.originalTitle})</c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>