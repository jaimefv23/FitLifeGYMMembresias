/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_gestionmembresias;

import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import BOS.NegocioException;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadReportesMembresias {
    
    /**
     * Genera un reporte general con todas las membresías y sus suscriptores.
     * @throws NegocioException si ocurre un error al generar el reporte
     */
    public abstract ReporteMembresiaDTO generarReporteGeneral() throws NegocioException;
    
    /**
     * Genera un reporte filtrado por período de fechas.
     * @param  filtros fechas de inicio y fin del período
     * @throws NegocioException si las fechas son nulas o inválidas
     */
    public abstract ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException;
    
    /**
     * Exporta un reporte a formato PDF.
     * @param  reporte datos del reporte a exportar
     * @return true si el PDF fue generado correctamente
     * @throws NegocioException si el reporte es nulo
     */
    public abstract Boolean exportarReportePDF(ReporteMembresiaDTO reporte) throws NegocioException;
}
