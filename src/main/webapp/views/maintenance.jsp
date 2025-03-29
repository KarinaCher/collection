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
</head>
<body style="padding: 50px;">
<h1>Add or edit postcard information</h1>
<form method="post" action="/update">
    <h2>Date sent:</h2>
    <input type="text" path="dateSent" class="date" name="dateSent" value=""/>
    <br/>

    <h2>Date received:</h2>
        <input type="text" path="dateReceived" class="date" name="dateReceived" value=""/>
    <br/>

    <h2>Country:</h2>
    <select name="countryList" id="countryList">
        <option value="-"></option>
        <c:forEach items="${countries}" var="country" varStatus="i">
            <option value="${country.key}">${country.value}</option>
        </c:forEach>
    </select>
    <br/>

    <h2>City:</h2>
    <input type="text" path="city" name="city" value=""/>
    <br/>

    <h2>Sender:</h2>
    <c:forEach items="${senders}" var="sender" varStatus="i">
        <span style="margin-right: 20px; white-space: nowrap"><input
                type="checkbox"
                id="sender"
                name="sender${i}"
                value="${sender.key}">&nbsp;<label for="tag${i}">${sender.value}</label>
        </span>
    </c:forEach>
    <br/>

    <h2>Size:</h2>
    <input type="text" path="width" name="width" value=""/>(w) x <input type="text" path="height" name="height"
                                                                              value=""/>(h)<br/>
    <h2>Tag:</h2>
    <c:forEach items="${tags}" var="tag" varStatus="i">
        <span style="margin-right: 20px; white-space: nowrap"><input
                type="checkbox"
                id="tag"
                name="tag${i}"
                value="${tag.key}">&nbsp;<label for="tag${i}">${tag.value}</label>
        </span>
    </c:forEach>
    <br/>

    <h2>Description</h2>
    <textarea cols="100" rows="10"></textarea>
    <br/>

    <h2>Upload images:</h2>
    <input type="file" id="image" name="image1"/><br/>
    <input type="file" id="image" name="image2"/><br/>
    <input type="file" id="image" name="image3"/><br/>
    <input type="file" id="image" name="image4"/><br/>
    <input type="file" id="image" name="image5"/><br/>
    <input type="submit">

</form>
</body>
</html>