/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_gestionmembresias;

import BOS.IReporteMembresiaBO;
import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import BOS.NegocioException;

/**
 * Caso de uso que gestiona la generación de reportes de membresías.
 * @author Jaime
 */
public class FuncionalidadReportesMembresias implements IFuncionalidadReportesMembresias{

    private final IReporteMembresiaBO reporteBO;

    /**
     * Construye el caso de uso inyectando los BOs necesarios.
     *
     * @param reporteBO BO que maneja la lógica de negocio de los reportes de membresias.
     */
    public FuncionalidadReportesMembresias(IReporteMembresiaBO reporteBO) {
        this.reporteBO = reporteBO;
    }
    
    /**
     * Genera un reporte general con todas las membresías y sus suscriptores.
     * @throws NegocioException si ocurre un error al generar el reporte
     */
    @Override
    public ReporteMembresiaDTO generarReporteGeneral() throws NegocioException {
        return reporteBO.generarReporteGeneral();
    }

    /**
     * Genera un reporte filtrado por período de fechas.
     * @param  filtros fechas de inicio y fin del período
     * @throws NegocioException si las fechas son nulas o inválidas
     */
    @Override
    public ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException {
        if (filtros.getFechaInicio() == null || filtros.getFechaFin() == null) {
            throw new NegocioException("Las fechas del período no pueden ser nulas");
        }
        if (!filtros.getFechaFin().isAfter(filtros.getFechaInicio())) {
            throw new NegocioException("La fecha fin debe ser posterior a la fecha inicio");
        }
        return reporteBO.generarReportePorPeriodo(filtros);
    }
    
    /**
     * Exporta un reporte a formato PDF.
     * @param  reporte datos del reporte a exportar
     * @return true si el PDF fue generado correctamente
     * @throws NegocioException si el reporte es nulo
     */
    @Override
    public Boolean exportarReportePDF(ReporteMembresiaDTO reporte) throws NegocioException {
        if (reporte == null) {
            throw new NegocioException("El reporte no puede ser nulo");
        }
        reporteBO.exportarPDF(reporte);
        return true;
    }
    
}
