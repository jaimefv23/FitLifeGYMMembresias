/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.fitlifegym_dtos.ImagenDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import Entidades.Estado;
import Entidades.Imagen;
import Entidades.Membresia;
import Entidades.PeriodoMembresia;
import Entidades.Suscripcion;
import Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class DtosAEntidadesAdapter {
    
    // ══ Membresia ══

    public static MembresiaDTO adaptarMembresia(Membresia m) {
        if (m == null) return null;
        return new MembresiaDTO(
                m.getIdMembresia(),
                m.getNombre(),
                m.getPrecio(),
                m.getEstado().name(),
                m.getBeneficios(),
                m.getFechaCreacion()
        );
    }

    public static List<MembresiaDTO> adaptarMembresias(List<Membresia> membresias) {
        List<MembresiaDTO> dtos = new ArrayList<>();
        for (Membresia m : membresias) {
            dtos.add(adaptarMembresia(m));
        }
        return dtos;
    }

    // ══ PeriodoMembresia ══

    public static PeriodoMembresiaDTO adaptarPeriodo(PeriodoMembresia p) {
        if (p == null) return null;
        return new PeriodoMembresiaDTO(
                p.getIdPeriodo(),
                p.getIdMembresia(),
                p.getFechaInicio(),
                p.getFechaFin(),
                p.getVigente()
        );
    }

    // ══ Suscripcion ══

    public static SuscripcionDTO adaptarSuscripcion(Suscripcion s) {
        if (s == null) return null;
        return new SuscripcionDTO(
                s.getIdSuscripcion(),
                s.getIdUsuario(),
                s.getIdMembresia(),
                s.getPrecioPagado(),
                s.getFechaInicio(),
                s.getFechaVencimiento(),
                s.getEstado().name()
        );
    }

    public static List<SuscripcionDTO> adaptarSuscripciones(List<Suscripcion> suscripciones) {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripciones) {
            dtos.add(adaptarSuscripcion(s));
        }
        return dtos;
    }

    public static Suscripcion adaptarSuscripcionDTO(SuscripcionDTO dto) {
        if (dto == null) return null;
        Suscripcion s = new Suscripcion();
        s.setIdSuscripcion(dto.getIdSuscripcion());
        s.setIdUsuario(dto.getIdUsuario());
        s.setIdMembresia(dto.getIdMembresia());
        s.setPrecioPagado(dto.getPrecioPagado());
        s.setFechaInicio(dto.getFechaInicio());
        s.setFechaVencimiento(dto.getFechaVencimiento());
        s.setEstado(Estado.valueOf(dto.getEstado()));
        return s;
    }

    // ══ Usuario ══

    public static UsuarioDTO adaptarUsuario(Usuario u) {
        if (u == null) return null;
        return new UsuarioDTO(
                u.getIdUsuario(),
                u.getNombre(),
                u.getCorreo(),
                u.getRol()
        );
    }

    // ══ Reporte ══

    public static ReporteMembresiaDTO adaptarReporte(
            int totalUsuarios, double totalVentas,
            String tipo,
            java.time.LocalDate fechaInicio,
            java.time.LocalDate fechaFin) {
        return new ReporteMembresiaDTO(
                null, null, tipo,
                fechaInicio, fechaFin,
                totalUsuarios, totalVentas,
                java.time.LocalDate.now()
        );
    }
    
    public static Imagen adaptarImagenDTO(ImagenDTO imagenDTO) {
        Imagen imagen = new Imagen();
        imagen.setImagen(imagenDTO.getImagen());
        return imagen;
    }
    
}
