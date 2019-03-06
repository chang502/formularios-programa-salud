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
        
        String cui = request.getParameter("cui");
        String nov = request.getParameter("nov");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fecha_nacimiento = request.getParameter("fecha_nacimiento");
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String telefono_emergencia = request.getParameter("telefono_emergencia");
        String contacto_emergencia = request.getParameter("contacto_emergencia");
        String carrera = request.getParameter("carrera");
        String peso = request.getParameter("peso");
        String estatura = request.getParameter("estatura");
        String cualidades_especiales = request.getParameter("cualidades_especiales");
        String id_tipo_discapacidad = request.getParameter("id_tipo_discapacidad");
        String id_disciplina = request.getParameter("id_disciplina");
        
        


        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            utils.DBManager dbm = new utils.DBManager();

            int id_estudiante_deportes = dbm.inscribir( cui,nov,nombre,apellido,fecha_nacimiento,sexo,
                    email,telefono,telefono_emergencia,contacto_emergencia,carrera,peso,estatura,
                    cualidades_especiales,id_tipo_discapacidad,id_disciplina);
            
            
            
            
            
            if (id_estudiante_deportes>0) {
                out.println("<h1>Inscripción Satisfactoria</h1>");
                response.sendRedirect("success.jsp?id="+id_estudiante_deportes);
            } else {
                out.println("<h1>Inscripción Erronea</h1>");
                out.println("<p>"+dbm.mensaje+"</p>");
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
