/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.Fachada;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.ImagenDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import DAOS.IMembresiaDAO;
import DAOS.ISuscripcionDAO;
import DAOS.PersistenciaException;
import Entidades.Estado;
import Entidades.Imagen;
import Entidades.Membresia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class MembresiaBO implements IMembresiaBO {

    private final IFachada fachada;

    public MembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<MembresiaDTO> listarTodas() throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresias(fachada.obtenerTodas());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al listar todas las membresías", e);
        }
    }

    @Override
    public List<MembresiaDTO> listarActivas() throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresias(fachada.obtenerActivas());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al listar membresías activas", e);
        }
    }

    @Override
    public MembresiaDTO obtenerPorId(Long idMembresia) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarMembresia(
                    fachada.obtenerMembresiaPorId(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener membresía por ID", e);
        }
    }

    @Override
    public MembresiaDTO agregar(NuevaMembresiaDTO nuevaMembresiaDTO) throws NegocioException {
        try {
            Membresia membresia = new Membresia();
            membresia.setNombre(nuevaMembresiaDTO.getNombre());
            membresia.setPrecio(nuevaMembresiaDTO.getPrecio());
            membresia.setEstado(Estado.valueOf(nuevaMembresiaDTO.getEstado()));
            membresia.setBeneficios(nuevaMembresiaDTO.getBeneficios());
            if (nuevaMembresiaDTO.getImagen() != null) {
                membresia.setImagen(DtosAEntidadesAdapter.adaptarImagenDTO(
                        nuevaMembresiaDTO.getImagen()));
            }
            Membresia guardada = fachada.guardarMembresia(membresia);
            fachada.guardarPeriodo(
                    guardada.getIdMembresia(),
                    nuevaMembresiaDTO.getFechaInicio(),
                    nuevaMembresiaDTO.getFechaFin()
            );
            return DtosAEntidadesAdapter.adaptarMembresia(guardada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar la membresía", e);
        }
    }

    @Override
    public MembresiaDTO editar(Long idMembresia,
                               EditarMembresiaDTO membresiaDTO) throws NegocioException {
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
            if (membresia.getPeriodo() != null) {
                fachada.editarPeriodo(
                        membresia.getPeriodo().getIdPeriodo(),
                        membresiaDTO.getFechaInicio(),
                        membresiaDTO.getFechaFin()
                );
            }
            return DtosAEntidadesAdapter.adaptarMembresia(editada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar la membresía", e);
        }
    }

    @Override
    public void eliminar(Long idMembresia) throws NegocioException {
        try {
            fachada.eliminarMembresia(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar la membresía", e);
        }
    }

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

    @Override
    public Boolean verificarSuscripcionesActivas(Long idMembresia) throws NegocioException {
        try {
            return fachada.contarActivasPorMembresia(idMembresia) > 0;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar suscripciones activas", e);
        }
    }

    @Override
    public Boolean verificarEstadoMembresia(Long idMembresia) throws NegocioException {
        MembresiaDTO mem = obtenerPorId(idMembresia);
        if (mem == null) 
            return false;
        return "ACTIVA".equals(mem.getEstado());
    }

}
