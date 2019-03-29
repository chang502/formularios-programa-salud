<%-- 
    Document   : inscripcion
    Created on : Jan 11, 2019, 11:21:56 AM
    Author     : Andres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignación de disciplina</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../vendor/noui/nouislider.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="../css/util.css">
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="container-contact100">
            <div class="wrap-contact100"><div class="wrap-contact100-header">
                    <div id="logo"><p>Departamento de Deportes</p></div>
                </div>
                <form class="contact100-form validate-form" action="../inscribir" method="POST">
                    <span class="contact100-form-title">
                        Asignación de Disciplina
                    </span>
                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100 input100-select">
                        <span class="label-input100">Tipo documento *</span>
                        <div>
                            <select class="js-selectdisc" placeholder="Seleccione una opci&oacute;n" required name="tipo_documento">
                                <option disabled selected value style="display:none">Seleccione</option>
                                <option value="carnet">Carnet Estudiantil</option>
                                <option value="nov">Número de Orientación Vocacional</option>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>
                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate="Ingrese la numeración del documento">
                        <span class="label-input100">Número de Documento *</span>
                        <input class="input100" type="number" min="5000000" max="2999999999" name="documento" placeholder="Número de Orientación Vocacional">
                    </div>





                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese su nombre">
                        <span class="label-input100">Nombres *</span>
                        <input class="input100" type="text" name="nombre" placeholder="Nombres">
                    </div>

                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese su apellido">
                        <span class="label-input100">Apellidos *</span>
                        <input class="input100" type="text" name="apellido" placeholder="Apellido">
                    </div>






                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100 input100-select">
                        <span class="label-input100">Carrera *</span>
                        <div>
                            <select class="js-select2" placeholder="Seleccione una opci&oacute;n" required name="carrera">
                                <option disabled selected value style="display:none">Seleccione</option>
                                <%= new utils.DBManager().getCarreras()%>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>







                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese una fecha válida">
                        <span class="label-input100">Fecha Nacimiento *</span>
                        <input class="input100" type="date" name="fecha_nacimiento" placeholder="dd/mm/yyyy">
                    </div>

                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100 input100-select">
                        <span class="label-input100">Sexo *</span>
                        <div>
                            <select class="js-select2" placeholder="Seleccione una opci&oacute;n" required name="sexo">
                                <option disabled selected value style="display:none">Seleccione</option>
                                <option value="F">Femenino</option>
                                <option value="M">Masculino</option>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>




                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate="Ingrese un numero de teléfono">
                        <span class="label-input100">Teléfono</span>
                        <input class="input100" type="number" min="10000000" max="99999999" name="telefono" placeholder="Teléfono">
                    </div>





                    <div class="wrap-input100 validate-input bg1 wrap-input100" data-validate = "Ingrese su correo electrónico (ejemplo@dominio.com)">
                        <span class="label-input100">Correo Electrónico *</span>
                        <input class="input100" type="text" name="email" placeholder="Ingrese su correo electrónico ">
                    </div>












                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate="Ingrese un numero de teléfono">
                        <span class="label-input100">Teléfono de emergencia</span>
                        <input class="input100" type="number" min="10000000" max="99999999" name="telefono_emergencia" placeholder="Teléfono de emergencia">
                    </div>


                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese un nombre">
                        <span class="label-input100">Contacto de emergencia *</span>
                        <input class="input100" type="text" name="contacto_emergencia" placeholder="Nombre del contacto">
                    </div>




                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese el peso en libras">
                        <span class="label-input100">Peso *</span>
                        <input class="input100" type="number" min="1" max="1000" name="peso" placeholder="Peso (lbs)">
                    </div>

                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100" data-validate = "Ingrese la estatura en metros">
                        <span class="label-input100">Estatura *</span>
                        <input class="input100" type="number" step="0.01" min="0.01" max="4" name="estatura" placeholder="Estatura (mts)">
                    </div>


                    <div class="wrap-input100 validate-input bg1 rs1-wrap-input100 input100-select">
                        <span class="label-input100">&iquest;Posee capacidades especiales o alguna enfermedad que lo obligue a utilizar silla de ruedas, muletas, bastón, ayuda auditiva, medicamentos u otro equipo especial&quest; *</span>
                        <div>
                            <select class="js-selectdisc" placeholder="Seleccione una opci&oacute;n" required name="cualidades_especiales">
                                <option disabled selected value style="display:none">Seleccione</option>
                                <option value="1">S&iacute;</option>
                                <option value="0">No</option>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>




                    <div class="dis-none rs1-wrap-input100 bg1 wrap-input100 js-show-service">
                        <div class=" validate-input bg1 input100-select">
                            <span class="label-input100">Tipo de Cualidad Especial</span>
                            <div>
                                <select class="js-select2" id="selectDisciplina" placeholder="Seleccione una opci&oacute;n" name="id_tipo_discapacidad">
                                    <option selected disabled hidden>Seleccione</option>
                                    <%= new utils.DBManager().getTiposDiscapacidad()%>
                                </select>
                                <div class="dropDownSelect2"></div>
                            </div>
                        </div>
                    </div>
                                
                                


                    <div class="wrap-input100 validate-input bg1 input100-select">
                        <span class="label-input100">¿Pertenece a alguna federación o asociación deportiva? *</span>
                        <div>
                            <select class="js-select2" placeholder="Seleccione una opci&oacute;n" required name="id_disciplina_persona">
                                
                                <%= new utils.DBManager().getTiposDisciplina()%>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>
                                
                                


                    <div class="wrap-input100 validate-input bg1 input100-select">
                        <span class="label-input100">¿Padece de alguna enfermedad crónica? *</span>
                        <div>
                            <select class="js-select2" placeholder="Seleccione una opci&oacute;n" required name="id_tipo_enfermedad">
                                
                                <%= new utils.DBManager().getTiposEnfermedad()%>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>


                    <div class="wrap-input100 validate-input bg1 input100-select">
                        <span class="label-input100">Disciplina *</span>
                        <div>
                            <select class="js-select2" placeholder="Seleccione una opci&oacute;n" required name="id_disciplina">
                                <option disabled selected value style="display:none">Seleccione</option>
                                <%= new utils.DBManager().getDisciplinas()%>
                            </select>
                            <div class="dropDownSelect2"></div>
                        </div>
                    </div>

                    <div class="container-contact100-form-btn">
                        <button class="contact100-form-btn">
                            <span>
                                Asignar
                                <i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
                            </span>
                        </button>
                    </div>
                </form>
            </div>
        </div>


        <!--===============================================================================================-->
        <script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="../vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="../vendor/bootstrap/js/popper.js"></script>
        <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="../vendor/select2/select2.min.js"></script>
        <script>
            $(".js-select2").each(function () {
                $(this).select2({
                    minimumResultsForSearch: 20,
                    dropdownParent: $(this).next('.dropDownSelect2')
                });
            })


            $(".js-selectdisc").each(function () {
                $(this).select2({
                    minimumResultsForSearch: 20,
                    dropdownParent: $(this).next('.dropDownSelect2')
                });


                $(".js-selectdisc").each(function () {
                    $(this).on('select2:close', function (e) {
                        if ($(this).val() != 1) {
                            $('.js-show-service').slideUp();
                            $("[name='tipo_cualidad']").prop("required", false);
                            $("[name='tipo_cualidad']").attr("required", false);
                            $("[name='tipo_cualidad']").prop("selected", false);
                            $("[name='tipo_cualidad']").val("Seleccione").trigger('change');
                        } else {
                            $('.js-show-service').slideUp();
                            $('.js-show-service').slideDown();
                            $("[name='tipo_cualidad']").prop("required", true);
                            $("[name='tipo_cualidad']").attr("required", true);
                            $("[name='tipo_cualidad']").prop("selected", false);
                            $("[name='tipo_cualidad']").val("Seleccione").trigger('change');
                        }
                    });
                });
            })
        </script>
        <!--===============================================================================================-->
        <script src="../vendor/daterangepicker/moment.min.js"></script>
        <script src="../vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="../vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->

        <script src="../js/main.js"></script>
    </body>
</html>
