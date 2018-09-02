<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<#list results as res>
	<table>
		<tr>
			<td>${res.id}</td>
			<td>${res.tittle}</td>
			<td>${res.content}</td>
		</tr>
	</table>
	</#list>
</body>
</html>