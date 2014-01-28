<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css">
<title>Warehouse / Add product</title>

</head>
<body>
	<form:form method="post" action="addTest.add" commandName="product">
 
    <table>
    <tr>
        <td>Product Name</td>
        <td><form:input path="prodName" />
        <form:errors path="prodName" cssClass="product" />
        </td>
    </tr>
    <tr>
        <td>Product Description</td>
        <td><form:input path="prodDescription" /></td>
    </tr>
    

    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.accept"/>"/>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>