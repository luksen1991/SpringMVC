<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>LISTA:</h1>
<table>
<#list result as res>
<tr>
<td>${res.id}</td>
<td>${res.content}</td>
<td>${res.tittle}</td>
</tr>
</#list>
</table>		
</body>
</html>