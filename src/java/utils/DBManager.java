/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;

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
        return getOptionsForSelect("get_student_document_types","id_tipo_documento","nombre");
    }

    public String getTiposDiscapacidad() {
        return getOptionsForSelect("get_disability_types","id_tipo_discapacidad","nombre");
    }

    public String getDisciplinas() {
        return getOptionsForSelect("get_active_disciplines","id_disciplina","nombre");
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

    public int inscribir(String tipo_documento, String numero_documento, String correo, String peso, String estatura, String cualidades, String tipo_discapacidad, String disciplina) {

        try {

            java.util.Map<String, String> params = new java.util.HashMap<>();
            params.put("id_tipo_documento", tipo_documento);
            params.put("numero_documento", numero_documento);
            params.put("email", correo);
            params.put("peso", peso);
            params.put("estatura", estatura);
            params.put("cualidades_especiales", cualidades);
            params.put("id_tipo_discapacidad", tipo_discapacidad);
            params.put("id_disciplina", disciplina);

            String fields[] = {"id_tipo_documento", "numero_documento", "email", "peso", "estatura", "cualidades_especiales", "id_tipo_discapacidad", "id_disciplina"};

            java.sql.CallableStatement result = callResultProcedure("assign_discipline", params, fields);

            int id_estudiante_deportes = result.getInt(fields.length + 1);
            String mensaje = result.getString(fields.length + 2);

            if (id_estudiante_deportes > 0) {

            }

            return id_estudiante_deportes;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return -1;
        }

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
