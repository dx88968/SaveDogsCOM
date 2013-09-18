<%-- 
    Document   : MyAccount
    Created on : Sep 30, 2012, 9:13:20 PM
    Author     : DX
--%>

<%@page import="org.github.dx88968.monitor.logger.TraceLevel"%>
<%@page import="org.github.dx88968.monitor.logger.DirectOutputTracker"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.myapp.struts.DBConnecter"%>
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
        <title>My Accounts</title>
    </head>
    <body>
        <h1>My Donation Records</h1>
        <div id="records">
            <input type="button" value="Back to Homepage" onclick="jump('DogList')" />
            <table>
            <tr>
                <th>ID</th>
                <th>Dog Name</th>
                <th>amount</th>
                <th>Date</th>
            </tr>
        <%
        DirectOutputTracker.instance.print(null, TraceLevel.DEBUG, "MyAccount.jsp");
        String username=(String)session.getAttribute("dxlogin");        
        String query="select * from records where donator='"+username+"'";
        Statement st=DBConnecter.getDBConnection().createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()!=false){
            Integer id=rs.getInt(1);
            String dogname=rs.getString(3);
            Double amount=rs.getDouble(4);
            Date date=rs.getDate(5);
            out.println("<tr>");
            out.println("<td>"+id+"</td>");
            out.println("<td>"+dogname+"</td>");
            out.println("<td>"+amount+"</td>");
            out.println("<td>"+date+"</td>");
            out.println("</tr>");
        }
        %>
        </table>
        </div>
    </body>
</html>
