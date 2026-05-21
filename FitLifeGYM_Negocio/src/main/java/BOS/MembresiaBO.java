/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import DAOS.PersistenciaException;
import Entidades.Estado;
import Entidades.Membresia;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class MembresiaBO implements IMembresiaBO {

    private final IFachada fachada;

    /**
     * @param fachada Fachada de persistencia dada por FabricaBO
     */
    public MembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }
    
    /**
     * Lista todas las membresías sin importar el estado.
     * @return Lista de MembresiaDTO
     * @throws NegocioException si falla la consulta
     */
    @Override
    public List<MembresiaDTO> listarTodas() throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresias(fachada.obtenerTodas());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al listar todas las membresías", e);
        }
    }

    /**
     * Lista solo las membresías con estado ACTIVA.
     * @return Lista de MembresiaDTO activas
     * @throws NegocioException si falla la consulta
     */
    @Override
    public List<MembresiaDTO> listarActivas() throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresias(fachada.obtenerActivas());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al listar membresías activas", e);
        }
    }

    /**
     * Obtiene una membresía por su ID.
     * @param  idMembresia ID de la membresía
     * @return MembresiaDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    @Override
    public MembresiaDTO obtenerPorId(String idMembresia) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresia(fachada.obtenerMembresiaPorId(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener membresía por ID", e);
        }
    }

    /**
     * Crea una nueva membresía con su periodo.
     * Convierte NuevaMembresiaDTO a entidad.
     * @param  nuevaMembresiaDTO DTO con nombre, precio, estado, beneficios, imagen y fechas
     * @return MembresiaDTO creada
     * @throws NegocioException si falla la insercción
     */
    @Override
    public MembresiaDTO agregar(NuevaMembresiaDTO nuevaMembresiaDTO) throws NegocioException {
        try {
            Membresia membresia = new Membresia();
            membresia.setNombre(nuevaMembresiaDTO.getNombre());
            membresia.setPrecio(nuevaMembresiaDTO.getPrecio());
            membresia.setEstado(Estado.valueOf(nuevaMembresiaDTO.getEstado()));
            membresia.setBeneficios(nuevaMembresiaDTO.getBeneficios());
            if (nuevaMembresiaDTO.getImagen() != null) {
                membresia.setImagen(DtosAEntidadesAdapter.adaptarImagenDTO(nuevaMembresiaDTO.getImagen()));
            }
            Membresia guardada = fachada.guardarMembresia(membresia);
            fachada.guardarPeriodo(guardada.getIdMembresia(), nuevaMembresiaDTO.getFechaInicio(),nuevaMembresiaDTO.getFechaFin()
            );
            return DtosAEntidadesAdapter.adaptarMembresia(guardada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar la membresía", e);
        }
    }

    /**
     * Edita los datos de una membresía y su periodo.
     * @param  idMembresia ID de la membresía 
     * @param  membresiaDTO DTO con los nuevos valores
     * @return MembresiaDTO actualizada
     * @throws NegocioException si no se encuentra la membresía o falla la actualización
     */
    @Override
    public MembresiaDTO editar(String idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException {
        try {
            Membresia membresia = fachada.obtenerMembresiaPorId(idMembresia);
            if (membresia == null) {
                throw new NegocioException("No se encontró la membresía");
            }
            membresia.setNombre(membresiaDTO.getNombre());
            membresia.setPrecio(membresiaDTO.getPrecio());
            membresia.setEstado(Estado.valueOf(membresiaDTO.getEstado()));
            membresia.setBeneficios(membresiaDTO.getBeneficios());
            
            Membresia editada = fachada.editarMembresia(membresia);

            fachada.editarPeriodo(idMembresia, membresiaDTO.getFechaInicio(), membresiaDTO.getFechaFin()
            );

            return DtosAEntidadesAdapter.adaptarMembresia(editada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar la membresía", e);
        }
    }

    /**
     * Elimina una membresía por su ID.
     * @param  idMembresia ID de la membresía 
     * @throws NegocioException si falla la eliminación
     */
    @Override
    public void eliminar(String idMembresia) throws NegocioException {
        try {
            fachada.eliminarMembresia(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar la membresía", e);
        }
    }

    /**
     * Valida que los campos obligatorios de una membresía no estén vacíos.
     * @param nombre Nombre de la membresía
     * @param precio Precio mayor a 0
     * @param estado Estado ACTIVA o NO_ACTIVA
     * @param beneficios Lista con al menos un beneficio
     * @return true si todos los campos son válidos
     * @throws NegocioException si falla alguna validación
     */
    @Override
    public Boolean validarCampos(String nombre, Float precio, String estado, List<String> beneficios) throws NegocioException {
        if (nombre == null || nombre.trim().isEmpty()) 
            return false;
        if (precio == null || precio <= 0) 
            return false;
        if (estado == null || estado.trim().isEmpty())
            return false;
        if (beneficios == null || beneficios.isEmpty()) 
            return false;
        return true;
    }

    /**
     * Verifica si una membresía tiene suscripciones activas.
     * Se usa antes de eliminar para evitar dejar usuarios sin membresía.
     * @param  idMembresia ID de la membresía
     * @return true si tiene al menos una suscripción activa
     * @throws NegocioException si falla la consulta
     */
    @Override
    public Boolean verificarSuscripcionesActivas(String idMembresia) throws NegocioException {
        try {
            return fachada.contarActivasPorMembresia(idMembresia) > 0;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar suscripciones activas", e);
        }
    }

    
    /**
     * Verifica si el estado de una membresía es ACTIVA.
     * @param  idMembresia ID de la membresía
     * @return true si el estado es ACTIVA
     * @throws NegocioException si falla la consulta
     */
    @Override
    public Boolean verificarEstadoMembresia(String idMembresia) throws NegocioException {
        MembresiaDTO membresiaDTO = obtenerPorId(idMembresia);
        if (membresiaDTO == null) 
            return false;
        return "ACTIVA".equals(membresiaDTO.getEstado());
    }

}
