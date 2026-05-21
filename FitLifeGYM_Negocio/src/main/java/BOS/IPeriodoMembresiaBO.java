/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public interface IPeriodoMembresiaBO {
    
    /**
     * Obtiene el periodo embebido de una membresía.
     * @param  idMembresia ID de la membresía
     * @return PeriodoMembresiaDTO o null si no tiene periodo
     * @throws NegocioException si falla la consulta
     */
    public abstract PeriodoMembresiaDTO obtenerPorMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Verifica si el periodo de una membresía es vigente (hoy entre fechaInicio y fechaFin).
     * @param  idMembresia ID de la membresía
     * @return true si el periodo es vigente
     * @throws NegocioException si falla la consulta
     */
    public abstract Boolean verificarVigente(String idMembresia) throws NegocioException;

    /**
     * Crea un nuevo periodo embebido en la membresía.
     * @param  periodoMembresiaDTO DTO con idMembresia, fechaInicio y fechaFin
     * @return PeriodoMembresiaDTO creado
     * @throws NegocioException si falla la insercción
     */
    public abstract PeriodoMembresiaDTO crear(PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException;
    
    /**
     * Edita el periodo embebido de una membresía.
     * @param  idMembresia ID de la membresía
     * @param  periodoMembresiaDTO DTO con las nuevas fechas
     * @return PeriodoMembresiaDTO actualizado
     * @throws NegocioException si falla la actualización
     */
    public abstract PeriodoMembresiaDTO editar(String idMembresia, PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException;
    
    /**
     * Valida que fechaFin sea posterior a fechaInicio y que ambas no sean nulas.
     * @param  fechaInicio Fecha de inicio del periodo
     * @param  fechaFin Fecha de fin del periodo
     * @return true si el rango es válido
     * @throws NegocioException si las fechas son nulas
     */
    public abstract Boolean validarRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;
    
    
}
