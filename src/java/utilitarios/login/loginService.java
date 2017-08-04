/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.login;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;

/**
 *
 * @author Javier
 */
@WebService(serviceName = "Login", portName = "LoginWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.login.loginwsdl.LoginWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/login/LoginWSDL", wsdlLocation = "WEB-INF/wsdl/loginService/LoginWSDL.wsdl")
public class loginService {

    public boolean registrar(org.netbeans.xml.schema.loginschema.Usuario usuario) {
        Conexion con = new Conexion();
        String query = "insert into login VALUES ('"+usuario.getNombre()+"','"+usuario.getContrasena()+"', '"+usuario.getUsuario()+"')";
        boolean estatus = con.insertar(query);
        return estatus;
    }

    public boolean verificar(org.netbeans.xml.schema.loginschema.Usuario usuarioV) {
        Conexion con = new Conexion();
        String query = "select * from login where usuario = '"+usuarioV.getUsuario()+"';";
        ResultSet rs = con.select(query);
        try {
            while(rs.next()){
               
            return rs.getString("contrasena").equals(usuarioV.getContrasena());
                
            
            
         }
         rs.close();
        } catch (SQLException e) {
            System.out.println("error: "+e);
        }
            
        return false;
    }
    
}
