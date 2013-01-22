<%-- 
    Document   : index
    Created on : Dec 14, 2012, 11:51:13 AM
    Author     : TempRDP2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
  
  <head>
    <title>JSP Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
        <link href="CSS/bootstrap.css" rel="stylesheet">
        <link href="CSS/bootstrap-responsive.css" rel="stylesheet">
        <script src="JS/jquery.js"></script>
        <script src="JS/bootstrap.js"></script>
    <script>
        function loadInnerContent() {
            $("#headerofpage").load("headerofpage.html");
            $("#forInformers").load("informers.html");
            $.ajax({ //checking if client is logged in, if yes - redirect to contentcl.html
                url: 'CheckClientLoggedInServlet',
                success : function(text){
                    responsestate = text.substring(0,7);
                    if (responsestate == "success") {
                        $(location).attr("href","contentcl.html");
                    }
                }
            });  
            $.ajax({
                url: 'CheckUserLoggedInServlet',
                success : function(text){
                    responsestate = text.substring(0,7);
                    if (responsestate == "success") {
                        $(location).attr("href","contentus.html");
                    }
                }
            });              
            window.setTimeout("setActive()", 500);
        }
        function setActive() {
            $("#homemenu").addClass("active");
        }
        function loginClient() {
            var data;
            if ($("#loginButton").attr("disabled") == "disabled") return false;
            $("#loginButton").attr("disabled", "disabled");
            var login = document.getElementById("inputClientLogin").value;
            var password = document.getElementById("inputClientPassword").value;
            $.ajax({
                url: 'ClientLoginServlet',
                data: { param1: login, param2: password },
                error: function() {
                    $("#replyField").innerHtml = "Failed!";
                    $("#loginButton").removeAttr("disabled");
                },
                success : function(text){
                    response = text;
                    $("#loginButton").removeAttr("disabled");
                    parseAnswer(response) }
            });
        }
        function parseAnswer(data) {
            if (data == "success") {
                $(location).attr("href","contentcl.html");
            }
            if (data == "failure") {
                document.getElementById("replyField").innerHTML = "<span class=\"alert alert-error\">Wrong login/password!</span>";
            }
            }
        function loginUser() {
            if ($("#loginUserButton").attr("disabled") == "disabled") return false;
            $("#loginUserButton").attr("disabled", "disabled");
            var login = document.getElementById("inputUserLogin").value;
            var password = document.getElementById("inputUserPassword").value;
            $.ajax ({
                url: "UserLoginServlet",
                data: { param1: login, param2: password },
                error: function() {
                    $("#replyUserField").innerHtml = "Failed!";
                    $("#loginUserButton").removeAttr("disabled");
                },
                success: function(text) { response = text; parseUserAnswer(response) }
            });
        }
        function parseUserAnswer(data) {
            if (data == "success") {
                $(location).attr("href","contentus.html");
            }
            if (data == "failure") {
                $("#loginUserButton").removeAttr("disabled");                
                document.getElementById("replyUserField").innerHTML = "<span class=\"alert alert-error\">Wrong login/password!</span>";
            }
        }
</script>

  </head>
  
    <body onload="loadInnerContent()">
    <div id="headerofpage">
    </div>

        
    <div class="row-fluid">
        <div class="span3" id="leftColumn">
            <div id="ClientLogin">
                <fieldset id="ClientLoginSet">
                    <legend>Login for clients of Demo CRM</legend>
                    <label>Input login name</label>
                    <input class="input" type="text" id="inputClientLogin" placeholder="Type login">
                    <label>Input password</label>
                    <input class="input" type="password" id="inputClientPassword" placeholder="Type password">
                    <label> </label>
                    <button id="loginButton" type="button" onclick="loginClient()" class="btn btn-primary">Log in</button>
                    <label> </label>
                    <div id="replyField"></div>
                </fieldset>
            </div>

        </div>
        <div class="span6" id="centerColumn">
        <div id="abouttext">
            <h2> About this project </h2>

            <p> This is a demo of simple CRM made to qualify knowledge of software development, implementation of modern front-end and back-end technologies and web-design.</p>
            <p> Back-end of this project uses JavaEE with servlets, MySql and hibernate and some additional stuff.</p>
            <p> Front-end uses next web-technologies:</p>
            <ul>
                <li> HTML </li>
                <li> CSS framework Twitter Bootstrap </li>
                <li> Javascript </li>
                <li> JQuery </li>
                <li> AJAX </li>
            </ul>
            <p>For testing purposes You can use the following accounts:</p>
            <p>Clients account:</p>
            <ul>
                <li> Login: login1 </li>
                <li> Password: password1 </li>
            </ul>
            User account with empty data:
            <ul>
                <li> Login: login1 </li>
                <li> Password: password1 </li>
            </ul>
            User account with filled data:
            <ul>
                <li> Login: login2 </li>
                <li> Password: password2 </li>
            </ul>
            <p>   This project was done basing on course by Nikolay Tkachenko and his training center <a href="http://javanec.kiev.ua">Javanec.kiev.ua</a> that took place on November - December, 2012.
        </div>
        </div>
        <div class="span3" id="rightColumn">
            <div class="accordion" id="userLoginAccordion">
              <div class="accordion-group">
                <div class="accordion-heading">
                  <a class="accordion-toggle" data-toggle="collapse" data-parent="#userLoginAccordion" href="#collapseOne">
                    Company member login:
                  </a>
                </div>
                <div id="collapseOne" class="accordion-body collapse">
                  <div class="accordion-inner">
                    <div id="UserLogin">
                        <fieldset id="UserLoginSet">
                            <legend>Login for staff only</legend>
                            <label>Input login name</label>
                            <input class="input" type="text" id="inputUserLogin" placeholder="Type login">
                            <label>Input password</label>
                            <input class="input" type="password" id="inputUserPassword" placeholder="Type password">
                            <label> </label>
                            <button id="loginUserButton" type="button" onclick="loginUser()" class="btn">Log in</button>
                            <label> </label>
                            <div id="replyUserField"></div>
                        </fieldset>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div id="forInformers"></div>
        </div>
    </div>
    </body>

</html>