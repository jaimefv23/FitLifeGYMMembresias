/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import DAOS.PersistenciaException;
import Entidades.Estado;
import Entidades.HistorialSuscripcion;
import Entidades.Suscripcion;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class SuscripcionBO implements ISuscripcionBO{

    private final IFachada fachada;

    /**
     * @param fachada Fachada de persistencia dada por FabricaBO
     */
    public SuscripcionBO(IFachada fachada) {
        this.fachada = fachada;
    }

    
    /**
     * Cuenta las suscripciones activas de una membresía.
     * @param idMembresia ID de la membresía
     * @return Número de suscripciones activas
     * @throws NegocioException si falla la consulta
     */
    @Override
    public Integer contarActivasPorMembresia(String idMembresia) throws NegocioException {
        try {
            return fachada.contarActivasPorMembresia(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al contar suscripciones activas", e);
        }
    }

    /**
     * Devuelve las suscripciones activas de una membresía.
     * @param  idMembresia ID de la membresía
     * @return Lista de suscripciones activas
     * @throws NegocioException si falla la consulta
     */
    @Override
    public List<SuscripcionDTO> obtenerActivasPorMembresia(String idMembresia)
            throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarSuscripciones(fachada.obtenerSuscripcionesPorMembresia(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener suscripciones activas", e);
        }
    }

    /**
     * Verifica si un usuario tiene alguna suscripción activa.
     * @param  idUsuario ID del usuario
     * @return true si tiene suscripción activa
     * @throws NegocioException si falla la consulta
     */
    @Override
    public Boolean verificarSuscripcionActiva(String idUsuario) throws NegocioException {
        try {
            return fachada.obtenerSuscripcionActivaPorUsuario(idUsuario) != null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar suscripción activa", e);
        }
    }

    /**
     * Obtiene la suscripción activa de un usuario.
     * @param  idUsuario ID del usuario
     * @return SuscripcionDTO activa o null si no tiene
     * @throws NegocioException si falla la conslta
     */
    @Override
    public SuscripcionDTO obtenerActivaPorUsuario(String idUsuario) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarSuscripcion(fachada.obtenerSuscripcionActivaPorUsuario(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener suscripción activa", e);
        }
    }

    /**
     * Registra una nueva suscripción con estado ACTIVA.
     * @param  nuevaSuscripcionDTO DTO con idUsuario, idMembresia, fechas, precio y estado
     * @return SuscripcionDTO registrada
     * @throws NegocioException si falla la persistencia
     */
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
            return DtosAEntidadesAdapter.adaptarSuscripcion(fachada.guardarSuscripcion(suscripcion));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la suscripción", e);
        }
    }

    /**
     * Agrega la suscripción al historial embebido del documento.
     * @param suscripcionDTO DTO de la suscripción a historizar
     * @throws NegocioException si falla la insercción 
     */
    @Override
    public void guardarEnHistorial(SuscripcionDTO suscripcionDTO) throws NegocioException {
        try {
            HistorialSuscripcion historial = new HistorialSuscripcion(
                    suscripcionDTO.getIdMembresia(),
                    suscripcionDTO.getFechaInicio(),
                    suscripcionDTO.getFechaVencimiento(),
                    suscripcionDTO.getPrecioPagado(),
                    suscripcionDTO.getEstado()
            );
            fachada.agregarAlHistorial(suscripcionDTO.getIdUsuario(), historial);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al guardar en historial", e);
        }
    }

    /**
     * Obtiene la membresía activa de un Usuario.
     * @param  idUsuario ID del usuario
     * @return MembresiaDTO activa del usuario o null si no tiene
     * @throws NegocioException si falla la consulta
     */
    @Override
    public MembresiaDTO obtenerMembresiaActivaDeUsuario(String idUsuario) throws NegocioException {
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
