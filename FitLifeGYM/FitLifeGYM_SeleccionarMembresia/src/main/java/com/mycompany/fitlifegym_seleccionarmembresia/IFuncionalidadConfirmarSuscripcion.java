/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadConfirmarSuscripcion {
    
    /**
     * Confirma la suscripción del usuario a la membresía seleccionada.
     * Verifica que no tenga suscripción activa, que la membresía esté activa,
     * registra la suscripción y la guarda en el historial.
     * @param  idMembresia ID de la membresía seleccionada
     * @param  idUsuario ID del usuario que se suscribe
     * @return SuscripcionDTO con los datos de la suscripción registrada
     * @throws NegocioException si ya tiene suscripción activa o la membresía no está disponible
     */
    public abstract SuscripcionDTO confirmarSuscripcion(String idMembresia, String idUsuario) throws NegocioException;
    
    /**
     * Cancela el proceso. La navegación la maneja el ControlMembresias.
     */
    public abstract void cancelarSuscripcion();
    
}
