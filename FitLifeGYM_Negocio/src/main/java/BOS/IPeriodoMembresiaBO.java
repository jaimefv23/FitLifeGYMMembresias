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
    
     // Admin y Cliente
    public abstract PeriodoMembresiaDTO obtenerPorMembresia(Long idMembresia) throws NegocioException;
    public abstract Boolean verificarVigente(Long idMembresia) throws NegocioException;

    // Admin
    public abstract PeriodoMembresiaDTO crear(PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException;
    public abstract PeriodoMembresiaDTO editar(Long idPeriodo, PeriodoMembresiaDTO periodoMembresiaDTO) throws NegocioException;
    public abstract Boolean validarRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;
    
}
