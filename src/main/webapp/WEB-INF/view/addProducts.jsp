<%@ page import="java.util.Calendar"%>
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
<title>Warehouse / Add product</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#count').keyup(function(ev) {
			var total = $('#count').val() * $('#price').val();
			$('#total').html((total).toFixed());
		});
		$('#price').keyup(function(ev) {
			var total = $('#price').val() * $('#count').val();
			$('#total').html((total).toFixed());
		});
	});
</script>
</head>
<body>
	<div id="header">
		<h1>Test Warehouse</h1>
	</div>
	<div id="viewDetails2">
		<%! Calendar calendar = Calendar.getInstance();
	Date currentDate = calendar.getTime();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = formatter.format(currentDate);%>
		<br /> <br />
		<h3>
			Test Warehouse / Add products <a href="./" class="back">←Back</a>
		</h3>
		<br /> <br />


		<form:form method="post" action="addProducts.add" commandName="report">

			<table>
				<th width="125px">
					<form:label path="incomes.incDate">
						<spring:message code="label.incDate" />
					</form:label> <br />
					<form:input path="incomes.incDate" value="<%= formattedDate %>" readonly="true"/>
				</th>
				<th width="200px">
					<form:label path="incomes.incSupplierName">
						<spring:message code="label.incSupplierName" />
					</form:label> <br />
					<form:input path="incomes.incSupplierName"/>
				</th>
				<th width="275px">
					<form:label path="dicStores.storName">
						<spring:message code="label.storName" />
					</form:label><br />
					<form:input path="dicStores.storName" id="storName"/>
				</th>
				<tr>
					<td>
						<form:label path="product.prodName">
							<spring:message code="label.prodName" />
						</form:label>
					</td>
					<td colspan="2">
						<form:input path="product.prodName" id="prodName" />
					<form:errors path="product.prodName"  cssClass="error"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="incomeDetails.incdCount">
							<spring:message code="label.incdCount" />
						</form:label>
					</td>
					<td>
						<form:input path="incomeDetails.incdCount" id="count"/> 
						<form:select path="product.prodDescription" >
							<form:option value="kg" label="kg" />
							<form:option value="pcs" label="pcs" />
							<form:option value="cm" label="cm" />
							<form:option value="m" label="m" />
						</form:select>
					</td>
					<td>
                       	<form:errors path="incomeDetails.incdCount" cssClass="error" />
                    </td>
				</tr>
				<tr>
					<td>
						<form:label path="incomeDetails.incdPrice">
							<spring:message code="label.incdPrice" />
						</form:label>
					</td>
					<td>
						<form:input path="incomeDetails.incdPrice" id="price"/> grn.
					</td>
					<td><form:errors path="incomeDetails.incdPrice" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Amount</td>
					<td colspan="2"><span id="total">0</span><span> grn.</span></td>
				</tr>
				<tr>
					<td colspan="3"><input align="center" type="submit"
						value="<spring:message code="label.accept"/>" />
					</td>
				</tr>
			</table>
			<form:errors path="incomes.incSupplierName" cssClass="error" /><br />
			<form:errors path="dicStores.storName" cssClass="error" />
		</form:form>
	</div>
</body>
</html>