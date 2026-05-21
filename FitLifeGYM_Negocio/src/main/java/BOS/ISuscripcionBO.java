/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ISuscripcionBO {
    
    /**
     * Cuenta las suscripciones activas de una membresía.
     * @param idMembresia ID de la membresía
     * @return Número de suscripciones activas
     * @throws NegocioException si falla la consulta
     */
    public abstract Integer contarActivasPorMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Devuelve las suscripciones activas de una membresía.
     * @param  idMembresia ID de la membresía
     * @return Lista de suscripciones activas
     * @throws NegocioException si falla la consulta
     */
    public abstract List<SuscripcionDTO> obtenerActivasPorMembresia(String idMembresia) throws NegocioException;

    /**
     * Verifica si un usuario tiene alguna suscripción activa.
     * @param  idUsuario ID del usuario
     * @return true si tiene suscripción activa
     * @throws NegocioException si falla la consulta
     */
    public abstract Boolean verificarSuscripcionActiva(String idUsuario) throws NegocioException;
    
    /**
     * Obtiene la suscripción activa de un usuario.
     * @param  idUsuario ID del usuario
     * @return SuscripcionDTO activa o null si no tiene
     * @throws NegocioException si falla la conslta
     */
    public abstract SuscripcionDTO obtenerActivaPorUsuario(String idUsuario) throws NegocioException;
    
    /**
     * Registra una nueva suscripción con estado ACTIVA.
     * @param  nuevaSuscripcionDTO DTO con idUsuario, idMembresia, fechas, precio y estado
     * @return SuscripcionDTO registrada
     * @throws NegocioException si falla la persistencia
     */
    public abstract SuscripcionDTO registrar(NuevaSuscripcionDTO nuevaSuscripcionDTO) throws NegocioException;
    
    /**
     * Agrega la suscripción al historial embebido del documento.
     * @param suscripcionDTO DTO de la suscripción a historizar
     * @throws NegocioException si falla la insercción 
     */
    public abstract void guardarEnHistorial(SuscripcionDTO suscripcionDTO) throws NegocioException;
    
    /**
     * Obtiene la membresía activa de un Usuario.
     * @param  idUsuario ID del usuario
     * @return MembresiaDTO activa del usuario o null si no tiene
     * @throws NegocioException si falla la consulta
     */
    public abstract MembresiaDTO obtenerMembresiaActivaDeUsuario(String idUsuario) throws NegocioException;
    
}
