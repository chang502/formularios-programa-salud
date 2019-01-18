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

    public String getDisciplinas() {

        try {

            ResultSet rs = getResultSet("SELECT d.id_disciplina, d.nombre, count(ed.id_disciplina), d.limite, d.semestre FROM disciplina d\n"
                    + "left join estudiante_deportes ed on d.id_disciplina = ed.id_disciplina\n"
                    + "WHERE d.activo and coalesce(ed.activo,true) and  d.semestre=CONCAT(IF(MONTH(NOW())<7,1,2),'S',YEAR(NOW()))\n"
                    + "group by d.id_disciplina, d.nombre, d.limite, d.semestre having count(ed.id_disciplina)<d.limite\n"
                    + "ORDER BY d.nombre;");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("<option value=\"");
                sb.append(rs.getInt("id_disciplina")).append("\">");
                sb.append(rs.getString("nombre")).append("</option>\n");
            }

            return sb.toString();
        } catch (Exception e) {
            return "";
        }

    }

    public String getTiposDocumento() {
        try {

            ResultSet rs = getResultSet("SELECT id_tipo_documento, nombre FROM tipo_documento WHERE activo ORDER BY nombre");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("<option value=\"");
                sb.append(rs.getInt("id_tipo_documento")).append("\">");
                sb.append(rs.getString("nombre")).append("</option>\n");
            }

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTiposDiscapacidad() {
        try {

            ResultSet rs = getResultSet("SELECT t.id_tipo_discapacidad, t.nombre FROM tipo_discapacidad t WHERE t.activo ORDER BY 1");

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                sb.append("<option value=\"");
                sb.append(rs.getInt("id_tipo_discapacidad")).append("\">");
                sb.append(rs.getString("nombre")).append("</option>\n");
            }

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* public boolean inscribir(String query){

        try{
        PreparedStatement ps=conn.prepareStatement(query);

        int resp=ps.executeUpdate();
        return resp>0;
        } catch(Exception e){
            e.printStackTrace(System.out);
            return false;
        }

        String tipo_documento = request.getParameter("tipo_documento");
        String numero_documento = request.getParameter("numero_documento");
        String correo = request.getParameter("correo");
        String peso = request.getParameter("peso");
        String estatura = request.getParameter("estatura");
        String cualidades = request.getParameter("cualidades");
        String tipo_discapacidad = request.getParameter("tipo_cualidad");
        String disciplina = request.getParameter("disciplina");
    
    }*/
    public int inscribir(String tipo_documento, String numero_documento, String correo, String peso, String estatura, String cualidades, String tipo_discapacidad, String disciplina) {

        try {
            
            java.util.Map<String, String> params = new java.util.HashMap<>();
            params.put("id_tipo_documento",tipo_documento);
            params.put("numero_documento",numero_documento);
            params.put("email",correo);
            params.put("peso",peso);
            params.put("estatura",estatura);
            params.put("cualidades_especiales",cualidades);
            params.put("id_tipo_discapacidad",tipo_discapacidad);
            params.put("id_disciplina",disciplina);
            
            String fields[] = {"id_tipo_documento", "numero_documento", "email", "peso", "estatura", "cualidades_especiales", "id_tipo_discapacidad", "id_disciplina"};
            
            
            java.sql.CallableStatement result = callResultProcedure("assign_discipline", params, fields);
            
            
            int id_estudiante_deportes=result.getInt(fields.length + 1);
            String mensaje=result.getString(fields.length+2);
            
            if (id_estudiante_deportes > 0) {

            }

            return id_estudiante_deportes;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return -1;
        }

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
