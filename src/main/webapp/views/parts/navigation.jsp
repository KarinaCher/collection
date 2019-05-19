<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var="filter" value="" />
<c:if test="${!empty itemId}">
    <c:set var="filter" value="/${navPath}/${itemId}" />
</c:if>
<nav>
    <ul class="pagination">
        <c:forEach begin="1" end="${pages}" var="page">
            <li class="page-item <c:if test="${currentPage eq page}">active</c:if>">
                <a class="page-link" href="${parentPath}${filter}/page/${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</nav>