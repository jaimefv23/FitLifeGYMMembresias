/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;

/**
 *
 * @author Jaime
 */
public interface IReporteMembresiaBO {
    
    /**
     * Genera un reporte con todas las membresías, sus usuarios activos
     * y el total de ventas acumulado.
     * @return ReporteMembresiaDTO con lista de EntradaReporteDTO 
     * @throws NegocioException si falla la persistencia
     */
    public abstract ReporteMembresiaDTO generarReporteGeneral() throws NegocioException;
    
    /**
     * Genera un reporte filtrando suscripciones cuyas fechas caen
     * dentro del rango indicado agrupadas por membresía.
     * @param  filtros DTO con fechaInicio y fechaFin del período
     * @return ReporteMembresiaDTO con entradas por membresía y totales del período
     * @throws NegocioException si falla la persistencia
     */
    public abstract ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException;
    
    /**
     * Calcula el total de usuarios suscritos a una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Número total de usuarios
     * @throws NegocioException si falla la persistencia
     */
    public abstract Integer calcularTotalUsuariosPorMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Calcula el total de ventas generadas por una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Total de ventas como Float
     * @throws NegocioException si falla la persistencia
     */
    public abstract Float calcularTotalVentasPorMembresia(String idMembresia) throws NegocioException;
    
    /**
     * Imprime en consola la información del reporte.
     * @param  reporteMembresiaDTO Reporte a exportar
     * @throws NegocioException si el reporte es nulo
     */
    public abstract void exportarPDF(ReporteMembresiaDTO reporteMembresiaDTO) throws NegocioException;
    
    
}
