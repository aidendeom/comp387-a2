<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
<status>success</status>
 <challenges>
 <c:forEach var="challenge" items="${challenges }">
 <challenge id="${challenge.id }" version="${challenge.version }" status="${challenge.status.id }" > 
  <challenger refid="${challenge.challenger.id }"/>
  <challengee refid="${challenge.challengee.id }"/> 
 </challenge>
</c:forEach>
 </challenges>
</checkers>