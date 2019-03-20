<%-- 
    Document   : _r3sult___
    Created on : Mar 15, 2019, 1:34:52 AM
    Author     : Andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Programa de Salud FIUSAC - <%= request.getAttribute("title") %></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/noui/nouislider.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
    </head>
    <body>
        
            <div class="container-contact100">
		<div class="wrap-contact100" style="height: 100vh;"><div class="wrap-contact100-header">
                        <div id="logo"><p>Programa de Salud FIUSAC</p></div>
                    </div>
			<form class="contact100-form validate-form" action="inscribir" method="POST">

                            <p class="contact100-form-title"><%= request.getAttribute("title") %></p>
                                    <p style="width:100%; text-align: center !important;" class="contact100-form-text"><%= request.getAttribute("message") %></p>

				
			</form>
		</div>
	</div>
    </body>
</html>
