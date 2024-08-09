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
        <div style="margin-top: 20px">
            <span style="font-size: 16px">${firstBook.author}</span>
            <c:if test="${not empty firstBook.originalAuthor}">(${firstBook.originalAuthor})</c:if>
                <table class="library">
                    <c:forEach var="book" items="${bookList}" varStatus="loop">
                        <tr>
                            <td class="library_1">${count}/</td>
                            <td class="library_2">${loop.index + 1}</td>
                            <td class="library_3">
                                ${book.title}
                                <c:if test="${not empty book.originalTitle}"><br />(${book.originalTitle})&nbsp;</c:if>
                            </td>
                            <td class="library_4"><c:if test="${not empty book.isbn}">
                                <a href="https://www.google.com/search?q=ISBN+${book.isbn}">ISBN: ${book.isbn}</a>a></c:if>&nbsp;
                            </td>
                            <td class="library_5"><c:if test="${not empty book.note}">${book.note}</c:if>&nbsp;</td>
                        </tr>

                        <c:set var="count" value="${count + 1}"/>
                    </c:forEach>
                </table>
        </div>
    </c:forEach>
</div>
</body>
</html>