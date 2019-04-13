<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="tag" items="${tagsByCountry}">
    <a href="/tag/${tag.name}/page/1"><span style="white-space: nowrap; padding-left: 10px"><fmt:message key="${tag.name}" bundle="${country}"/>&nbsp;&mdash;&nbsp;${tag.count},</span></a> 
</c:forEach>