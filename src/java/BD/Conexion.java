/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class Conexion {
    Connection conn;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

    public Conexion()  {
        try{
        // URI dbUri = new URI("jdbc:postgresql://ec2-54-235-120-39.compute-1.amazonaws.com:5432/d6lbmjlsussr3b?sslmode=require&user=juvzhtttwxbugr&password=d604cc2437cc24c04726211041e12d9458bea7736a11e7d9f54b754050ed80f9");
       
            
        URI dbUri = new URI("postgres://juvzhtttwxbugr:d604cc2437cc24c04726211041e12d9458bea7736a11e7d9f54b754050ed80f9@ec2-54-235-120-39.compute-1.amazonaws.com:5432/d6lbmjlsussr3b");
      
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        conn =  DriverManager.getConnection(dbUrl, username, password);
        if(conn == null){
            System.out.println("fallo");
        }else{
            System.out.println("exito");
        }
        }catch(URISyntaxException | SQLException e){
            System.out.println("error: "+e);
        }
    }
    
    public ResultSet select(String sentencia){
        
       // ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(sentencia);
            /*while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
            }*/
            rs.close();
            st.close();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean insertar(String query){
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        }catch(SQLException e){
            System.out.println("Error insertar: "+e);
            return false;
        }
    }
    
    public boolean actualizar(String query){
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        }catch(SQLException e){
            System.out.println("Error insertar: "+e);
            return false;
        }
    
    }
    public boolean eliminar(String query){
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        }catch(SQLException e){
            System.out.println("Error insertar: "+e);
            return false;
        }
    
    }
    

    

}
