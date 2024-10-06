<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Display Student Information</title>
    <style>
        table#nat {
            width: 50%;
            background-color: #c48ec5;
            border-collapse: collapse;
        }

        table#nat td, table#nat th {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        table#nat tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>  

<!-- Fetching data using EL (Expression Language) -->
<table id="nat">
    <tr>
        <td>Full Name</td>
        <td>${param.fullname}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${param.address}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${param.age}</td>
    </tr>
    <tr>
        <td>Qualification</td>
        <td>${param.qual}</td>
    </tr>
    <tr>
        <td>Percentage</td>
        <td>${param.percent}</td>
    </tr>
    <tr>
        <td>Year of Passout</td>
        <td>${param.yop}</td>
    </tr>
</table>

</body>
</html>
