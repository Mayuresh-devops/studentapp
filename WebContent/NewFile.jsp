<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome Page</title>
</head>
<body>

<% 
    String user = (String) request.getAttribute("username");
    if (user == null) {
        user = "Guest";
    }
%>

<h1>Welcome, <%= user %>!</h1>
<p>This is a dynamic page that greets the user.</p>

</body>
</html>
