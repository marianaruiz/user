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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.un.Usuario;
import paquete.un.UsuarioDAO;

/**
 *
 * @author Mariana
 */
public class Actualizaru extends HttpServlet {

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
        String pagina = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario u =new Usuario();
            String tipo=request.getParameter("id");
            u.setIdusuario(Integer.parseInt(tipo));
            UsuarioDAO udao=new UsuarioDAO();
            
            u=udao.load(u);
            
            pagina +="<html>";
            pagina +="<head>";
            pagina +="<title>actualizar</title>";            
            pagina +="</head>";
            pagina +="<body>";
            pagina+="<form action=\"Actualizar\" method=\"get\" >" + " <fieldset>"; 
            pagina+="<legend>Datos Usuario</legend>";
            pagina+="id <input type=\"hidden\" name=\"id\""+"value=\""+u.getIdusuario()+"\""+">";
            pagina+="<br/>";
            pagina+="Nombre <br/>";
            
            pagina+= " <input type=\"text\" name=\"nombre\""+"value=\""+u.getNombre()+"\""+"/>" +"<br/>";
            pagina+="Apallido Paterno <br/>\n" + "<input type=\"text\" name=\"paterno\""+"value=\""+u.getPaterno()+"\""+"/>" + "<br/>";
            pagina+="Apellido Materno <br/>\n" +"<input type=\"text\" name=\"materno\""+"value=\""+u.getMaterno()+"\""+"/>" + "<br/>";
            pagina+="Correo Electronico <br/>\n" +"<input type=\"text\" name=\"email\""+"value=\""+u.getEmail()+"\""+"/>" + "<br/>\n";
            pagina+="Tipo de usuario<br/>";
            if("A".equals(u.getTipo())){
            pagina+="<input type=\"radio\" name=\"tipo\" value=\"A\"checked>Administrador<br>";
            pagina+="<input type=\"radio\" name=\"tipo\" value=\"U\">Usuario<br>\n";
            }
            else{
            pagina+="<input type=\"radio\" name=\"tipo\" value=\"A\">Administrador<br>\n";
            pagina+="<input type=\"radio\" name=\"tipo\" value=\"U\" checked>Usuario<br>\n";
            }
            
            pagina+="Nombre de Usuario<br/>" +
                    "<input  type=\"text\" name=\"nombreUsuario\""+"value=\""+
                    u.getNombreUsuario()+"\""
                    +"/>" +
                    "<br/>" +
                    "Contraseña <br/>\n" +
                    "<input  type=\"password\" name=\"clavedeusuario\""+"value=\""+u.getClave()+"\""+"/>" 
                    +
                    "</fieldset>\n" +
                    "<input type=\"submit\" value=\"Actualizar\">\n" +
                    "</form>";
            
            pagina +="</body>";
            pagina +="</html>";
            
            out.println(pagina);
            
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Actualizar</title>");            
            out.println("</head>");
            out.println("<body>");
            value="unusario"
            out.println("<h1>Servlet Actualizar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
            Logger.getLogger(Actualizaru.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Actualizaru.class.getName()).log(Level.SEVERE, null, ex);
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
