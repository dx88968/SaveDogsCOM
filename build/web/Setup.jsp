<%-- 
    Document   : Setup
    Created on : Sep 20, 2012, 1:19:12 PM
    Author     : DX
--%>

<%@page import="org.github.dx88968.monitor.logger.TraceLevel"%>
<%@page import="org.github.dx88968.monitor.logger.DirectOutputTracker"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="com.myapp.struts.DBConnecter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="MsStyle.css" />
        <title>Setup...</title>
    </head>
    <body>
        <H2>Setup this Web site</H2>
        Firstly, load the configure file...<br/>
        <%
        DirectOutputTracker.instance.print(null, TraceLevel.ALL, "Initial message");
        boolean needconfig=false;
         String path=getServletContext().getRealPath("/Setup.jsp"); 
         File configure=new File(path+"-config.txt");
         DBConnecter.setConfigPath(path+"-config.txt");
         DBConnecter.setPath(path.split("Setup.jsp")[0]);
         BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(configure));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                DBConnecter.dispatchConfig(tempString);
                line++;
            }
            reader.close();
            if(DBConnecter.getDBConnection()==null){
                out.println("Errors detected in config file!");
                DBConnecter.setNeedInit(true);
                needconfig=true;
            }else{
                out.println("Nice config file!");
                needconfig=false;
            }
        } catch (IOException e) {
            needconfig=true;
            DBConnecter.setNeedInit(true);
            out.println("Have not find the config file!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        %>
        <%
        if(needconfig){
            out.println("<br/>Please input your Mysql Account, Mysql service port and the Database name you assigned for this Web site (Must be a valid database name!)");
            out.println("<form  method='GET' action='/SaveDogsCOM/configDB.do'>");  
            out.println("User:<input type='text' name='user' value=''>");
            out.println("Password:<input type='text' name='password' value=''>"
                         +"Port:<input type='text' name='port' value='3306'>"
                         +"Database name:<input type='text' name='DBname' value=''>"
                         + "<input type='submit' name='submit' value='Submit'>");
            out.println("</form>"); 
               }else{
               out.println("Config Success!");
               if(DBConnecter.getNeedInit()){
                    DBConnecter.initDB();
                    DBConnecter.setNeedInit(false);
                             }
               response.sendRedirect("./DogList.jsp");
               }
        %>
    </body>
</html>
