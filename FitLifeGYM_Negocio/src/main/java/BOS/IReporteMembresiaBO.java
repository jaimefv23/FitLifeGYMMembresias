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
    
    public abstract ReporteMembresiaDTO generarReporteGeneral() throws NegocioException;
    public abstract ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException;
    public abstract Integer calcularTotalUsuariosPorMembresia(Long idMembresia) throws NegocioException;
    public abstract Float calcularTotalVentasPorMembresia(Long idMembresia) throws NegocioException;
    public abstract void exportarPDF(ReporteMembresiaDTO reporteMembresiaDTO) throws NegocioException;
    
}
