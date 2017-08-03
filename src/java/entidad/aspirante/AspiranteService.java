/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.aspirante;

import BD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.jws.WebService;
import org.netbeans.xml.schema.aspiranteschema.Aspirante;

/**
 *
 * @author Josseline Arreola
 */
@WebService(serviceName = "AspiranteWSDLService", portName = "AspiranteWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.soa_sep.aspirante.aspirantewsdl.AspiranteWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/SOA-SEP/aspirante/AspiranteWSDL", wsdlLocation = "WEB-INF/wsdl/AspiranteService/AspiranteWSDL.wsdl")
public class AspiranteService {

    public int registroAspirante(org.netbeans.xml.schema.aspiranteschema.Aspirante aspirante) {
        Conexion con = new Conexion();
        int folio = 0;
        Random r = new Random();
        folio = r.nextInt(100000)+1;
        String query = "insert into aspirante VALUES ('"+aspirante.getNombre()+"','"+aspirante.getApellidos()+"', '"+aspirante.getFecha()+"', '"+aspirante.getCurp()+"', '"+aspirante.getEmail()+"', '"+aspirante.getTelefono()+"', '"+folio+"'); ";
        boolean estatus = con.insertar(query);
        if (estatus){
            System.out.println("Registro exitoso");
            return folio;
        }else{
            System.out.println("No se pudo registrar");
            return 0;  
        }
    }

    public org.netbeans.xml.schema.aspiranteschema.Aspirante consultaAspirante(int folioA) {
        Conexion con = new Conexion();
        String query = "select * from aspirante where folio = '"+folioA+"';";
        ResultSet rs = con.select(query);
        try {
            while(rs.next()){
                Aspirante aux = new Aspirante();
                aux.setNombre(rs.getString("nombre"));
                aux.setApellidos(rs.getString("apellidos"));
                aux.setFecha(rs.getString("fecha"));
                aux.setCurp(rs.getString("curp"));
                aux.setEmail(rs.getString("email"));
                aux.setTelefono(rs.getString("telefono"));
                aux.setFolio(rs.getInt("folio"));
            return aux;
            
         }
         rs.close();
        } catch (SQLException e) {
            System.out.println("error: "+e);
        }
            
        return null;
    }

    public boolean actualizarAspirante(org.netbeans.xml.schema.aspiranteschema.Aspirante aspiranteA) {
        Conexion con = new Conexion();
        String query = "UPDATE aspirante SET nombre = '"+aspiranteA.getNombre()+"' ,apellidos = '"+aspiranteA.getApellidos()+"' ,fecha = '"+aspiranteA.getFecha()+"' ,curp = '"+aspiranteA.getCurp()+"' ,email = '"+aspiranteA.getEmail()+"' ,telefono = '"+aspiranteA.getTelefono()+"' WHERE folio = '"+aspiranteA.getFolio()+"';";
        boolean estatus = con.actualizar(query);
        return estatus;
    }
    
}
