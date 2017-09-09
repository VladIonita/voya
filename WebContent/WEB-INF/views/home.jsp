<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
		<th></th>
	</tr>
	<c:forEach var="user" items="${listUser}">
		<tr>
			<td>${user.first_name}</td>
			<td>${user.last_name}</td>
			<td>${user.email}</td>
			<td><a href="<c:url value="/edit/${user.id}" />"
					class="btn btn-primary btn-sm" role="button">Edit</a>
					<a href="<c:url value="/userprofile/${user.id}" />"
					class="btn btn-primary btn-sm" role="button">Profile</a>
					<a href="<c:url value="/userdelete/${user.id}" />"
					class="btn btn-primary btn-sm" role="button">Delete</a>
					</td>
		</tr>
	</c:forEach>
</table>
		<a href="<c:url value="/edit" />"
					class="btn btn-primary btn-sm" role="button">Add New User</a>

</body>
</html>