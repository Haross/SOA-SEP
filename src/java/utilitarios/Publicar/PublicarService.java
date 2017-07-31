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
import org.netbeans.xml.schema.publicarschema.Capacitaciones;
import org.netbeans.xml.schema.publicarschema.ClubDeLectura;

/**
 *
 * @author Josseline Arreola
 */
@WebService(serviceName = "PublicarWSDLService", portName = "PublicarWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.publicar.publicarwsdl.PublicarWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/Publicar/PublicarWSDL", wsdlLocation = "WEB-INF/wsdl/PublicarService/PublicarWSDL.wsdl")
public class PublicarService {
    Conexion con = new Conexion();
    public org.netbeans.xml.schema.publicarschema.Capacitaciones publicarCapacitacion(int tipo) {
        if (tipo==1){
          
        String query = "select * from capacitaciones;";
        ResultSet rs = con.select(query);
        try {
            while(rs.next()){
                Capacitaciones aux = new Capacitaciones();
                aux.setFolio(rs.getString("folio"));
                aux.setNombre(rs.getString("nombre"));
                aux.setTipo(rs.getString("tipo"));
                aux.setObjetivos(rs.getString("objetivos"));
                aux.setRequisitos(rs.getString("requisitos"));
                aux.setFechaPublicacion(rs.getString("fechapublicaciones"));
            return aux;
            
         }
         rs.close();
        } catch (SQLException e) {
            System.out.println("error: "+e);
        }
            
        return null;  
        }
        else{
            if (tipo==2){
                publicarClub(2);
            }
        }
        return null;
    }

    public org.netbeans.xml.schema.publicarschema.ClubDeLectura publicarClub(int tipoClub) {
       if (tipoClub==2){
        String query = "select * from clublectura;";
                ResultSet rs = con.select(query);
                try {
                    while(rs.next()){
                        ClubDeLectura aux2 = new ClubDeLectura();
                        aux2.setNombre(rs.getString("nombre"));
                        aux2.setCupo(rs.getInt("cupo"));
                        aux2.setTematica(rs.getString("tematica"));
                    return aux2;
                 }
                 rs.close();
                } catch (SQLException e) {
                    System.out.println("error: "+e);
                }
        }else{
           if (tipoClub==1){
               publicarCapacitacion(1);
           }
       }
                return null;
    }
    
}
