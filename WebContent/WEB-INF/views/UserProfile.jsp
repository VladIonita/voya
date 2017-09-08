<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border="2" width="70%" cellpadding="2" modelAtribute="userform">
	<tr>
		<td>First Name: ${userForm.first_name}
		<td>
	</tr>
	<tr>
		<td>Last Name: ${userForm.last_name}
		<td>
	</tr>
	<tr>
		<td>Email:${userForm.email}
		<td>
	</tr>
</table>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Account Number</th>
		<th><h3>Balance</h3></th>
		<th><h3>Currency</h3></th>
	</tr>
	<c:forEach var="account" items="${listAccount}">
		<tr>
			<td>${account.account}</td>
			<td>${account.balance}</td>
			<td>${account.currency}</td>
		</tr>
	</c:forEach>
</table>
<a href="<c:url value="/UserAdd" />" class="btn btn-primary btn-sm"
	role="button">Add New User</a>
<br />
<a href="<c:url value="/UserProfile/${userForm.id}/CreateAccount" />"
	class="btn btn-primary btn-sm" role="button">CreateAccount</a>
<br />
<a href="<c:url value="/UserProfile/${userForm.id}/Deposit" />" class="btn btn-primary btn-sm"
	role="button">Deposit</a>
<br />
<a href="<c:url value="/UserProfile/${userForm.id}/Transfer" />" class="btn btn-primary btn-sm"
	role="button">Transfer</a>
<br />

</body>
</html>