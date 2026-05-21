/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_gestionmembresias;

import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadGestionMembresias {
    
    /**
     * Obtiene la lista completa de membresías registradas en el sistema.
     *
     * @return lista de todas las membresías como DTOs
     * @throws NegocioException si ocurre un error al obtener las membresías
     */
    public abstract List<MembresiaDTO> listarMembresias() throws NegocioException;
    
    /**
     * Obtiene únicamente las membresías con estado ACTIVA.
     *
     * @return lista de membresías activas como DTOs
     * @throws NegocioException si ocurre un error al obtener las membresías
     */
    public abstract List<MembresiaDTO> listarMembresiasActivas() throws NegocioException;
    
    /**
     * Consulta una membresía específica por su ID.
     *
     * @param  idMembresia ID de la membresia
     * @return MembresiaDTO con la información de la membresía encontrada
     * @throws NegocioException si no se encontró la membresía con el ID dado
     */
    public abstract MembresiaDTO consultarMembresiaPorID(String idMembresia) throws NegocioException;
    
    /**
     * Agrega una nueva membresía validando campos y rango de fechas.
     * @param membresiaDTO datos de la nueva membresía
     * @throws NegocioException si los campos o fechas no son válidos
     */
    public abstract MembresiaDTO agregarMembresia(NuevaMembresiaDTO membresiaDTO) throws NegocioException;
    
    /**
     * Edita una membresía existente validando campos y rango de fechas.
     * @param idMembresia ID de la membresía a editar
     * @param membresiaDTO nuevos datos de la membresía
     * @throws NegocioException si los campos o fechas no son válidos
     */
    public abstract MembresiaDTO editarMembresia(String idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException;
    
    /**
     * Elimina una membresía si no tiene suscripciones activas.
     * @param idMembresia ID de la membresía a eliminar
     * @throws NegocioException si la membresía tiene usuarios activos
     */
    public abstract void eliminarMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Cuenta las suscripciones activas de una membresía.
     * @param idMembresia ID de la membresía
     * @throws NegocioException si ocurre un error al contar
     */
    public abstract Integer contarSuscripcionesActivas(String idMembresia) throws NegocioException;
    
    /**
     * Obtiene el período de vigencia de una membresía.
     * @param idMembresia ID de la membresía
     * @throws NegocioException si ocurre un error al obtener el período
     */
    public abstract PeriodoMembresiaDTO obtenerPeriodoDeMembresia(String idMembresia) throws NegocioException;
    
}
