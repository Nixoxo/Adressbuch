<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adressdetails</title>
<jsp:useBean id="address" class="de.dhbw.webengineering.addressbook.Address" scope="request"></jsp:useBean>
<jsp:setProperty property="id" name="address"/>
</head>
<body>
	<h1>Details</h1>
	<table>
		<tr>
			<th>ID:</th>
			<td><jsp:getProperty property="id" name="address" /></td>
		</tr>
		<tr>
			<th>Anrede:</th>
			<td><jsp:getProperty property="addressform" name="address" /></td>
		</tr>

		<tr>
			<th>Name:</th>
			<td><jsp:getProperty property="name" name="address" /></td>
		</tr>
		<tr>
			<th>Vorname:</th>
			<td><jsp:getProperty property="christianname" name="address" /></td>
		</tr>
		<tr>
			<th>E-Mail:</th>
			<td><jsp:getProperty property="email" name="address" /></td>
		</tr>
		<tr>
			<th>Telefon:</th>
			<td><jsp:getProperty property="phone" name="address" /></td>
		</tr>
		<tr>
			<th>handy:</th>
			<td><jsp:getProperty property="mobile" name="address" /></td>
		</tr>
		<tr>
			<th>Straße:</th>
			<td><jsp:getProperty property="street" name="address" /></td>
		</tr>
		<tr>
			<th>Hausnummer:</th>
			<td><jsp:getProperty property="number" name="address" /></td>
		</tr>
		<tr>
			<th>PLZ:</th>
			<td><jsp:getProperty property="postcode" name="address" /></td>
		</tr>
		<tr>
			<th>Stadt:</th>
			<td><jsp:getProperty property="city" name="address" /></td>
		</tr>
		<tr>
			<th>Land:</th>
			<td><jsp:getProperty property="country" name="address" /></td>
		</tr>
		<tr>
			<th>Geburtstag:</th>
			<td><jsp:getProperty property="birthday" name="address" /></td>
		</tr>
	</table>
</body>
</html>