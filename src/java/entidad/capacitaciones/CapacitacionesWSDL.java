/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.capacitaciones;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.jws.WebService;
import org.netbeans.xml.schema.capacitacionesschema.Capacitaciones;

/**
 *
 * @author Jesus
 */
@WebService(serviceName = "CapacitacionesWSDL", portName = "CapacitacionesWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.capacitaciones.capacitacioneswsdl.CapacitacionesWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/capacitaciones/CapacitacionesWSDL", wsdlLocation = "WEB-INF/wsdl/CapacitacionesWSDL/CapacitacionesWSDL.wsdl")
public class CapacitacionesWSDL {

    public boolean capacitacionesWSDL(org.netbeans.xml.schema.capacitacionesschema.Capacitaciones registroC) {
         Conexion con = new Conexion();
        int folio=0;
        Random r = new Random();
        folio =r.nextInt(100000)+1;
        String Fol = String.valueOf(folio);
        String query = "insert into capacitaciones VALUES ('"+Fol+"','"+registroC.getNombre()+"', '"+registroC.getTipo()+"','"+registroC.getObjetivos()+"','"+registroC.getRequisitos()+","+registroC.getFechapublicacion()+"')";
        boolean estatus = con.insertar(query);
        return estatus;
    }

    /**
     * Web service operation
     */
    public Capacitaciones ConsultaWSDL(String Nombre) throws SQLException {
         Conexion con = new Conexion();
        String query = "select * from capacitaciones where nombre = '"+Nombre+"';";
        ResultSet rs = con.select(query);
        while(rs.next()){
            Capacitaciones aux = new Capacitaciones();
            aux.setNombre(rs.getString("folio"));
            aux.setNombre(rs.getString("nombre"));
            aux.setNombre(rs.getString("tipo"));
            aux.setNombre(rs.getString("objetivos"));
            aux.setNombre(rs.getString("requisitos"));
            aux.setNombre(rs.getString("fechapublicacion"));
            
            return aux;
            
        }
        rs.close();
            
        return null;
    }
    
}
