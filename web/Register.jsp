<%-- 
    Document   : Register
    Created on : Sep 30, 2012, 11:05:08 PM
    Author     : DX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="MsStyle.css" />
        <script language="JavaScript">
            function jump(target){
                 window.location.href="./"+target+".jsp";
            }
        </script>
        <title>User Register</title>
    </head>
    <body>
        <%
        String warn=(String)session.getAttribute("warning");
              if(warn!=null){
                      out.println("<script language='JavaScript'>alert('"+warn+"')</script>");
                      request.getSession().setAttribute("warning",null);
               } 
        %>
        <h1>User Register</h1>
        <div id="registerform">
        <form  action="/SaveDogsCOM/register.do" method="post">
                <span>Username:</span> <input  name="username" type="text" value="" /><br/>
                <span>Password:</span> <input  name="password1" type="password" value="" /><br/>
                <span>Retype Password:</span><input  name="password2" type="password" value="" /><br/>
                <input type="submit" value="Register" />
                <input type="button" value="Back to Homepage" onclick="jump('DogList')" />		
        </form>
        </div>
    </body>
</html>
