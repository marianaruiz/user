/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete.un;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mariana
 */
public class GraficaDao {
    private String url = "jdbc:mysql://localhost:3306/Usuarios";
    private String driver = "com.mysql.jdbc.Driver";
    private String usuario = "root";
    private String password = "root";
    private Connection conexionDB = null;
    private static final String SQL_GRAFICAR="{call spDatosGrafica()}";
    public GraficaDao(){
    this.conexionDB= conectar(this.url, this.driver, this.usuario, this.password);
    }

    private Connection conectar(String url, String driver, String usuario, String password) {
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
    public List grafica () throws SQLException{
        CallableStatement cs=null;
        ResultSet rs=null;
        List lista =new ArrayList();
        try{
            cs=conexionDB.prepareCall(SQL_GRAFICAR);
            rs=cs.executeQuery();
            while(rs.next()){
            Grafica grafica =new Grafica();
            grafica.setCantidad(Integer.parseInt(rs.getString("Alumnos")));
            grafica.setNombre(rs.getString("carreras"));
            lista.add(grafica);
            }
            }
        finally{
            if(rs!=null){rs.close();}
            if(cs!=null){cs.close();}
        }
        return lista;
        
    }
}
