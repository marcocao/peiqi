<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>
</head>
<body>
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>ID</td>
			<td>Email</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Password</td>
			<td>Created Date</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.emailId}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.password}</td>
				<td>${user.createdTime}</td>
				<td><a href="<c:url value='/edit-${user.id}-user' />">Edit</a></td>
				<td><a href="<c:url value='/delete-${user.id}-user' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/new' />">Add New User</a>
</body>
</html>