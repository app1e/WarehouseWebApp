

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>WarehouseWebApp</title>
</head>
<body>
	<div id="header">
		<h1>WarehouseWebApp</h1>
	</div>
	<div id="viewDetails">
		<br /> <br />
		<h3>Test Warehouse / Main Details</h3>
		<br /> <br />
		<table width="100%" align="center">
			<!-- column headers -->
			<c:forEach var="resultList" var="res">
				<tr>
					<td>${res.prodId}</td>
					<td>${res.prodName}</td>
					<td>${res.prodDescription}</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>