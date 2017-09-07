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
			<td><a href="<c:url value="/UserEdit/${user.id}" />"
					class="btn btn-primary btn-sm" role="button">Edit</a>
					<a href="<c:url value="/UserProfile/${user.id}" />"
					class="btn btn-primary btn-sm" role="button">Profile</a>
					<a href="<c:url value="/UserDelete" />"
					class="btn btn-primary btn-sm" role="button">Delete</a>
					</td>
		</tr>
	</c:forEach>
</table>
		<a href="<c:url value="/UserAdd" />"
					class="btn btn-primary btn-sm" role="button">Add New User</a>

</body>
</html>