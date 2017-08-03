/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.clubDeLectura;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import org.netbeans.xml.schema.clubdelecturaschema.Club;

/**
 *
 * @author Josseline Arreola
 */
@WebService(serviceName = "ClubDeLecturaWSDLService", portName = "ClubDeLecturaWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.clubdelectura.clubdelecturawsdl.ClubDeLecturaWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/clubDeLectura/ClubDeLecturaWSDL", wsdlLocation = "WEB-INF/wsdl/ClubDeLecturaService/ClubDeLecturaWSDL.wsdl")
public class ClubDeLecturaService {

    public java.lang.String registroClubDeLectura(org.netbeans.xml.schema.clubdelecturaschema.Club clubR) {
        Conexion con = new Conexion();
        String query = "insert into clublectura VALUES ('"+clubR.getNombre()+"','"+clubR.getCupo()+"', '"+clubR.getTematica()+"')";
        boolean estatus = con.insertar(query);
        if (estatus)
            return "El registro fue exitoso";
        else
            return "Registro no exitoso. El nombre ya existe";
    }

    public org.netbeans.xml.schema.clubdelecturaschema.Club consultarClubDeLectura(java.lang.String clubC) {
        Conexion con = new Conexion();
        String query = "select * from clublectura where nombre = '"+clubC+"';";
        ResultSet rs = con.select(query);
        try {
            while(rs.next()){
                Club aux = new Club();
                aux.setNombre(rs.getString("nombre"));
                aux.setCupo(rs.getInt("cupo"));
                aux.setTematica(rs.getString("tematica"));

            return aux;
            
         }
         rs.close();
        } catch (SQLException e) {
            System.out.println("error: "+e);
        }
            
        return null;
    }

    public boolean eliminarClubDeLectura(java.lang.String clubE) {
        Conexion con = new Conexion();
        String query = "DELETE FROM clublectura WHERE nombre = '"+clubE+"';";
        boolean estatus = con.eliminar(query);
        return estatus;
    }

    public boolean actualizarClubDeLectura(org.netbeans.xml.schema.clubdelecturaschema.Club clubA) {
        Conexion con = new Conexion();
        String query = "UPDATE clublectura SET cupo = '"+clubA.getCupo()+"' ,tematica = '"+clubA.getTematica()+"' WHERE nombre = '"+clubA.getNombre()+"';";
        boolean estatus = con.actualizar(query);
        return estatus;
    }
    
}
