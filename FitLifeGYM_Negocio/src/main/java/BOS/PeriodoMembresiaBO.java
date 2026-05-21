    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import DAOS.PersistenciaException;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class PeriodoMembresiaBO implements IPeriodoMembresiaBO{

    private final IFachada fachada;

    /**
     * @param fachada Fachada de persistencia dada por FabricaBO
     */
    public PeriodoMembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }

    /**
     * Obtiene el periodo embebido de una membresía.
     * @param  idMembresia ID de la membresía
     * @return PeriodoMembresiaDTO o null si no tiene periodo
     * @throws NegocioException si falla la consulta
     */
    @Override
    public PeriodoMembresiaDTO obtenerPorMembresia(String idMembresia) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(fachada.obtenerPeriodoPorMembresia(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener período de membresía", e);
        }
    }

    /**
     * Verifica si el periodo de una membresía es vigente (hoy entre fechaInicio y fechaFin).
     * @param  idMembresia ID de la membresía
     * @return true si el periodo es vigente
     * @throws NegocioException si falla la consulta
     */
    @Override
    public Boolean verificarVigente(String idMembresia) throws NegocioException {
        try {
            return fachada.estaVigente(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar vigencia", e);
        }
    }

    /**
     * Crea un nuevo periodo embebido en la membresía.
     * @param  periodoMembresiaDTO DTO con idMembresia, fechaInicio y fechaFin
     * @return PeriodoMembresiaDTO creado
     * @throws NegocioException si falla la insercción
     */
    @Override
    public PeriodoMembresiaDTO crear(PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(fachada.guardarPeriodo(
                            periodoMembresiaDTO.getIdMembresia(),
                            periodoMembresiaDTO.getFechaInicio(),
                            periodoMembresiaDTO.getFechaFin()
                    ));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear el período", e);
        }
    }

    
    /**
     * Edita el periodo embebido de una membresía.
     * @param  idMembresia ID de la membresía
     * @param  periodoMembresiaDTO DTO con las nuevas fechas
     * @return PeriodoMembresiaDTO actualizado
     * @throws NegocioException si falla la actualización
     */
    @Override
    public PeriodoMembresiaDTO editar(String idMembresia, PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(fachada.editarPeriodo(
                            idMembresia,
                            periodoMembresiaDTO.getFechaInicio(),
                            periodoMembresiaDTO.getFechaFin()
                    ));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar el período", e);
        }
    }

    /**
     * Valida que fechaFin sea posterior a fechaInicio y que ambas no sean nulas.
     * @param  fechaInicio Fecha de inicio del periodo
     * @param  fechaFin Fecha de fin del periodo
     * @return true si el rango es válido
     * @throws NegocioException si las fechas son nulas
     */
    @Override
    public Boolean validarRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        if (fechaInicio == null || fechaFin == null) 
            return false;
        return fechaFin.isAfter(fechaInicio);
    }

}
