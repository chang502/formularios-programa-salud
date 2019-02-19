/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andres
 */
@WebServlet(name = "inscribir", urlPatterns = {"/inscribir"})
public class inscribir extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet inscribir</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet inscribir at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.sendRedirect("index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo_documento = request.getParameter("tipo_documento");
        String numero_documento = request.getParameter("numero_documento");
        String correo = request.getParameter("correo");
        String peso = request.getParameter("peso");
        String estatura = request.getParameter("estatura");
        String cualidades = request.getParameter("cualidades");
        String tipo_discapacidad = request.getParameter("tipo_cualidad");
        String disciplina = request.getParameter("disciplina");

        String query = "INSERT INTO estudiante_deportes (semestre, id_tipo_documento,numero_documento,email,peso,estatura,cualidades_especiales,id_tipo_discapacidad,id_disciplina,activo) VALUES (CONCAT(IF(MONTH(NOW())<7,1,2),'S',YEAR(NOW())),"
                + tipo_documento + ",'" + numero_documento + "','" + correo.toLowerCase() + "'," + peso + "," + estatura + "," + (cualidades.equals("1") ? "true" : "false")+"," +(cualidades.equals("1")? tipo_discapacidad : "null")+ "," + disciplina + ",true);";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            utils.DBManager dbm = new utils.DBManager();

            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado de Inscripción</title>");
            out.println("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body>");*/
            int id_estudiante_deportes = dbm.inscribir( tipo_documento,  numero_documento,  correo,  peso,  estatura,  cualidades,  tipo_discapacidad,  disciplina);
            if (id_estudiante_deportes>0) {
                out.println("<h1>Inscripción Satisfactoria</h1>");
                response.sendRedirect("success.jsp?id="+id_estudiante_deportes);
            } else {
                out.println("<h1>Inscripción Erronea</h1>");
            }
            /*out.println("</body>");
            out.println("</html>");*/
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
