<%-- 
    Document   : DogList
    Created on : Sep 17, 2012, 4:32:52 PM
    Author     : DX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="MsStyle.css" />
        <script language="JavaScript">
            function jump(target){
                 window.location.href="./"+target+".jsp";
            }
            function p1changeLarger(){
                document.getElementById("p1").style.width=500;
                document.getElementById("p1").style.height=750;
            }
            function p1changeBack(){
                document.getElementById("p1").style.width=400;
                document.getElementById("p1").style.height=600;
            }
            function p2changeLarger(){
                document.getElementById("p2").style.width=500;
                document.getElementById("p2").style.height=750;
            }
            function p2changeBack(){
                document.getElementById("p2").style.width=400;
                document.getElementById("p2").style.height=600;
            }
            function showS1(){
                document.getElementById("s1").style.display="none";
            }
            function recoverS1(){
                document.getElementById("s1").style.display="block";
            }
            function chooseList1(){
                document.getElementById("pl1").src="Resources/List1_1.PNG";
                document.getElementById("pl2").src="Resources/List2_2.PNG";
                document.getElementById("s1").style.display="block";
                document.getElementById("s2").style.display="none";
            }
            function chooseList2(){
                document.getElementById("pl1").src="Resources/List1_2.PNG";
                document.getElementById("pl2").src="Resources/List2_1.PNG";
                document.getElementById("s1").style.display="none";
                document.getElementById("s2").style.display="block";
            }
            function onButton1(){
                document.getElementById("b1").src="Resources/button1_1.png";
            }
            function outButton1(){
                document.getElementById("b1").src="Resources/button1_2.png";
            }
            function onButton2(){
                document.getElementById("b2").src="Resources/button2_1.png";
            }
            function outButton2(){
                document.getElementById("b2").src="Resources/button2_2.png";
            }
        </script>
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome To SaveDogs.COM</h1>
        <div id="login">
            <%
            String username=(String)session.getAttribute("dxlogin");
            if(username!=null){
              out.println("<form id='logoutform' method='GET' action='/SaveDogsCOM/logout.do'>");  
              out.println("Welcome "+username+"!");
              out.println(" <input type='button' value='View my records' onclick='jump(\"MyAccount\")' />");
              out.println("<input type='submit' name='submit' value='Logout'>");
              out.println("</form>");   
            }else{
              out.println("<form id='loginform' name='bookForm' method='GET' action='/SaveDogsCOM/login.do'>");  
              out.println("Username:<input type='text' name='username' value=''>");
              out.println("Password:<input type='password' name='password' value=''><input type='submit' name='submit' value='Login'>");
              out.println(" <input type='button' value='Register a new user' onclick='jump(\"Register\")' />");
              out.println("</form>");
              String warn=(String)session.getAttribute("warning");
              if(warn!=null){
                      out.println("<script language='JavaScript'>alert('"+warn+"')</script>");
                      request.getSession().setAttribute("warning",null);
               }                                      
            }%>
        </div>
        <div id="mainBlock">
        <div id="ChoiceSelection">
            <H2>What can you DO here?</H2>
            <img id="pl1" src="Resources\List1_1.PNG" width="256" height="62" onmouseover="chooseList1()"/>
            <img id="pl2" src="Resources\List2_2.PNG" width="256" height="62"onmouseover="chooseList2()"/>
        </div>
        <div id="s1">
        <div id="mainpanel">
            <div id="pic">
                <img id="p1" src="Resources\CoolDog.jpg" width="456" height="600"/>
                <div id="text">
                <H3>This dog's name is John, he is 6 years old. He got serious disease last summer. Now thanks to this web site. <br/>He can eventually run again </H3>
                </div>
            </div>
            <div id="operation">
                <div id="optitle">
                <p>Remember</p>
                </div>
                <div id="optext">
                    <p>Every cent you donate in this web site may change a helpless dog's life!<br/>
                    Now you can get start by clicking </p>
                    <a href="./SaveDog.jsp">
                        <img id="b1" src="Resources\button1_2.png" onmouseover="onButton1()" onmouseout="outButton1()"/>
                    </a>
                </div>
            </div>
        </div>
        </div>
        <div id="s2">
            <div id="mainpanel2">
                <div id="operation">
                <div id="optitle">
                <p>Remember</p>
                </div>
                <div id="optext">
                    <p>Want to do more for a helpless dog?<br/>
                    Why not register it in this web site by clicking </p>
                    <a href="./BookView.jsp">
                        <img id="b2" src="Resources\button2_2.png" onmouseover="onButton2()" onmouseout="outButton2()"/>
                    </a>
                </div>
                </div>
            <div id="pic">
                <img id="p1" src="Resources\sadDog.png" width="456" height="576"/>
                <div id="text">
                <H3>This dog's name is Sam, he is 2 years old and homeless. <br/>Now thanks to this web site. <br/>He is getting more and more public attention. </H3>
                </div>
            </div>
            </div>
        </div>
        </div>
    </body>
</html>
