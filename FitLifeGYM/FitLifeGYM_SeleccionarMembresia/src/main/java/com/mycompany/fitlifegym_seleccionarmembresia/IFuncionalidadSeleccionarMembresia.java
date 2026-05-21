/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadSeleccionarMembresia {
    
    /**
     * Retorna las membresías activas disponibles para suscribirse.
     * @throws NegocioException si no hay membresías disponibles
     */
    public abstract List<MembresiaDTO> obtenerMembresiasDisponibles() throws NegocioException;
    
    /**
     * Selecciona una membresía verificando que exista y esté activa.
     * @param  idMembresia ID de la membresía a seleccionar
     * @throws NegocioException si no existe o no está activa
     */
    public abstract MembresiaDTO seleccionarMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Verifica si una membresía está en estado ACTIVA.
     * @param idMembresia ID de la membresía
     * @return true si está activa, false si no existe o no está activa
     */
    public abstract Boolean verificarEstadoMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Verifica si un usuario tiene suscripción activa y vigente.
     * @param  idUsuario ID del usuario
     * @return true si tiene suscripción vigente, false si no tiene o venció
     */
    public abstract Boolean verificarSuscripcionActivaUsuario(String idUsuario) throws NegocioException;
    
    /**
     * Obtiene la suscripción activa y vigente del usuario.
     * @param  idUsuario ID del usuario
     * @return SuscripcionDTO activa o null si no tiene o venció
     */
    public abstract SuscripcionDTO obtenerSuscripcionActiva(String idUsuario) throws NegocioException;
    
    /**
     * Obtiene la membresía a la que está suscrito actualmente el usuario.
     * @param idUsuario ID del usuario
     * @return MembresiaDTO activa del usuario o null si no tiene
     */
    public abstract MembresiaDTO obtenerMembresiaActivaDeUsuario(String idUsuario) throws NegocioException;
    
}
