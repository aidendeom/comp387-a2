<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
    <status>success</status>
    <${notification.type.stringVal} id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.wasSeen}" ${notification.target}/>
</checkers>