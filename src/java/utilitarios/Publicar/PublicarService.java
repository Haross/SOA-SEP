/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.Publicar;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import org.netbeans.xml.schema.publicarschema.Capacitacion;
import org.netbeans.xml.schema.publicarschema.Capacitaciones;
import org.netbeans.xml.schema.publicarschema.Club;
import org.netbeans.xml.schema.publicarschema.ClubDeLectura;

/**
 *
 * @author Josseline Arreola
 */
@WebService(serviceName = "PublicarWSDLService", portName = "PublicarWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.publicar.publicarwsdl.PublicarWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/Publicar/PublicarWSDL", wsdlLocation = "WEB-INF/wsdl/PublicarService/PublicarWSDL.wsdl")
public class PublicarService {
    Conexion con = new Conexion();
    
    public org.netbeans.xml.schema.publicarschema.Capacitacion publicarCapacitacion(int tipo) {
        if (tipo==1){
            String query = "select * from capacitaciones;";
            ResultSet rs = con.select(query);
            try {
                Capacitaciones aux = new Capacitaciones();
                Capacitacion c = new Capacitacion();
                while(rs.next()){
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
        }else{
            if (tipo==2){
                publicarClub(2);
            }
        }
        return null;
    }

    public org.netbeans.xml.schema.publicarschema.Club publicarClub(int tipoC) {
        if (tipoC==2){
            String query = "select * from clublectura;";
            ResultSet rs = con.select(query);
            try {
                ClubDeLectura aux = new ClubDeLectura();
                Club c = new Club();
                while(rs.next()){
                    aux.setNombre(rs.getString("nombre"));
                    aux.setCupo(rs.getInt("cupo"));
                    aux.setTematica(rs.getString("tematica"));
                    c.getListaClub().add(aux);
             }
             return c;
            } catch (SQLException e) {
                System.out.println("error: "+e);
                return null;
            } 
        }else{
            if (tipoC==2){
                publicarCapacitacion(2);
            }
        }
        return null;
    }
    
}
