<%-- 
    Document   : SaveDog
    Created on : Sep 21, 2012, 10:19:18 PM
    Author     : DX
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.myapp.struts.DBConnecter"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="MsStyle.css" />
        <script language="JavaScript">
            function onButton(id){
                document.getElementById(id).src="Resources/button3_1.png";
            }
            function outButton(id){
                document.getElementById(id).src="Resources/button3_2.png";
            }
            function jump(target){
                 window.location.href="./"+target+".jsp";
            }
        </script>
        <title>Save a Dog</title>
    </head>
    <body>
        <%
        String dogid=request.getParameter("selectdog");
        if(dogid==null){
            out.println("<h1>Helpless Dog List</h1>");
        }else{
            out.println("<h1>Make a Donation</h1>");
        }
        %>
        <div id="doglist">
            <input type="button" value="Back to Homepage" onclick="jump('DogList')" />
        <table>
            <tr>
                <th>ID</th>
                <th>Photo</th>
                <th>Name</th>
                <th>Person who finds him</th>
                <th>Description</th>
                <th>Money needed</th>
                <th>Money Received</th>
                <th>Options</th>
            </tr>
        <%
        String warn=(String)session.getAttribute("donateresult");
              if(warn!=null){
                      out.println("<script language='JavaScript'>alert('"+warn+"')</script>");
                      request.getSession().setAttribute("donateresult",null);
               }        
        String query="";
        Statement st=DBConnecter.getDBConnection().createStatement();
        if(dogid==null){
            query="select * from dogs";
        }else{
            query="select * from dogs where id="+dogid;
        }
        ResultSet rs=st.executeQuery(query);
        while(rs.next()!=false){
            Integer id=rs.getInt(1);
            String dogname=rs.getString(2);
            String people=rs.getString(3);
            String des=rs.getString(5);
            Double mn=rs.getDouble(6);
            Double cm=rs.getDouble(7);
            Double gap=mn-cm;
            out.println("<tr>");
            out.println("<td>"+id+"</td>");
            out.println("<td><img src='/SaveDogsCOM/showPic.do?pid="+id+"'/></td>");
            out.println("<td>"+dogname+"</td>");
            out.println("<td>"+people+"</td>");
            out.println("<td>"+des+"</td>");
            out.println("<td>"+mn+"</td>");
            out.println("<td>"+cm+"</td>");
            out.println("<td><div id='option'>");
            if(gap>0){
              out.println("<p id='red'>This Dog still need $"+gap+" to be saved</p>");
              if(dogid==null){
              out.println("<a href='SaveDog.jsp?selectdog="+id+"'>");
              out.println("<img id='Dog"+id+"' src='Resources\\button3_2.png'onmouseover='onButton(\"Dog"+id+"\")' onmouseout='outButton(\"Dog"+id+"\")'/>");
              out.println("</a>");
                           }
                       }else{
                out.println("<p id='green'>This Dog has been saved</p>");
                       }
            out.println("</div></td>");
            out.println("</tr>");
        }
        %>
        </table>
        <%
        if(dogid!=null){
            out.println("<div id='donateform'>");
            out.println("<h2>Fill in necessary information for your Donation</h2>");
            out.println("<form id='donate' method='GET' action='/SaveDogsCOM/donate.do'>");  
            out.println("Target Dog ID:<input type='text' name='id' value='"+dogid+"' size='10' readonly/><br/>");
            out.println("Donate Amount:<input type='text' name='amount' value='' size='20'/><br/>");
            out.println("Bank Account Number(7-digit NUMBER):<input type='text' name='account' value=''size='20'><br/><input type='submit' name='submit' value='Confirm Donate'>");
            out.println(" <input type='button' value='Back to dog list' onclick='jump(\"SaveDog\")' />");
            out.println("</form>");
            out.println("</div>");
        }
        %>
        </div>
    </body>
</html>
