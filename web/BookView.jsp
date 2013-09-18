<%-- 
    Document   : BookView
    Created on : Sep 17, 2012, 11:32:08 AM
    Author     : iJab
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.DBConnecter"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.DataInputStream"%>
<%@ page language="java" %>
<%@ page import="java.io.*,java.sql.*,java.util.*,java.text.*"%>

<!DOCTYPE html>
<html:html lang="true">
    <head>
        <meta http-equiv ="Content-Type" content ="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="MsStyle.css" />
        <script language="JavaScript">
            function jump(target){
                 window.location.href="./"+target+".jsp";
            }
        </script>
        <title>Register a Dog on Save Dogs</title>
    </head>
    <body>
    <%
            String userName = (String)session.getAttribute("dxlogin");
            if(userName == null){
                
              // redirect to login page
              request.getSession().setAttribute("warning", "Please login to register a dog!");
              response.sendRedirect("DogList.jsp");
            }
     %>
    
    <%
		
                String isRegistered = (String)request.getSession().getAttribute("registered");
                if( isRegistered == null)
                {
                    
                }
                else
                {
                    request.getSession().setAttribute("registered", null);
                    if(isRegistered.equals("true"))
                    {
                        out.println("<H2>You have registered a dog successfully and Thanks!</H2>");  
                        out.println("<BR><input type='button' value='AddAnotherDog' onclick='jump(\"BookView\")' />");
                        out.println("<BR><input type='button' value='BackToHomepage' onclick='jump(\"DogList\")' />");
                        out.println("<BR><input type='button' value='ViewDogList' onclick='jump(\"SaveDog\")' />");
                        out.println("<!--");
                    }
                    else
                    {
                        out.println("<H3>Ooops, something wrong with our website!</H3>");  
                        out.println("<BR><input type='button' value='AddAnotherDog' onclick='jump(\"BookView\")' />");
                        out.println("<BR><input type='button' value='BackToHomepage' onclick='jump(\"DogList\")' />");
                        out.println("<BR><input type='button' value='ViewDogList' onclick='jump(\"SaveDog\")' />");
                    }
                }
                
	%>

        <h1>Register a dog that needs help!</h1>
        <div id="registerform">
        <html:form enctype="multipart/form-data" action="/registerDog" method="post">
            <span>Dog Name:</span> <input id="dog_name" name="dog_name" type="text" value="Test Dog" /><br/>
                <span>Fund Needs:</span> <input id="dog_fund" name="dog_fund" type="text" value="234" /><br/>
                <span>Description:</span><br/><textarea id="dog_desc" name="dog_desc">This is a dog for test</textarea><br/>
                <span>Upload A Dog Photo:</span><html:file property="theFile"/> <br/>
                <input type="submit" value="Register" />
                <input type="button" value="Back to Homepage" onclick="jump('DogList')" />		
        </html:form>
        </div>
    <%
        if(isRegistered != null && isRegistered.equals("true"))
        {
            out.println("-->");
        }
    %>

    </body>
</html:html>
