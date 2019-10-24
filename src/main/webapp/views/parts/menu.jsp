<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="/"><fmt:message key="myPostcards" bundle="${texts}"/></a></li>
            <li><a href="/other/page/1"><fmt:message key="otherPostcards" bundle="${texts}"/></a></li>
            <li><a href="/statistics/sender"><fmt:message key="statistics" bundle="${texts}"/></a></li>
            <li><a href="https://www.postcrossing.com/user/ljulitka/gallery" target="_blank">Postcrossing.com</a></li>
        </ul>
    </div>
</nav>