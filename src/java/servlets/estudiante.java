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
@WebServlet(name = "estudiante", urlPatterns = {"/estudiante"})
public class estudiante extends HttpServlet {

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
            out.println("<title>Servlet estudiante</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet estudiante at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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

        String carnet = request.getParameter("carnet");
        String cui = request.getParameter("cui");
        String carrera = request.getParameter("carrera");
        String fecha_nacimiento = request.getParameter("fecha_nacimiento");
       
        String telefono = request.getParameter("telefono");
        String telefono_emergencia = request.getParameter("telefono_emergencia");
        String contacto_emergencia = request.getParameter("contacto_emergencia");
        String peso = request.getParameter("peso");
        String estatura = request.getParameter("estatura");
        String cualidades_especiales = request.getParameter("cualidades_especiales");
        String id_tipo_discapacidad = request.getParameter("id_tipo_discapacidad");
        String id_tipo_enfermedad = request.getParameter("id_tipo_enfermedad");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            utils.DBManager dbm = new utils.DBManager();

            int id_persona = dbm.actualizarDatosEstudiante(carnet, cui, carrera, fecha_nacimiento, 
                    telefono, telefono_emergencia, contacto_emergencia, peso, estatura,
                    cualidades_especiales, id_tipo_discapacidad, id_tipo_enfermedad);

            if (id_persona > 0) {
                out.println("<h1>Ingreso Satisfactorio</h1>");
                response.sendRedirect("success.jsp?id=" + id_persona);
            } else {
                request.setAttribute("title", "Ingreso Erróneo");
                request.setAttribute("message", dbm.mensaje);
                request.getRequestDispatcher("_r3sult___.jsp").include(request, response);
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
    }

}
