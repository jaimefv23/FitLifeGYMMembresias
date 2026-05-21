/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import BOS.IMembresiaBO;
import BOS.ISuscripcionBO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class FuncionalidadConfirmarSuscripcion implements IFuncionalidadConfirmarSuscripcion{

    private final ISuscripcionBO suscripcionBO;
    private final IMembresiaBO membresiaBO;

    /**
     ** Construye el caso de uso inyectando las BOs necesarias.
     *
     * @param membresiaBO BO que maneja la lógica de negocio de membresias
     * @param suscripcionBO BO que maneja la lógica de negocio de suscripciones
     */
    public FuncionalidadConfirmarSuscripcion(ISuscripcionBO suscripcionBO, IMembresiaBO membresiaBO) {
        this.suscripcionBO = suscripcionBO;
        this.membresiaBO = membresiaBO;
    }

    /**
     * Confirma la suscripción del usuario a la membresía seleccionada.
     * Verifica que no tenga suscripción activa, que la membresía esté activa,
     * registra la suscripción y la guarda en el historial.
     * @param  idMembresia ID de la membresía seleccionada
     * @param  idUsuario ID del usuario que se suscribe
     * @return SuscripcionDTO con los datos de la suscripción registrada
     * @throws NegocioException si ya tiene suscripción activa o la membresía no está disponible
     */
    @Override
    public SuscripcionDTO confirmarSuscripcion(String idMembresia, String idUsuario) throws NegocioException {
        SuscripcionDTO suscripcionExistente = suscripcionBO.obtenerActivaPorUsuario(idUsuario);
        if (suscripcionExistente != null && !LocalDate.now().isAfter(suscripcionExistente.getFechaVencimiento())) {
            throw new NegocioException("El usuario ya tiene una suscripción activa");
        }

        MembresiaDTO membresia = membresiaBO.obtenerPorId(idMembresia);
        if (membresia == null) {
            throw new NegocioException("La membresía no existe");
        }
        
        if (!"ACTIVA".equals(membresia.getEstado())) {
            throw new NegocioException("La membresía seleccionada no está disponible");
        }
     
        NuevaSuscripcionDTO nuevaSuscripcion = new NuevaSuscripcionDTO(
                idUsuario,
                idMembresia,
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                membresia.getPrecio()
        );

        SuscripcionDTO suscripcion = suscripcionBO.registrar(nuevaSuscripcion);
        suscripcionBO.guardarEnHistorial(suscripcion);

        return suscripcion;
    }

    /**
     * Cancela el proceso. La navegación la maneja el ControlMembresias.
     */
    @Override
    public void cancelarSuscripcion() {
    }
    
}
