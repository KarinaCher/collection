<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="menuBar">
    <table>
        <tr>
            <td><a href="/"><fmt:message key="myPostcards" bundle="${texts}"/></a></td>
            <td><a href="/other/page/1"><fmt:message key="otherPostcards" bundle="${texts}"/></a></td>
            <td><a href="/statistics/country"><fmt:message key="statistics" bundle="${texts}"/></a></td>
            <td><a href="https://www.postcrossing.com/user/ljulitka/gallery" target="_blank">Postcrossing.com</a></td>
<%--            <td><a href="/book" style="color: #67b168">Library</a></td>--%>
        </tr>
    </table>
</div>