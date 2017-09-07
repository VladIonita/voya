<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:choose>
	<c:when test="${userForm['new']}">
		<h3>Add User</h3>
	</c:when>
	<c:otherwise>
		<h3>Update User</h3>
	</c:otherwise>
</c:choose>
<br />

<spring:url value="/UserAdd" var="userActionUrl" />
<form:form modelAttribute="userForm" action="${userActionUrl}"
	method="post">
	<form:hidden path="id" />

	<table align="center">
		<tr>
			<td><form:label path="first_name">First Name</form:label></td>
			<td><form:input path="first_name" name="first_name"
					id="first_name" />
			<form:errors path="first_name" class="control-label" /></td>
		</tr>
		<tr>
			<td><form:label path="last_name">Last Name</form:label></td>
			<td><form:input path="last_name" name="last_name" id="last_name" />
			<form:errors path="last_name" class="control-label" /></td>

		</tr>
		<tr>
			<td><form:label path="email">Email</form:label></td>
			<td><form:input path="email" name="email" id="email" />
			<form:errors path="email" class="control-label" /></td>
		</tr>
		<tr>
			<td><c:choose>
					<c:when test="${userForm['new']}">
						<input type="submit" value="Register"
							class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/' />">Cancel</a>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr></tr>
	</table>
</form:form>

</body>
</html>