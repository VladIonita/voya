<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

First Name: ${user.first_name}
<br />
Last Name: ${user.last_name}
<br />
Email:${user.email}
<br />
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Account Number</th>
		<th><h3>Balance</h3></th>
		<th><h3>Currency</h3></th>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
<a href="<c:url value="/UserAdd" />" class="btn btn-primary btn-sm"
	role="button">Add New User</a>
<br />
<a href="<c:url value="/CreateAccount" />"
	class="btn btn-primary btn-sm" role="button">CreateAccount</a>
<br />
<a href="<c:url value="/Deposit" />" class="btn btn-primary btn-sm"
	role="button">Deposit</a>
<br />
<a href="<c:url value="/Transfer" />" class="btn btn-primary btn-sm"
	role="button">Transfer</a>
<br />

</body>
</html>