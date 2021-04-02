<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My agenda</title>
</head>
<body>
<h1>My Agenda
</h1>
<jsp:include page="header.jsp"/>

<c:choose>
    <c:when test="${appointment != null}">
        <p>Are you sure you want to delete ${appointment.name} with id ${appointment.id}?</p>
        <form action="Controller?command=Delete&id=${appointment.id}" method="post">
            <input type="submit" value="Sure">
        </form>
        <p><a href="Controller?command=Overview">Cancel</a></p>

    </c:when>
    <c:otherwise>
        <p>Nothing to delete</p>
    </c:otherwise></c:choose>
</body>
</html>