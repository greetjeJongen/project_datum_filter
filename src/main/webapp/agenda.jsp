<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mijn agenda</title>
</head>
<body>
<h1>Mijn Afspraken
</h1>
<jsp:include page="header.jsp"/>

<form action="Controller" method="get">
    <p><label for="from">From${dateFormat}</label><input id="from" type="date" name="from"></p>
    <p><label for="until">Until${dateFormat}</label><input id="until" type="date" name="until"></p>
    <input type="hidden" name="command" value="Filter">
    <p><label><input type="radio" name="mode" value="java" class="disableJS">Filter By Java</label></p>
    <p><label><input type="radio" name="mode" value="sql" class="disableJS">Filter By SQL</label></p>
    <p><label><input type="radio" name="mode" value="sql" id="enableJS">Filter By JS</label></p>
    <p><input type="submit" value="Filter" id="button"></p>
</form>
<h2 id="h2">${filterInfo}</h2>
<%--@elvariable id="agenda" type=""--%>
<c:if test="${not empty agenda}">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Date</th>
            <th>Time</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${agenda}">
            <tr id="${item.id}">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td class="date">${item.getLocalDateAsString()}</td>
                <td>${item.getLocalTimeAsString()}</td>
                <td><a href="Controller?command=DeleteConfirmation&id=${item.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<script src="js/filterAgenda.js"></script>
</body>
</html>