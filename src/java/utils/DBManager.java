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

            ResultSet rs = getResultSet("SELECT d.id_disciplina, d.nombre, count(ed.id_disciplina), d.limite, d.semestre FROM disciplina d\n" +
"left join estudiante_deportes ed on d.id_disciplina = ed.id_disciplina\n" +
"WHERE d.activo and coalesce(ed.activo,true) and  d.semestre=CONCAT(IF(MONTH(NOW())<7,1,2),'S',YEAR(NOW()))\n" +
"group by d.id_disciplina, d.nombre, d.limite, d.semestre having count(ed.id_disciplina)<d.limite\n" +
"ORDER BY d.nombre;");

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
    
    

    public boolean inscribir(String query){

        try{
        PreparedStatement ps=conn.prepareStatement(query);

        int resp=ps.executeUpdate();
        return resp>0;
        } catch(Exception e){
            e.printStackTrace(System.out);
            return false;
        }

    }

}
