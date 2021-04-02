<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mijn agenda</title>
</head>
<body>
<h1>Add an appointment
</h1>
<jsp:include page="header.jsp"/>
<c:if test="${not empty errors}">
    <div style="background-color: #ec7878">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="Controller?command=Add" method="post" novalidate>
    <p><label for="name">Name: </label><input type="text" id="name" name="name" value="${previousName}"></p>
    <p><label for="date">Date (yyyy-mm-dd): </label><input type="date" id="date" name="date" value="${previousDate}">
    </p>
    <p><label for="time">Time (hh:mm): </label><input type="time" id="time" name="time" value="${previousTime}"></p>
    <p><input type="submit" value="Add Appointment"></p>
</form>
</body>
</html>