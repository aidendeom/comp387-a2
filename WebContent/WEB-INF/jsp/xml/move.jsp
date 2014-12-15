<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
<status>success</status>
 <game id="${checkerboard.id}" version="${checkerboard.version}" status="${checkerboard.status.id}">
  <firstPlayer refid="${checkerboard.firstPlayer.id}" />
  <secondPlayer refid="${checkerboard.secondPlayer.id}" />
  <currentPlayer refid="${checkerboard.currentPlayer.id}" />
  <pieces>${checkerboard.pieces}</pieces>
 </game>
</checkers>