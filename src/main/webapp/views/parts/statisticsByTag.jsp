<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            by count<br />
            <c:forEach var="tag" items="${tags}">
                <a href="/tag/${tag.name}/page/1">
                    <span style="white-space: nowrap; padding-left: 10px">
                        ${tag.count}&nbsp;&mdash;&nbsp;<fmt:message key="${tag.name}" bundle="${tagMap}"/>,
                    </span>
                </a> 
                <br />
            </c:forEach>
        </div>
        <div>
            by name <br />
            <c:forEach var="tag" items="${tagsByName}">
                <a href="/tag/${tag.name}/page/1">
                    <span style="white-space: nowrap; padding-left: 10px">
                        <fmt:message key="${tag.name}" bundle="${tagMap}"/>&nbsp;&mdash;&nbsp;${tag.count},
                    </span>
                </a> 
                <br />
            </c:forEach>
        </div>
    </div>
</div>
