/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_gestionmembresias;

import BOS.IMembresiaBO;
import BOS.IPeriodoMembresiaBO;
import BOS.ISuscripcionBO;
import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import java.util.List;


/**
 * Caso de uso que gestiona las operaciones sobre membresías.
 *
 * @author Jaime
 */
public class FuncionalidadGestionMembresias implements IFuncionalidadGestionMembresias{

    private final IMembresiaBO membresiaBO;
    private final ISuscripcionBO suscripcionBO;
    private final IPeriodoMembresiaBO periodoBO;

    /**
     * Construye el caso de uso inyectando los BOs necesarios.
     *
     * @param membresiaBO BO que maneja la lógica de negocio de membresías
     * @param suscripcionBO BO que maneja la lógica de negocio de suscripciones
     * @param periodoBO BO que maneja la lógica de negocio de períodos de membresía
     */
    public FuncionalidadGestionMembresias(IMembresiaBO membresiaBO, ISuscripcionBO suscripcionBO, IPeriodoMembresiaBO periodoBO) {
        this.membresiaBO = membresiaBO;
        this.suscripcionBO = suscripcionBO;
        this.periodoBO = periodoBO;
    }
    
    /**
     * Obtiene la lista completa de membresías registradas en el sistema.
     *
     * @return lista de todas las membresías como DTOs
     * @throws NegocioException si ocurre un error al obtener las membresías
     */
    @Override
    public List<MembresiaDTO> listarMembresias() throws NegocioException {
        return membresiaBO.listarTodas();
    }

    /**
     * Obtiene únicamente las membresías con estado ACTIVA.
     *
     * @return lista de membresías activas como DTOs
     * @throws NegocioException si ocurre un error al obtener las membresías
     */
    @Override
    public List<MembresiaDTO> listarMembresiasActivas() throws NegocioException {
        return membresiaBO.listarActivas();
    }

    /**
     * Consulta una membresía específica por su ID.
     *
     * @param  idMembresia ID de la membresia
     * @return MembresiaDTO con la información de la membresía encontrada
     * @throws NegocioException si no se encontró la membresía con el ID dado
     */
    @Override
    public MembresiaDTO consultarMembresiaPorID(String idMembresia) throws NegocioException {
        MembresiaDTO membresia = membresiaBO.obtenerPorId(idMembresia);
        if (membresia == null) {
            throw new NegocioException("No se encontró la membresía");
        }
        return membresia;
    }

    /**
     * Agrega una nueva membresía validando campos y rango de fechas.
     * @param membresiaDTO datos de la nueva membresía
     * @throws NegocioException si los campos o fechas no son válidos
     */
    @Override
    public MembresiaDTO agregarMembresia(NuevaMembresiaDTO membresiaDTO) throws NegocioException {
        Boolean camposValidos = membresiaBO.validarCampos(membresiaDTO.getNombre(), membresiaDTO.getPrecio(), membresiaDTO.getEstado(), membresiaDTO.getBeneficios());
        if (!camposValidos) {
            throw new NegocioException("Los campos de la membresía no son válidos");
        }
        
        Boolean fechasValidas = periodoBO.validarRangoFechas(membresiaDTO.getFechaInicio(), membresiaDTO.getFechaFin());
        if (!fechasValidas) {
            throw new NegocioException("El rango de fechas no es válido");
        }
        return membresiaBO.agregar(membresiaDTO);
    }

    /**
     * Edita una membresía existente validando campos y rango de fechas.
     * @param idMembresia ID de la membresía a editar
     * @param membresiaDTO nuevos datos de la membresía
     * @throws NegocioException si los campos o fechas no son válidos
     */
    @Override
    public MembresiaDTO editarMembresia(String idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException {
        Boolean camposValidos = membresiaBO.validarCampos(
                membresiaDTO.getNombre(), membresiaDTO.getPrecio(),
                membresiaDTO.getEstado(), membresiaDTO.getBeneficios());
        if (!camposValidos) {
            throw new NegocioException("Los campos de la membresía no son válidos");
        }
        
        Boolean fechasValidas = periodoBO.validarRangoFechas(membresiaDTO.getFechaInicio(), membresiaDTO.getFechaFin());
        if (!fechasValidas) {
            throw new NegocioException("El rango de fechas no es válido");
        }
        return membresiaBO.editar(idMembresia, membresiaDTO);
    }

    /**
     * Elimina una membresía si no tiene suscripciones activas.
     * @param idMembresia ID de la membresía a eliminar
     * @throws NegocioException si la membresía tiene usuarios activos
     */
    @Override
    public void eliminarMembresia(String idMembresia) throws NegocioException {
        Boolean tieneSuscripciones = membresiaBO.verificarSuscripcionesActivas(idMembresia);
        if (tieneSuscripciones) {
            throw new NegocioException("No se puede eliminar. La membresía cuenta con usuarios activos");
        }
        membresiaBO.eliminar(idMembresia);
    }

    /**
     * Cuenta las suscripciones activas de una membresía.
     * @param idMembresia ID de la membresía
     * @throws NegocioException si ocurre un error al contar
     */
    @Override
    public Integer contarSuscripcionesActivas(String idMembresia) throws NegocioException {
        return suscripcionBO.contarActivasPorMembresia(idMembresia);
    }
    
    /**
     * Obtiene el período de vigencia de una membresía.
     * @param idMembresia ID de la membresía
     * @throws NegocioException si ocurre un error al obtener el período
     */
    @Override
    public PeriodoMembresiaDTO obtenerPeriodoDeMembresia(String idMembresia) throws NegocioException {
        PeriodoMembresiaDTO periodo = periodoBO.obtenerPorMembresia(idMembresia);
        return periodo;
    }
    
}
