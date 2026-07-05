<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="container">
    <div class="row">
        <div style="text-align: center">
            <c:forEach var="tag" items="${tagsByName}">
                <a href="/tag/${tag.name}/page/1">
                    <span style="white-space: nowrap; padding-left: 10px; font-size: ${10 + tag.count}px">
                        <fmt:message key="${tag.name}" bundle="${tagMap}"/>&nbsp;&mdash;&nbsp;${tag.count},
                    </span>
                </a>
                <br />
            </c:forEach>
        </div>
    </div>
</div>
