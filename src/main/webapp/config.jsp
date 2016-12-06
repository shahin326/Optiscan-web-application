<%-- 
    Document   : config
    Created on : Dec 6, 2016, 2:47:48 PM
    Author     : Sl-lAl-liN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configure database connection</title>
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>

        <form action="Configure" method="GET">

            <ul class="form-style-1">

                <li><label>Enter Postgres localhost port number. <span class="required">*</span></label>
                    <div style="color: #FF0000;">${portError}</div>
                    <input type="number" name="port" class="field-long" min="1" max="9999" step="1" value="${port}">

                </li>
                
                <li><label>Enter the name of database. <span class="required">*</span></label>
                    <div style="color: #FF0000;">${nameError}</div>
                    <input type="text" name="databaseName" class="field-long" placeholder="Database name" maxlength="100" value="${databaseName}"/>

                </li>
                
                <li><label>Enter database username. <span class="required">*</span></label>
                    <div style="color: #FF0000;">${usernameError}</div>
                    <input type="text" name="username" class="field-long" placeholder="Username" maxlength="100" value="${username}"/>

                </li>
                
                <li><label>Enter database password. <span class="required">*</span></label>
                    <div style="color: #FF0000;">${passwordError}</div>
                    <input type="text" name="password" class="field-long" placeholder="Password" maxlength="100" value="${password}"/>

                </li>
                

                <li>
                    <input type="submit" value="Submit" />
                </li>
            </ul>
        </form>

    </body>
</html>
