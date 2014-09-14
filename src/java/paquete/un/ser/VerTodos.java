/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete.un.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class VerTodos extends HttpServlet {

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
        Usuario u=new Usuario();
        UsuarioDAO udao=new UsuarioDAO();
        List lista;
        String nombre,paterno,materno,email,nombreUser,claveUser,tipoUser,enlace,enlace2;
        int id;
        String pagina;
        List ls;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            pagina="<!DOCTYPE html>";
            pagina +="<html>";
            pagina +="<head>";
            pagina +="<title>userlist</title>";            
            pagina +="</head>";
            pagina +="<body>";
            pagina +="<h1>Lista de usuarios" + request.getContextPath() + "</h1>";
            pagina +="<table>";
            pagina+="<tr>";
            pagina+="<th>IdUsuario</th>";
            pagina+="<th>Nombre</th>";
            pagina+="<th>Apellido Paterno</th>";
            pagina+="<th>Apellido Materno</th>";
            pagina+="<th>email</th>";
            pagina+="<th>Nombre de Usuario</th>";
            pagina+="<th>Contraseña</th>";
            pagina+="<th>Tipo</th>";
            pagina+="</tr>";
            ls = udao.load_all();
            for(int i = 0; i<ls.size();i++){
                Usuario lu= (Usuario)(ls.get(i));
                
                id = lu.getIdusuario();
                System.out.println(id+" "+ls.size());
                nombre = lu.getNombre();
                paterno =lu.getPaterno();
                materno = lu.getMaterno();
                email = lu.getEmail();
                nombreUser = lu.getNombreUsuario();
                claveUser = lu.getClave();
                tipoUser = lu.getTipo();
               
                enlace = "<a href='EliminarUsuario?id="+id+"'>"+"Borrar"+"</a>";
                enlace2 = "<a href='Actualizaru?id="+id+"'>"+"Modificar"+"</a>";
                //pagina+="<a href=\"#\" onmouseDown=\"alert(\'Se Elimino correctamente al usuario\')\">su texto aquí</a>";
                
                pagina += "<tr>";
                pagina += "<td>" +id+"</td>";
                
                //System.err.println(id);
                pagina += "<td>" +nombre+"</td>";
                pagina += "<td>" +paterno+"</td>";
                pagina += "<td>" +materno+"</td>";
                pagina += "<td>" +email+"</td>";
                pagina += "<td>" +nombreUser+"</td>";
                pagina += "<td>" +claveUser+"</td>";
                pagina += "<td>" +tipoUser+"</td>";
                pagina += "<td>" +enlace+"</td>";
                pagina +="<td>" +enlace2+"</td>";
               pagina += "</tr>";
            }
            pagina +="<table>";
            pagina+="<FORM METHOD=\"LINK\" ACTION=\"newUsuario.html\"> <INPUT TYPE=\"submit\" VALUE=\"Agregar Usuario\"> </FORM>";
            pagina+="<a href=\"grafica_ser\">Ver Grafica</a>";
            pagina +="</body>";
            pagina +="</html>";
            
            out.println(pagina);
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
            Logger.getLogger(VerTodos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerTodos.class.getName()).log(Level.SEVERE, null, ex);
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
