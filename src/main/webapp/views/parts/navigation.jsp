<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<nav>
    <ul class="pagination">
        <c:forEach begin="1" end="${pages}" var="page">
            <li class="page-item <c:if test="${currenctPage eq page}">active</c:if>">
                <a class="page-link" href="<c:if test="${!empty tag}">/tag/${tag}</c:if>/page/${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</nav>