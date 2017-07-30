/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.lector;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import org.netbeans.xml.schema.lectorschema.Lector;

/**
 *
 * @author Javier
 */
@WebService(serviceName = "Lector", portName = "LectorWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.sep.lector.lectorwsdl.LectorWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SEP/lector/LectorWSDL", wsdlLocation = "WEB-INF/wsdl/LectorService/LectorWSDL.wsdl")
public class LectorService {

    public boolean registroLectorOperation(org.netbeans.xml.schema.lectorschema.Lector lector) {
          Conexion con = new Conexion();
        String query = "insert into lectores VALUES ('"+lector.getNombre()+"','"+lector.getContrasena()+"', '"+lector.getUsuario()+"')";
        boolean estatus = con.insertar(query);
        return estatus;
    }

    public boolean eliminarLectorOperation(java.lang.String lector3) {
        Conexion con = new Conexion();
        String query = "DELETE FROM lectores WHERE usuario = '"+lector3+"';";
        boolean estatus = con.eliminar(query);
        return estatus;
    }

    public boolean actualizarLectorOperation(org.netbeans.xml.schema.lectorschema.Lector lector4) {
        Conexion con = new Conexion();
        
        String query = "UPDATE lectores SET nombre = '"+lector4.getNombre()+"' ,contrasena = '"+lector4.getContrasena()+"' WHERE usuario = '"+lector4.getUsuario()+"';";
        boolean estatus = con.actualizar(query);
        return estatus;
    }

    public org.netbeans.xml.schema.lectorschema.Lector conseguirLectorOperation(java.lang.String lector2) {
        Conexion con = new Conexion();
        String query = "select * from lectores where usuario = '"+lector2+"';";
        ResultSet rs = con.select(query);
        try {
            while(rs.next()){
            return (Lector) rs;
            
         }
         rs.close();
        } catch (SQLException e) {
            System.out.println("error: "+e);
        }
            
        return null;
    }
    
}
