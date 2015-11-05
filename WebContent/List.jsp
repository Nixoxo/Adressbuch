<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Addressbook</title>
<jsp:useBean id="addressList" class="de.dhbw.webengineering.addressbook.AddressList" scope="session"/>
</head>
<body>
<h1>Addressbook</h1>
<table>
<tr>
<td>Name</td>
<td>Telephone</td>
<td>Email</td>
    <c:foreach items="${addressList.getList()}" var="address">
       <tr>
        <td><a href=${address.name} + "_" + ${address.christianname} + ".jsp">${address.name}</a></td>
        <td>${address.christianname}</td>
        <td>${address.email}</td>
       </tr>
    </c:foreach>
</table>
</body>
</html>
