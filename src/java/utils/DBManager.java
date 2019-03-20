/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Andres
 */
public class DBManager {

    static final String DRIVER = "org.mariadb.jdbc.Driver";

    private static final String connectionstring = "jdbc:mariadb://localhost:3306/programasalud";
    private static final String user = "root";
    private static final String password = "progsalud";

    private static Connection conn;

    public int id_asignacion;
    public String mensaje;

    public DBManager() {
        connect();
    }

    private static void connect() {
        if (conn != null) {
            //System.out.println("connection already created");
            return;
        }
        try {
            //System.out.println("Creating the connection");
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(connectionstring, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    public ResultSet getResultSet(String query) throws Exception {

        return conn.createStatement().executeQuery(query);

    }

    public String getTiposDocumento() {
        return getOptionsForSelect("get_student_document_types", "id_tipo_documento", "nombre");
    }
    
    public String getTiposEnfermedad() {
        return getOptionsForSelect("get_disease_types", "id_tipo_enfermedad", "nombre");
    }

    public String getCarreras() {
        //return getOptionsForSelect("get_carreras_for_select","carrera","nombre");
        try {

            ResultSet rs = callGetProcedure("get_carreras_for_select");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("<option value=\"");
                sb.append(rs.getString("carrera")).append("\">");
                sb.append(rs.getString("nombre")).append("</option>\n");
            }

            return sb.toString();
        } catch (Exception e) {
            return "<option value=\"\"></option>\n";
        }
    }

    public String getTiposDiscapacidad() {
        return getOptionsForSelect("get_disability_types", "id_tipo_discapacidad", "nombre");
    }

    public String getDisciplinas() {
        return getOptionsForSelect("get_active_disciplines", "id_disciplina", "nombre");
    }

    private String getOptionsForSelect(String procedure_name, String value, String text) {
        try {

            ResultSet rs = callGetProcedure(procedure_name);

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("<option value=\"");
                sb.append(rs.getInt(value)).append("\">");
                sb.append(rs.getString(text)).append("</option>\n");
            }

            return sb.toString();
        } catch (Exception e) {
            return "<option value=\"\"></option>\n";
        }
    }

    public int inscribir(String cui, String nov, String nombre, String apellido, String fecha_nacimiento, String sexo, String email, String telefono, String telefono_emergencia,
            String contacto_emergencia, String carrera, String peso, String estatura, String cualidades_especiales, String id_tipo_discapacidad, String id_tipo_enfermedad, String id_disciplina) {

        try {

            java.util.Map<String, String> params = new java.util.HashMap<>();
            params.put("cui", cui);
            params.put("nov", nov);
            params.put("nombre", nombre);
            params.put("apellido", apellido);
            params.put("fecha_nacimiento", fecha_nacimiento);
            params.put("sexo", sexo);
            params.put("email", email);
            params.put("telefono", telefono);
            params.put("telefono_emergencia", telefono_emergencia);
            params.put("contacto_emergencia", contacto_emergencia);
            params.put("carrera", carrera);
            params.put("peso", peso);
            params.put("estatura", estatura);
            params.put("flag_tiene_discapacidad", cualidades_especiales);
            params.put("id_tipo_discapacidad", id_tipo_discapacidad);
            params.put("id_tipo_enfermedad", id_tipo_enfermedad);
            params.put("id_disciplina", id_disciplina);

            //System.out.println("carrera: " + params.get("carrera"));

            String fields[] = {"cui", "nov", "nombre", "apellido", "fecha_nacimiento",
                "sexo", "email", "telefono", "telefono_emergencia", "contacto_emergencia",
                "carrera", "peso", "estatura",
                "flag_tiene_discapacidad",
                "id_tipo_discapacidad",
                "id_tipo_enfermedad",
                "id_disciplina"};

            java.sql.CallableStatement result = callResultProcedure("assign_discipline", params, fields);

            int id_estudiante_deportes = result.getInt(fields.length + 1);
            String msj = result.getString(fields.length + 2);

            //System.out.println("o_result: "+id_estudiante_deportes);
            //System.out.println("mensaje asignación: "+mensaje);
            this.mensaje = msj;

            if (id_estudiante_deportes > 0) {

            }

            this.id_asignacion = id_estudiante_deportes;
            return this.id_asignacion;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return -1;
        }

    }

    private String getCcWsResponseMetadata(java.io.InputStream is) {

        try {

            //java.io.InputStreamReader isr=new java.io.InputStreamReader(is,"UTF-8");
            BufferedReader bfreader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            JsonReader reader = Json.createReader(bfreader);
            JsonObject jsonObject = reader.readObject();
            reader.close();

            String metadata = jsonObject.getString("metadata");
            return metadata;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFieldFromCcWsResponseMetadata(String raw, String fieldname) {
        String resp = null;

        try {
            java.io.InputStream is = new java.io.ByteArrayInputStream(raw.getBytes("UTF-8"));
            JsonReader reader = Json.createReader(is);

            JsonArray jsonArray = reader.readArray();
            reader.close();

            if (jsonArray.isEmpty()) {
                return null;
            }
            JsonObject jsonObject = jsonArray.getJsonObject(0);

            if (!jsonObject.isNull(fieldname)) {
                resp = jsonObject.getString(fieldname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int actualizarDatosEstudiante(String carnet, String cui, String carrera,
            String fecha_nacimiento, String telefono, String telefono_emergencia,
            String contacto_emergencia, String peso, String estatura,
            String cualidades_especiales, String id_tipo_discapacidad, String id_tipo_enfermedad) 
    {
        try {

            java.util.Map<String, String> params = new java.util.HashMap<>();

            String fields[] = {"nombre", "apellido", "fechanacimiento", "sexo", "correo", 
                        "cui", "nov", "usuarioid", "carrera", 
                        "telefono", "telefono_emergencia", "contacto_emergencia",
                        "peso", "estatura","flag_tiene_discapacidad","id_tipo_discapacidad", "id_tipo_enfermedad"};

            try {
                HttpURLConnection con = utils.ConexionCentroCalculo.getEstudiante(carnet);

                if (con != null && con.getResponseCode() == 200) {

                    java.io.InputStream is = con.getInputStream();
                    String ws_response = getCcWsResponseMetadata(is);
                    //carrera
                    con = utils.ConexionCentroCalculo.getEstudianteCarrera(carnet);
                    String ws_response_carrera = getCcWsResponseMetadata(con.getInputStream());
                    String id_carrera = getFieldFromCcWsResponseMetadata(ws_response_carrera, "carrera");

                    java.io.InputStream bais = new java.io.ByteArrayInputStream(ws_response.getBytes("UTF-8"));
                    JsonReader reader = Json.createReader(bais);

                    JsonArray jsonArray = reader.readArray();
                    reader.close();

                    if (jsonArray.isEmpty()) {
                        this.mensaje = "No se encontraron registros";
                        return -2;
                    }
                    JsonObject jsonObject = jsonArray.getJsonObject(0);

                    for (int i = 0; i < 9; i++) {
                        String field = fields[i];
                        String tmp = null;
                        try {
                            tmp = jsonObject.getString(field);
                        } catch (Exception ff) {
                        }
                        params.put(field, tmp);
                        //System.out.println(field+" - "+tmp);
                    }

                    params.put("carrera", carrera);

                    String tmp = params.remove("fechanacimiento");
                    params.put("fecha_nacimiento", tmp);

                    tmp = params.remove("correo");
                    params.put("email", tmp);

                    tmp = params.remove("usuarioid");
                    params.put("carnet", tmp);

                    fields[2] = "fecha_nacimiento";
                    fields[4] = "email";
                    fields[7] = "carnet";

                    if (id_carrera.equals(carrera) 
                        && params.get("cui").equals(cui)
                        && params.get("carnet").equals(carnet)
                        && params.get("fecha_nacimiento").equals(fecha_nacimiento)) 
                    {
                        //System.out.println("Información coincide, persona identificada");
                        
                        params.put("telefono", telefono);
                        params.put("telefono_emergencia", telefono_emergencia);
                        params.put("contacto_emergencia", contacto_emergencia);
                        params.put("peso", peso);
                        params.put("estatura", estatura);
                        params.put("flag_tiene_discapacidad", cualidades_especiales);
                        params.put("id_tipo_discapacidad", id_tipo_discapacidad);
                        params.put("id_tipo_enfermedad", id_tipo_enfermedad);

                        
                        java.sql.CallableStatement result = callResultProcedure("registrar_datos_estudiante", params, fields);

                        int id_persona = result.getInt(fields.length + 1);
                        String msj = result.getString(fields.length + 2);

                        //System.out.println("o_result: "+id_estudiante_deportes);
                        //System.out.println("mensaje asignación: "+mensaje);
                        this.mensaje = msj;

                        if (id_persona > 0) {

                        }

                        this.id_asignacion = id_persona;
                        return this.id_asignacion;

                    } else {
                        
                        this.mensaje = "Los datos ingresados no coinciden, revise su número de carnet, CUI, fecha de nacimiento y carrera. Si tiene asignadas varias carreras, seleccione una diferente.";
                    }

                    //System.out.println(ws_response_carrera);
                    //return resp == null ? callSelectStoredProcedure("search_person_by_carnet", map, fields2) : resp;
                } else {
                    this.mensaje = "No es posible buscar los datos de estudiantes en este momento";
                }
            } catch (Exception e) {
                this.mensaje="Ocurrió un error: "+e.getMessage();
                e.printStackTrace(System.err);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return -1;
        }
        return -1;
    }

    private ResultSet callGetProcedure(String procedure_name) throws Exception {

        String query = "{ call " + procedure_name + "() }";

        CallableStatement stmt = conn.prepareCall(query);

        if (stmt.execute()) {
            return stmt.getResultSet();
        }

        return null;

    }

    public CallableStatement callResultProcedure(String procedure, java.util.Map<String, String> params, String fields[]) throws Exception {

        String param_list = "";
        for (int i = 0; i < fields.length; i++) {
            param_list += "?,";
        }

        if (fields.length > 0) {
            param_list = param_list.substring(0, param_list.length() - 1);
        }

        String query = "{ call " + procedure + "(" + param_list + ",?,?) }";//2 parámetros más para el id y el mensaje

        CallableStatement stmt = conn.prepareCall(query);

        for (int i = 0; i < fields.length; i++) {
            stmt.setString(i + 1, params.get(fields[i]));
        }
        stmt.registerOutParameter(fields.length + 1, java.sql.Types.INTEGER);//"o_result"
        stmt.registerOutParameter(fields.length + 2, java.sql.Types.VARCHAR);//"o_mensaje"

        stmt.executeUpdate();

        return stmt;

    }

    public String getResultadoAsignacion(String id_estudiante_deportes) {
        try {

            ResultSet rs = getResultSet("select ed.id_estudiante_deportes, d.nombre, ed.semestre, concat(if(substr(ed.semestre,1,1)='1','Primer','Segundo'), ' Semestre, ',substr(ed.semestre,3,4)) sem from estudiante_deportes ed\n"
                    + "join disciplina d on ed.id_disciplina = d.id_disciplina where ed.id_estudiante_deportes=" + id_estudiante_deportes);
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("Su código de asignación es <b>");
                sb.append(rs.getInt("id_estudiante_deportes")).append("</b><br>");
                sb.append("Se ha asignado la disciplina <b>").append(rs.getString("nombre")).append("</b> en el ").append(rs.getString("sem"));
            }

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
