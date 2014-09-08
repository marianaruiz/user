/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete.un.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.un.Usuario;
import paquete.un.UsuarioDAO;

/**
 *
 * @author Mariana
 */
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class Actualizar extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario u =new Usuario();
            u.setNombre(request.getParameter("nombre"));
            u.setPaterno(request.getParameter("paterno"));
            u.setMaterno(request.getParameter("materno"));
            u.setEmail(request.getParameter("email"));
            u.setNombreUsuario(request.getParameter("nombreUsuario"));
            u.setClave(request.getParameter("clavedeusuario"));
            u.setTipo(request.getParameter("tipo"));
            u.setIdusuario (Integer.valueOf(request.getParameter("id")));
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Actualizar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Actualizar at " + request.getContextPath() + "</h1>");
            out.println("<p>"+u.getIdusuario()+"</p>");
            out.println("<p>"+u.getNombre()+"</p>");
            out.println("<p>"+u.getMaterno()+"</p>");
            out.println("<p>"+u.getPaterno()+"</p>");
            out.println("<p>"+u.getEmail()+"</p>");
            out.println("<p>"+u.getNombreUsuario()+"</p>");
            out.println("<p>"+u.getClave()+"</p>");
            out.println("</body>");
            out.println("</html>");*/
            
            
            System.out.println("si");
            System.out.println(u.getIdusuario());
            UsuarioDAO udao= new UsuarioDAO();
            udao.update(u);
            System.out.println("Se actualizo");
             response.sendRedirect("/user/VerTodos"); 
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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
