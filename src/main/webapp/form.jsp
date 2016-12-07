<%-- 
    Document   : form
    Created on : Dec 5, 2016, 7:44:36 AM
    Author     : Sl-lAl-liN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form page</title>
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>

        <form action="FormServlet" method="GET">

            <ul class="form-style-1">

                <li><label>Full Name <span class="required">*</span></label>
                    <div style="color: #FF0000;">${nameError}</div>
                    <input type="text" name="firstname"  class="field-divided" placeholder="First" maxlength="50" value="${firstName}"/>
                    <input type="text" name="lastname" class="field-divided" placeholder="Last" maxlength="50" value="${lastName}"/>
                </li>

                <li>    
                    <label>Date of birth <span class="required">*</span></label>
                    <div style="color: #FF0000;">${dateError}</div>
                    <div style="color: #FF0000;">${dayError}</div>
                    <div style="color: #FF0000;">${monthError}</div>
                    <div style="color: #FF0000;">${yearError}</div>
                    <input type="number" name="day"  min="1" max="31" step="1" value="${day}"> day
                    <input type="number" name="month"  min="1" max="12" step="1" value="${month}"> month
                    <input type="number" name="year"  min="1918" max="2001" step="1"value="${year}"> year

                </li>
                <li>
                    <label>Gender<span class="required">*</span></label>
                    <div style="color: #FF0000;">${genderError}</div>
                    <select name="gender" class="field-select">
                        <option selected>${gender}</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>           
                    </select>
                </li>
                <li>
                    <label>Why are you applying to this job?<span class="required">*</span></label>
                    <div style="color: #FF0000;">${messageError}</div>
                    <textarea name="message" class="field-long field-textarea" maxlength="1000">${message}</textarea>
                </li>
                <li>
                    <input type="submit" value="Submit" />
                </li>
            </ul>
        </form>

    </body>
</html>
