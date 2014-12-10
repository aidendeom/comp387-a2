<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
<status>success</status>
 <player firstName="${player.firstName }" lastName="${player.lastName }" email="${player.email }" id="${player.id }" version="${player.version }"> 
  <user username="${player.user.username}" id="${player.user.id}" version="${player.user.version}"/> 
 </player >
</checkers>