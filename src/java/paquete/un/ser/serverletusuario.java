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
import javax.mail.MessagingException;
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
@WebServlet(name = "serverletusuario", urlPatterns = {"/serverletusuario"})
public class serverletusuario extends HttpServlet {

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
            throws ServletException, IOException, SQLException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //recuperamos los datos enviados del formulario
            Usuario u =new Usuario();
            u.setNombre(request.getParameter("nombre"));
            u.setPaterno(request.getParameter("paterno"));
            u.setMaterno(request.getParameter("materno"));
            u.setEmail(request.getParameter("email"));
            u.setNombreUsuario(request.getParameter("nombreUsuario"));
            u.setClave(request.getParameter("clavedeusuario"));
            /*String tipo=request.getParameter("U");
            if("U".equals(tipo)){
            u.setTipo(tipo);
            }
            else{
                u.setTipo("A");
            }*/
            u.setTipo(request.getParameter("tipo"));
            
            
            UsuarioDAO udao= new UsuarioDAO();
            udao.create(u);
            //enviar mail ce registro
            //Mail mail = new Mail();
            Mail mail =new Mail();
            String asunto="Registro";
            String texto="Confirmación de Registro-Exitoso";
            //texto+="Datos Personales:<br>";
            //texto+=request.getParameter("nombre")+request.getParameter("paterno")+getInitParameter("materno");
            
            /*texto+="Nombre de usuario:";
            texto+=request.getParameter("nombreUsuario");
            texto+="";
            texto+="Contraseña:";
            texto+=request.getParameter("clavedeusuario");*/
            //mail.enviarMail(request.getParameter("email"), asunto, texto);
            mail.enviarMail(request.getParameter("email"), asunto, texto);
                    
             response.sendRedirect("/user/index.html");
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
            Logger.getLogger(serverletusuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(serverletusuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(serverletusuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(serverletusuario.class.getName()).log(Level.SEVERE, null, ex);
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
