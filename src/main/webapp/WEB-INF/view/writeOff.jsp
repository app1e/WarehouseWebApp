<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
		
		
		<form:form method="post" action="writeOff.delete" commandName="report">
			<table>
				<th width="125px">
					<form:label path="outcomes.outDate">
						<spring:message code="label.outDate" />
					</form:label> <br />
					<form:input path="outcomes.outDate" value="<%= formattedDate %>" readonly="true"/>
				</th>
				<th width="200px">
					<form:label path="outcomes.outCustomer">
						<spring:message code="label.outCustomer" />
					</form:label> </br>
					<form:input path="outcomes.outCustomer"/>
				</th>
				<th width="275px">
					<form:label path="dicStores.storName">
						<spring:message code="label.storName" />
					</form:label> 
					<br />
					<form:select path="dicStores.storName" id="storName" >
						<form:options items="${dicStores}" itemValue="storName" itemLabel="storName" />
					</form:select>
				</th>
				<tr>
					<td>
						<form:label path="product.prodName">
							<spring:message code="label.prodName" />
						</form:label>
					</td>
					<td colspan="2">
						<form:select path="product.prodName" id="prodName" >
							<form:options items="${products}" itemValue="prodName" itemLabel="prodName" />
						</form:select> 
					<form:errors path="product.prodName"  cssClass="error"/>					
					
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="outcomeDetails.outdCount">
							<spring:message code="label.outdCount" />
						</form:label>
					</td>
					<td>
						<form:input path="outcomeDetails.outdCount" id="count"/> 
							<form:select path="product.prodDescription" >
								<form:option value="kg" label="kg" />
								<form:option value="pcs" label="pcs" />
								<form:option value="cm" label="cm" />
								<form:option value="m" label="m" />
							</form:select>
					</td>
					<td><form:errors path="outcomeDetails.outdCount" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3"><input align="center" type="submit"
						value="<spring:message code="label.accept"/>" />
					</td>
				</tr>
			</table>
			<form:errors path="outcomes.outCustomer" cssClass="error" /><br />
			<form:errors path="dicStores.storName" cssClass="error" />
		</form:form>
	</div>
</body>
</html>

