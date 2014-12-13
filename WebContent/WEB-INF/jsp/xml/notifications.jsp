<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
    <status>success</status>
    <notifications page="1" count="14">
        <c:forEach var="n" items="${gameNotifications}">
            <${n.type.toString} id="n.id" />
        </c:forEach>
    </notifications>
</checkers>