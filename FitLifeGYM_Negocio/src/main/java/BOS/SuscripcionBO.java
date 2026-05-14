/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.Fachada;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import DAOS.ISuscripcionDAO;
import DAOS.PersistenciaException;
import Entidades.Estado;
import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Suscripcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class SuscripcionBO implements ISuscripcionBO{

    private final IFachada fachada;

    public SuscripcionBO(IFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public Integer contarActivasPorMembresia(Long idMembresia) throws NegocioException {
        try {
            return fachada.contarActivasPorMembresia(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al contar suscripciones activas", e);
        }
    }

    @Override
    public List<SuscripcionDTO> obtenerActivasPorMembresia(Long idMembresia)
            throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarSuscripciones(
                    fachada.obtenerSuscripcionesPorMembresia(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener suscripciones activas", e);
        }
    }

    @Override
    public Boolean verificarSuscripcionActiva(Long idUsuario) throws NegocioException {
        try {
            return fachada.obtenerSuscripcionActivaPorUsuario(idUsuario) != null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar suscripción activa", e);
        }
    }

    @Override
    public SuscripcionDTO obtenerActivaPorUsuario(Long idUsuario) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarSuscripcion(
                    fachada.obtenerSuscripcionActivaPorUsuario(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener suscripción activa", e);
        }
    }

    @Override
    public SuscripcionDTO registrar(NuevaSuscripcionDTO nuevaSuscripcionDTO)
            throws NegocioException {
        try {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setIdUsuario(nuevaSuscripcionDTO.getIdUsuario());
            suscripcion.setIdMembresia(nuevaSuscripcionDTO.getIdMembresia());
            suscripcion.setFechaInicio(nuevaSuscripcionDTO.getFechaInicio());
            suscripcion.setFechaVencimiento(nuevaSuscripcionDTO.getFechaVencimiento());
            suscripcion.setPrecioPagado(nuevaSuscripcionDTO.getPrecioPagado());
            suscripcion.setEstado(Estado.ACTIVA);
            return DtosAEntidadesAdapter.adaptarSuscripcion(
                    fachada.guardarSuscripcion(suscripcion));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la suscripción", e);
        }
    }

    @Override
    public void guardarEnHistorial(SuscripcionDTO suscripcionDTO) throws NegocioException {
        try {
            HistorialSuscripcionesMembresias historial = new HistorialSuscripcionesMembresias();
            historial.setIdUsuario(suscripcionDTO.getIdUsuario());
            historial.setIdMembresia(suscripcionDTO.getIdMembresia());
            historial.setPrecioPagado(suscripcionDTO.getPrecioPagado());
            historial.setFechaInicio(suscripcionDTO.getFechaInicio());
            historial.setFechaVencimiento(suscripcionDTO.getFechaVencimiento());

            List<Suscripcion> lista = new ArrayList<>();
            lista.add(DtosAEntidadesAdapter.adaptarSuscripcionDTO(suscripcionDTO));
            historial.setSuscripciones(lista);

            fachada.guardarHistorial(historial);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al guardar en historial", e);
        }
    }

    @Override
    public MembresiaDTO obtenerMembresiaActivaDeUsuario(Long idUsuario) throws NegocioException {
            try {
            Suscripcion suscripcion = fachada.obtenerSuscripcionActivaPorUsuario(idUsuario);
            if (suscripcion == null) return null;
            return DtosAEntidadesAdapter.adaptarMembresia(
                    fachada.obtenerMembresiaPorId(suscripcion.getIdMembresia()));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener membresía activa del usuario", e);
        }
    }
    
   
}
