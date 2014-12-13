<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
    <status>success</status>
    <notifications page="1" count="142">
        <c:forEach var="n" items="${gameNotifications}">
            <${n.type} id="${n.id}" version="${n.version}" recipient="${n.recipient.id}" seen="${n.seen}" game="${n.game.id}"/>
        </c:forEach>
        <c:forEach var="n" items="${challengeNotifications}">
            <${n.type} id="${n.id}" version="${n.version}" recipient="${n.recipient.id}" seen="${n.seen}" challenge="${n.challenge.id}"/>
        </c:forEach>
    </notifications>
</checkers>