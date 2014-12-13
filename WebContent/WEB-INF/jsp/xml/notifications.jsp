<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
    <status>success</status>
    <notifications page="${page}" count="${length}">
        <c:forEach var="n" items="${notifications}">
            <${n.type.stringVal} id="${n.id}" version="${n.version}" recipient="${n.recipient.id}" seen="${n.wasSeen}" ${n.target}/>
        </c:forEach>
    </notifications>
</checkers>