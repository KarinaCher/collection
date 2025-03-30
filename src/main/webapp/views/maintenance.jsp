<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<form method="post" action="/update" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Date sent:</td>
            <td><input type="text" path="dateSent" class="date" name="dateSent" value=""/></td>
        </tr>
        <tr>
            <td>Date received:</td>
            <td><input type="text" path="dateReceived" class="date" name="dateReceived" value=""/></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <select name="countryList" id="countryList">
                    <option value="-"></option>
                    <c:forEach items="${countries}" var="country" varStatus="i">
                        <option value="${country.key}">${country.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" path="city" name="city" value=""/></td>
        </tr>
        <tr>
            <td>Sender:</td>
            <td>
                <c:forEach items="${senders}" var="sender" varStatus="i">
                    <span style="margin-right: 20px; white-space: nowrap"><input
                            type="checkbox"
                            id="sender"
                            name="sender${i}"
                            value="${sender.key}">&nbsp;<label for="tag${i}">${sender.value}</label>
                    </span>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Size:</td>
            <td>
                <input type="text" path="width" name="width" value=""/>(w)
                x <input type="text" path="height" name="height" value=""/>(h)
            </td>
        </tr>
        <tr>
            <td>Tag:</td>
            <td>
                <c:forEach items="${tags}" var="tag" varStatus="i">
                    <span style="margin-right: 20px; white-space: nowrap"><input
                            type="checkbox"
                            id="tag"
                            name="tag${i}"
                            value="${tag.key}">&nbsp;<label for="tag${i}">${tag.value}</label>
                    </span>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea cols="100" rows="10"></textarea></td>
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
            <td><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>