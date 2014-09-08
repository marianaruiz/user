/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Mariana
 */
public  class UsuarioDAO {
    private String url = "jdbc:mysql://localhost:3306/Usuarios";
    private String driver = "com.mysql.jdbc.Driver";
    private String usuario = "root";
    private String password = "root";
    private Connection conexionDB = null;
    private  static final String SQL_INSERT="insert into User (nombre,paterno,materno,email,"
            + "nombreUsuario,claveUsuario,tipoUsuario)" + "values(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="update User set nombre=?,paterno=?,materno=?,email=?,"
            + "nombreUsuario=?,claveUsuario=?,tipoUsuario=?"
            +"where idUsuario=?";
    private static final String SQL_DELETE="delete from User where idUsuario=?";
    private static final String SQL_SELECT="select * from User where idUsuario=?";  
    private static final String SQL_SELECT_ALL="select *from User";
    private static final String SQL_log="select * from User where nombreUsuario=? and claveUsuario=?";
    
    public UsuarioDAO() throws SQLException{
        this.conexionDB = conectar(this.url, this.driver, this.usuario, this.password);
    }
    
    public void create (Usuario u) throws SQLException{
        PreparedStatement ps=null;
        try {
            ps=conexionDB.prepareStatement(SQL_INSERT);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getPaterno());
            ps.setString(3,u.getMaterno());
            ps.setString(4,u.getEmail());
            ps.setString(5,u.getNombreUsuario());
            ps.setString(6,u.getClave());
            ps.setString(7,u.getTipo());
            ps.executeUpdate();
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    public void update (Usuario u) throws SQLException{
        PreparedStatement ps=null;
        try {
            ps=conexionDB.prepareStatement(SQL_UPDATE);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getPaterno());
            ps.setString(3,u.getMaterno());
            ps.setString(4,u.getEmail());
            ps.setString(5,u.getNombreUsuario());
            ps.setString(6,u.getClave());
            ps.setString(7,u.getTipo());
            ps.setInt(8, u.getIdusuario());
            ps.executeUpdate();
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    public void delete (Usuario u) throws SQLException{
        PreparedStatement ps=null;
        try {
            ps=conexionDB.prepareStatement(SQL_DELETE);
            ps.setInt(1,u.getIdusuario());
            ps.executeUpdate();
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    public Usuario load(Usuario u) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conexionDB.prepareStatement(SQL_SELECT);
            ps.setInt(1,u.getIdusuario());
            rs=ps.executeQuery();
            List resultados=ObtenerResultados(rs);
            if(resultados.size()>0){
            return(Usuario) (resultados.get(0));
            }
            else{
            return null;
            }
            
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    public Usuario login(Usuario u) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conexionDB.prepareStatement(SQL_log);
            ps.setString(1,u.getNombreUsuario());
            ps.setString(2,u.getClave());
            rs=ps.executeQuery();
            List resultados=ObtenerResultados(rs);
            if(resultados.size()>0){
            return(Usuario) (resultados.get(0));
            }
            else{
            return null;
            }
            
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    
    public List load_all() throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conexionDB.prepareStatement(SQL_SELECT_ALL);
            rs=ps.executeQuery();
            List resultados=ObtenerResultados(rs);
            if(resultados.size()>0){
            return resultados;
            }
            else{
            return null;
            }
            
        } finally{
            if(ps!=null)ps.close();
            if(conexionDB!=null)conexionDB.close();
        }
    }
    

    private List ObtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new LinkedList();
        while (rs.next()){
            Usuario u =new Usuario();
            u.setIdusuario(rs.getInt(1));
            u.setNombre(rs.getString(2));
            u.setPaterno(rs.getString(3));
            u.setMaterno(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setNombreUsuario(rs.getString(6));
            u.setClave(rs.getString(7));
            u.setTipo(rs.getString(8));
            resultados.add(u);
       }
        return resultados;
    }
    
    
    public  Connection conectar(String url, String driver, String usuario, String password){
        try {			
        Class.forName(driver);
        java.sql.Connection con = DriverManager.getConnection(url, usuario, password);
        System.out.println("Conexion establecida");
         return con;
        } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
        }
        catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
        } 
        catch (Exception e){
        e.printStackTrace();
        return null;
        }
    }
	    
}
