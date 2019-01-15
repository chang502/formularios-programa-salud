<%-- 
    Document   : index
    Created on : Oct 2, 2018, 12:28:55 PM
    Author     : Andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Departamento de Deportes - Inicio</title>
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
        <div id="content">
            <jsp:include page="header.jsp" />
            <div id="main-container">
                <p><h1>Asignaci&oacute;n de Disciplinas para Deportes</h1></p>
            
                <form method="GET" action="inscribir" id="inscribirform">
                    <table>
                        <tr>
                            <td><p id="tipodoc">Tipo de documento</p></td>
                            <td>
                                <select id="tipo_documento" placeholder="Seleccione una opci&oacute;n" required="required" name="tipo_documento">
                                    <option> </option>
                                    <%= new utils.DBManager().getTiposDocumento()%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Numeraci&oacute;n</p></td>
                            <td><input class="inputs" type="number" min="1" max="999999999999999999" required name="numero_documento"></td>
                        </tr>
                        <tr>
                            <td><p>Correo electr&oacute;nico</p></td>
                            <td><input class="inputs" type="email" required name="correo"></td>
                        </tr>
                        <tr>
                            <td><p>Peso (lbs)</p></td>
                            <td><input class="inputs" type="number" min="1" max="1000" required name="peso"></td>
                        </tr>
                        <tr>
                            <td><p>Estatura (mts)</p></td>
                            <td><input class="inputs" type="number" step="0.01" min="0.01" max="4" required name="estatura"></td>
                        </tr>
                        <tr>
                            <td>
                                <div id="cualidades"><p>&iquest;Posee capacidades especiales o alguna enfermedad que lo obligue a utilizar silla de ruedas, muletas, bastón, ayuda auditiva, medicamentos u otro equipo especial&quest; </p></div>
                            </td>
                            <td>
                                <select id="cbcualidades" placeholder="Seleccione" required name="cualidades" onchange="cbCualidadesOnChange(this.value)">
                                    <option></option>
                                    <option value="1">S&iacute;</option>
                                    <option value="0">No</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Indique</p></td>
                            <td>
                                <select id="cbdiscapacidad" placeholder="Seleccione una opci&oacute;n" required name="tipo_cualidad" disabled>
                                    <option> </option>
                                    <%= new utils.DBManager().getTiposDiscapacidad()%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Disciplina</p></td>
                            <td>
                                <select id="disciplina" placeholder="Seleccione una opci&oacute;n" required name="disciplina">
                                    <option> </option>
                                    <%= new utils.DBManager().getDisciplinas()%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Guardar"></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>

    </body>
</html>
