/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import Entidades.Usuario;
import java.time.LocalDate;
import org.bson.Document;

/**
 *
 * @author Jaime
 */
public class UsuarioToDocumentAdapter {
    
    private static final String ID_USUARIO = "idUsuario";
    private static final String NOMBRE = "nombre";
    private static final String CORREO = "correo";
    private static final String CONTRASENIA = "contrasenia";
    private static final String FECHA_REGISTRO = "fechaRegistro";
    private static final String ROL = "rol";

    public static Usuario adaptar(Document doc) {
        if (doc == null) return null;
        Usuario u = new Usuario();
        u.setIdUsuario(doc.getLong(ID_USUARIO));
        u.setNombre(doc.getString(NOMBRE));
        u.setCorreo(doc.getString(CORREO));
        u.setContrasenia(doc.getString(CONTRASENIA));
        u.setFechaRegistro(doc.get(FECHA_REGISTRO, LocalDate.class));
        u.setRol(doc.getString(ROL));
        return u;
    }

    public static Document adaptar(Usuario u) {
        return new Document()
                .append(ID_USUARIO, u.getIdUsuario())
                .append(NOMBRE, u.getNombre())
                .append(CORREO, u.getCorreo())
                .append(CONTRASENIA, u.getContrasenia())
                .append(FECHA_REGISTRO, u.getFechaRegistro())
                .append(ROL, u.getRol());
    }
    
}
