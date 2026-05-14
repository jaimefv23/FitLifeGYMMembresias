/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.Fachada;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import DAOS.ISuscripcionDAO;
import DAOS.PersistenciaException;
import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class ReporteMembresiaBO implements IReporteMembresiaBO{

    private final IFachada fachada;

    public ReporteMembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public ReporteMembresiaDTO generarReporteGeneral() throws NegocioException {
        try {
            List<HistorialSuscripcionesMembresias> historial = fachada.obtenerTodoHistorial();
            int totalUsuarios = 0;
            double totalVentas = 0;
            for (HistorialSuscripcionesMembresias h : historial) {
                totalUsuarios += h.getSuscripciones().size();
                totalVentas += h.getPrecioPagado();
            }
            return DtosAEntidadesAdapter.adaptarReporte(
                    totalUsuarios, totalVentas, "GENERAL", null, null);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al generar reporte general", e);
        }
    }

    @Override
    public ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException {
        try {
            List<HistorialSuscripcionesMembresias> historial =
                    fachada.obtenerHistorialPorPeriodo(
                            filtros.getFechaInicio(),
                            filtros.getFechaFin()
                    );
            int totalUsuarios = 0;
            double totalVentas = 0;
            for (HistorialSuscripcionesMembresias h : historial) {
                totalUsuarios += h.getSuscripciones().size();
                totalVentas += h.getPrecioPagado();
            }
            return DtosAEntidadesAdapter.adaptarReporte(
                    totalUsuarios, totalVentas, "PERIODO",
                    filtros.getFechaInicio(), filtros.getFechaFin());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al generar reporte por período", e);
        }
    }

    @Override
    public Integer calcularTotalUsuariosPorMembresia(Long idMembresia) throws NegocioException {
        try {
            return fachada.obtenerSuscripcionesPorMembresia(idMembresia).size();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular total de usuarios", e);
        }
    }

    @Override
    public Float calcularTotalVentasPorMembresia(Long idMembresia) throws NegocioException {
        try {
            List<Suscripcion> suscripciones =
                    fachada.obtenerSuscripcionesPorMembresia(idMembresia);
            float total = 0f;
            for (Suscripcion s : suscripciones) {
                total += s.getPrecioPagado();
            }
            return total;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular total de ventas", e);
        }
    }

    @Override
    public void exportarPDF(ReporteMembresiaDTO reporteMembresiaDTO) throws NegocioException {
        if (reporteMembresiaDTO == null) {
            throw new NegocioException("El reporte no puede ser nulo");
        }
        System.out.println("Generando PDF — Tipo: " + reporteMembresiaDTO.getTipo());
        System.out.println("Total usuarios: " + reporteMembresiaDTO.getTotalUsuarios());
        System.out.println("Total ventas: $" + reporteMembresiaDTO.getTotalVentas());
        System.out.println("Fecha generación: " + reporteMembresiaDTO.getFechaGeneracion());
    }
    
}
