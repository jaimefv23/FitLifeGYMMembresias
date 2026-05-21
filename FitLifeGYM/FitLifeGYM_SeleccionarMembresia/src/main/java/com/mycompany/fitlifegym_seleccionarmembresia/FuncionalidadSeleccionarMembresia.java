/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import BOS.IMembresiaBO;
import BOS.ISuscripcionBO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;
import java.time.LocalDate;
import java.util.List;


/**
 * Caso de uso que gestiona la selección de membresías disponibles para el cliente.
 * @author Jaime
 */
public class FuncionalidadSeleccionarMembresia implements IFuncionalidadSeleccionarMembresia{

    private final IMembresiaBO membresiaBO;
    private final ISuscripcionBO suscripcionBO;

    /**
     * Construye el caso de uso inyectando las BOs necesarias.
     *
     * @param membresiaBO BO que maneja la lógica de negocio de membresias
     * @param suscripcionBO BO que maneja la lógica de negocio de suscripciones
     */
    public FuncionalidadSeleccionarMembresia(IMembresiaBO membresiaBO, ISuscripcionBO suscripcionBO) {
        this.membresiaBO = membresiaBO;
        this.suscripcionBO = suscripcionBO;
    }

    /**
     * Retorna las membresías activas disponibles para suscribirse.
     * @throws NegocioException si no hay membresías disponibles
     */
    @Override
    public List<MembresiaDTO> obtenerMembresiasDisponibles() throws NegocioException {
        List<MembresiaDTO> activas = membresiaBO.listarActivas();
        if (activas == null || activas.isEmpty()) {
            throw new NegocioException("No hay membresías disponibles en este momento");
        }
        return activas;
    }

    /**
     * Selecciona una membresía verificando que exista y esté activa.
     * @param  idMembresia ID de la membresía a seleccionar
     * @throws NegocioException si no existe o no está activa
     */
    @Override
    public MembresiaDTO seleccionarMembresia(String idMembresia) throws NegocioException {
        MembresiaDTO membresia = membresiaBO.obtenerPorId(idMembresia);
        if (membresia == null) {
            throw new NegocioException("No se encontró la membresía seleccionada");
        }
        if (!"ACTIVA".equals(membresia.getEstado())) {
            throw new NegocioException("La membresía seleccionada no está disponible");
        }
        return membresia;
    }

    /**
     * Verifica si una membresía está en estado ACTIVA.
     * @param idMembresia ID de la membresía
     * @return true si está activa, false si no existe o no está activa
     */
    @Override
    public Boolean verificarEstadoMembresia(String idMembresia) throws NegocioException {
        MembresiaDTO membresia = membresiaBO.obtenerPorId(idMembresia);
        if (membresia == null) 
            return false;
        return "ACTIVA".equals(membresia.getEstado());
    }

    /**
     * Verifica si un usuario tiene suscripción activa y vigente.
     * @param  idUsuario ID del usuario
     * @return true si tiene suscripción vigente, false si no tiene o venció
     */
    @Override
    public Boolean verificarSuscripcionActivaUsuario(String idUsuario) throws NegocioException {
        SuscripcionDTO activa = suscripcionBO.obtenerActivaPorUsuario(idUsuario);
        if (activa == null) 
            return false;
        if (LocalDate.now().isAfter(activa.getFechaVencimiento())) {
            return false;
        }
        return true;
    }

    /**
     * Obtiene la suscripción activa y vigente del usuario.
     * @param  idUsuario ID del usuario
     * @return SuscripcionDTO activa o null si no tiene o venció
     */
    @Override
    public SuscripcionDTO obtenerSuscripcionActiva(String idUsuario) throws NegocioException {
        SuscripcionDTO activa = suscripcionBO.obtenerActivaPorUsuario(idUsuario);
        if (activa == null) 
            return null;
        if (LocalDate.now().isAfter(activa.getFechaVencimiento())) {
            return null;
        }
        return activa;
    }

    /**
     * Obtiene la membresía a la que está suscrito actualmente el usuario.
     * @param idUsuario ID del usuario
     * @return MembresiaDTO activa del usuario o null si no tiene
     */
    @Override
    public MembresiaDTO obtenerMembresiaActivaDeUsuario(String idUsuario) throws NegocioException {
        SuscripcionDTO suscripcion = obtenerSuscripcionActiva(idUsuario);
        if (suscripcion == null)
            return null;
        return membresiaBO.obtenerPorId(suscripcion.getIdMembresia());
    }
}
