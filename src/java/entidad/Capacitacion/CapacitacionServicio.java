/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.Capacitacion;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import org.netbeans.xml.schema.capacitacionschema.Capacitacion;
import org.netbeans.xml.schema.capacitacionschema.CapacitacionNom;
import org.netbeans.xml.schema.capacitacionschema.Capacitaciones;

/**
 *
 * @author Javier
 */
@WebService(serviceName = "CapacitacionWSDLService", portName = "CapacitacionWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.capacitacion.capacitacionwsdl.CapacitacionWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/Capacitacion/CapacitacionWSDL", wsdlLocation = "WEB-INF/wsdl/CapacitacionServicio/CapacitacionWSDL.wsdl")
public class CapacitacionServicio {
Conexion con = new Conexion();
    public org.netbeans.xml.schema.capacitacionschema.CapacitacionNom capacitacionNombre(java.lang.String nombre) {
        String query = "select * from capacitaciones where nombre = '"+nombre+"';";
            ResultSet rs = con.select(query);
            try {
                
                CapacitacionNom c = new CapacitacionNom();
                while(rs.next()){
                    Capacitaciones aux = new Capacitaciones();
                    aux.setFolio(rs.getString("folio"));
                    aux.setNombre(rs.getString("nombre"));
                    aux.setTipo(rs.getString("tipo"));
                    aux.setObjetivos(rs.getString("objetivos"));
                    aux.setRequisitos(rs.getString("requisitos"));
                    aux.setFechaPublicacion(rs.getString("fechapublicacion"));
                    c.getListaNombre().add(aux);
             }
             return c;
            } catch (SQLException e) {
                System.out.println("error: "+e);
                return null;
    }
    }

    public org.netbeans.xml.schema.capacitacionschema.Capacitacion capacitacionTipo(java.lang.String tipo) {
        String query = "select * from capacitaciones where tipo = '"+tipo+"';";
            ResultSet rs = con.select(query);
            try {
                
                Capacitacion c = new Capacitacion();
                while(rs.next()){
                    Capacitaciones aux = new Capacitaciones();
                    aux.setFolio(rs.getString("folio"));
                    aux.setNombre(rs.getString("nombre"));
                    aux.setTipo(rs.getString("tipo"));
                    aux.setObjetivos(rs.getString("objetivos"));
                    aux.setRequisitos(rs.getString("requisitos"));
                    aux.setFechaPublicacion(rs.getString("fechapublicacion"));
                    c.getListaCapacitaciones().add(aux);
             }
             return c;
            } catch (SQLException e) {
                System.out.println("error: "+e);
                return null;
    }
    }
    
}
