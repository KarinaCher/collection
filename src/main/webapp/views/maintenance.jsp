<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel='stylesheet' href='/css/test.css'>
    <link rel='stylesheet' href='/css/main.css'>
    <script src="/js/test.js"></script>

    <title>Add/edit postcard</title>
    <style>
        td {
            padding: 10px;
        }
    </style>
</head>
<body style="padding: 50px;">
<h1>Add or edit postcard information</h1>

<form:form action="/maintenance" method="post" modelAttribute="postcard">
    <div id="errorHandler">${errorMessage}</div>
    <table>
        <tr>
            <td>Date sent:</td>
            <td><form:input path="dateSent" type="date"/></td>
        </tr>
        <tr>
            <td>Date received:</td>
            <td><form:input path="dateReceived" type="date"/></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <form:select path="country">
                    <option value="-"></option>
                    <form:option value="-"/>
                    <c:forEach items="${countries}" var="country" varStatus="i">
                        <c:choose>
                            <c:when test="${foCountry eq country.key}">
                                <form:option value="${country.key}" selected="selected">${country.value}</form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value="${country.key}">${country.value}</form:option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td><form:input path="city"/></td>
        </tr>
        <tr>
            <td>Sender:</td>
            <td>
                <c:forEach items="${senders}" var="sender" varStatus="loop">
                    <form:checkbox path="senders" value="${sender.key}" label="${sender.value}"/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Size:</td>
            <td>Width: <form:input path="width"/>, Height:
            <form:input path="height"/></tr>
        <tr>
            <td>Tag:</td>
            <td>
                <c:forEach items="${tags}" var="tag" varStatus="loop">
                    <form:checkbox path="tags" value="${tag.key}" label="${tag.value}"/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><form:textarea path="description" cols="100" rows="10"/></td>
        </tr>
        <tr>
            <td>Upload images:</td>
            <td>
                <input type="file" id="image1" name="image1"/><br/>
                <input type="file" id="image2" name="image2"/><br/>
                <input type="file" id="image3" name="image3"/><br/>
                <input type="file" id="image4" name="image4"/><br/>
                <input type="file" id="image5" name="image5"/>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><form:button>Submit</form:button></td>
        </tr>
    </table>
</form:form>
</body>
</html>