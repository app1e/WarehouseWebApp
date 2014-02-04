<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css">
<title>Test Warehouse / Write off the products</title>

</head>
<body>

	<div id="header">
		<h1>Test Warehouse / Write off the products</h1>
	</div>
	<div id="viewDetails2">
		<%!  Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(currentDate);
            %>
		<br /> <br />
		<h3>
			Test Warehouse / Write off the products <a href="./" class="back">←Back</a>
		</h3>

		<br /> <br />
		<form action="DeleteProductServlet" method="POST">
			<table>
				<th>Date: (yyyy-mm-dd)<input type="text" name="outDate"
					value="<%= formattedDate%>" />
				</th>
				<th width="200px">Customer Name: <br /> <input type="text"
					name="outCustomer" value="" />
				</th>
				<th>Select the Store: <select name="storName">
						<c:forEach var="row" items="${stores.rowsByIndex}">
							<c:forEach var="column" items="${row}">
								<option>
									<c:out value="${column}" />
								</option>
							</c:forEach>
						</c:forEach>
				</select>
				</th>
				<tr>
					<td>Product</td>
					<td colspan="2"><select name="prodName">
							<c:forEach items="${productsList}" var="prod">
								<option>
									<c:out value="${prod.prodName}" />
								</option>
							</c:forEach>
					</select> <span class="error">${noProdMessages.prod}</span></td>
				</tr>
				<tr>
					<td>Number</td>
					<td><input type="text" id="count" name="outdCount" value="0" />
						<select name="prodDescr">
							<option value="kg">kg</option>
							<option value="pcs">pcs</option>
							<option value="cm">cm</option>
							<option value="m">m</option>
					</select></td>
					<td><span class="error">${countMessages.count}</span></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit"
						name="action" value="Accept" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

