<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">

<title>Test Warehouse</title>
</head>
<body>
	<div id="header">
		<h1>WarehouseWebApp</h1>
	</div>
	<div id="viewDetails">
		<br /> <br />
		<h3>Test Warehouse / Main Details</h3>
		<br /> <br />
		<c:if test="${!empty productsList}">
			<table width="100%" align="center">
				<tr>
					<th>Product Id</th>
					<th>Product Name</th>
					<th>Product Description</th>
				</tr>
				<c:forEach items="${productsList}" var="prod">
					<tr>
						<td align="center">${prod.prodId}</td>
						<td align="center">${prod.prodName}</td>
						<td align="center">${prod.prodDescription}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br /><br />
            <a href="addProducts" class="add"><span class="link">Add</span></a>
            <a href="writeOff" class="delete"><span class="link">Write off</span></a>
	</div>
</body>
</html>