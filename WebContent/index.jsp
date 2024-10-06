<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User Data</title>
    <style>
        div.ex {
            text-align: right;
            width: 300px;
            padding: 10px;
            border: 5px solid grey;
            margin: 0 auto;
        }

        table {
            width: 100%;
        }

        table td {
            padding: 8px;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 4px 0;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Student Registration Form</h1>
    <div class="ex">
        <form action="registrationController" method="post">
            <table>
                <tr>
                    <td><label for="fullname">Student Name</label></td>
                    <td><input type="text" id="fullname" name="fullname" required /></td>
                </tr>
                <tr>
                    <td><label for="address">Student Address</label></td>
                    <td><input type="text" id="address" name="address" required /></td>
                </tr>
                <tr>
                    <td><label for="age">Student Age</label></td>
                    <td><input type="text" id="age" name="age" required /></td>
                </tr>
                <tr>
                    <td><label for="qual">Student Qualification</label></td>
                    <td><input type="text" id="qual" name="qual" required /></td>
                </tr>
                <tr>
                    <td><label for="percent">Student Percentage</label></td>
                    <td><input type="text" id="percent" name="percent" required /></td>
                </tr>
                <tr>
                    <td><label for="yop">Year Passed</label></td>
                    <td><input type="text" id="yop" name="yop" required /></td>
                </tr>
            </table>
            <input type="submit" value="Register" />
        </form>
    </div>
</body>
</html>
