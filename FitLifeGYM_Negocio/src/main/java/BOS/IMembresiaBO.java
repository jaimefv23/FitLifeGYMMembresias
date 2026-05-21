/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import Entidades.Membresia;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IMembresiaBO {
    
    /**
     * Lista todas las membresías sin importar el estado.
     * @return Lista de MembresiaDTO
     * @throws NegocioException si falla la consulta
     */
    public abstract List<MembresiaDTO> listarTodas() throws NegocioException;
    
    /**
     * Lista solo las membresías con estado ACTIVA.
     * @return Lista de MembresiaDTO activas
     * @throws NegocioException si falla la consulta
     */
    public abstract List<MembresiaDTO> listarActivas() throws NegocioException;
    
    /**
     * Obtiene una membresía por su ID.
     * @param  idMembresia ID de la membresía
     * @return MembresiaDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    public abstract MembresiaDTO obtenerPorId(String idMembresia) throws NegocioException;
    
    
    /**
     * Verifica si el estado de una membresía es ACTIVA.
     * @param  idMembresia ID de la membresía
     * @return true si el estado es ACTIVA
     * @throws NegocioException si falla la consulta
     */
    public abstract Boolean verificarEstadoMembresia(String idMembresia) throws NegocioException;

    /**
     * Crea una nueva membresía con su periodo.
     * Convierte NuevaMembresiaDTO a entidad.
     * @param  nuevaMembresiaDTO DTO con nombre, precio, estado, beneficios, imagen y fechas
     * @return MembresiaDTO creada
     * @throws NegocioException si falla la insercción
     */
    public abstract MembresiaDTO agregar(NuevaMembresiaDTO nuevaMembresiaDTO) throws NegocioException;
    
    /**
     * Edita los datos de una membresía y su periodo.
     * @param  idMembresia ID de la membresía 
     * @param  membresiaDTO DTO con los nuevos valores
     * @return MembresiaDTO actualizada
     * @throws NegocioException si no se encuentra la membresía o falla la actualización
     */
    public abstract MembresiaDTO editar(String idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException;
    
    /**
     * Elimina una membresía por su ID.
     * @param  idMembresia ID de la membresía 
     * @throws NegocioException si falla la eliminación
     */
    public abstract void eliminar(String idMembresia) throws NegocioException;
    
    /**
     * Valida que los campos obligatorios de una membresía no estén vacíos.
     * @param nombre Nombre de la membresía
     * @param precio Precio mayor a 0
     * @param estado Estado ACTIVA o NO_ACTIVA
     * @param beneficios Lista con al menos un beneficio
     * @return true si todos los campos son válidos
     * @throws NegocioException si falla alguna validación
     */
    public abstract Boolean validarCampos(String nombre, Float precio, String estado, List<String> beneficios) throws NegocioException;
    
     /**
     * Verifica si una membresía tiene suscripciones activas.
     * Se usa antes de eliminar para evitar dejar usuarios sin membresía.
     * @param  idMembresia ID de la membresía
     * @return true si tiene al menos una suscripción activa
     * @throws NegocioException si falla la consulta
     */
    public abstract Boolean verificarSuscripcionesActivas(String idMembresia) throws NegocioException;

}
