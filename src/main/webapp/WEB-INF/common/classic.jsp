<%-- 
    Document   : classic
    Created on : Sep 7, 2018, 1:54:37 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <tiles:insertAttribute name="head"/>
        <title><tiles:getAsString name="title"/></title>
    </head>
    <body>
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="body"/>
    </body>
    <footer>
        <tiles:insertAttribute name="footer"/>
    </footer>
</html>
