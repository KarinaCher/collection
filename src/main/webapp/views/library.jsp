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
    <c:set var="count" value="1"/>
    <c:forEach var="item" items="${map}">
        <c:set var="author" value="${item.key}"/>
        <c:set var="bookList" value="${item.value}"/>
        <c:set var="firstBook" value="${item.value[0]}"/>
        <div style="margin-top: 10px">
                ${firstBook.author}
            <c:if test="${not empty firstBook.originalAuthor}">(${firstBook.originalAuthor})</c:if>
            <c:forEach var="book" items="${bookList}">
                <div style="margin-left: 15px">
                        ${count}) ${book.title}
                    <c:if test="${not empty book.originalTitle}">(${book.originalTitle})</c:if>
                </div>

                <c:set var="count" value="${count + 1}"/>
            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>