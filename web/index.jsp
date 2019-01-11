<%-- 
    Document   : index
    Created on : Oct 2, 2018, 12:28:55 PM
    Author     : Andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Departamento de Deportes</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript">
            function cbCualidadesOnChange(value) {
                document.getElementById("cbdiscapacidad").disabled = !(value == "1");
                document.getElementById("cbdiscapacidad").required = (value == "1");
                if (value != "1") {
                    document.getElementById("cbdiscapacidad").selectedIndex = 0;
                }
            }
        </script>
    </head>
    <body>
        <div id="content">
            <jsp:include page="header.jsp" />
            <div id="main-container">
                <p><h1>Asignaci&oacute;n de Disciplinas para Deportes</h1></p>
            
                <form method="POST" action="inscribir" id="inscribirform">
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
