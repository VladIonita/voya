<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url value="/userprofile/${id}/transfer" var="userActionUrl" />
<form:form modelAttribute="depositForm" method="post"
	action="${userActionUrl}">

	<table align="center">
		<tr>
			<td><label>Select Account From</label></td>
			<td><form:select path="account">
					<form:options items="${listAccount}" itemLabel="account"
						itemValue="id" />
				</form:select></td>
		</tr>
		<tr>
			<td><label>Select Account To</label></td>
			<td><form:select path="to">
					<form:options items="${listAccount}" itemLabel="account"
						itemValue="id" />
				</form:select></td>

		</tr>
		<tr>
			<td><label>Amount to transfer</label></td>
			<td><input name="dep" id="dep" /> <form:errors path="dep"
					class="control-label" />
		</tr>
		<tr>
			<td><input type="submit" value="Transfer"
				class="btn btn-primary btn-sm" /> or <a href="<c:url value='/' />">Cancel</a>
			</td>
		</tr>
		<tr></tr>
	</table>
</form:form>
</body>
</html>