/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import DAOS.PersistenciaException;
import Entidades.Membresia;
import Entidades.Suscripcion;
import com.mycompany.fitlifegym_dtos.EntradaReporteDTO;
import com.mycompany.fitlifegym_dtos.ImagenDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jaime
 */
public class ReporteMembresiaBO implements IReporteMembresiaBO{

    private final IFachada fachada;

    /**
     * @param fachada Fachada de persistencia dada por FabricaBO
     */
    public ReporteMembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }

    /**
     * Genera un reporte con todas las membresías, sus usuarios activos
     * y el total de ventas acumulado.
     * @return ReporteMembresiaDTO con lista de EntradaReporteDTO 
     * @throws NegocioException si falla la persistencia
     */
    @Override
    public ReporteMembresiaDTO generarReporteGeneral() throws NegocioException {
        try {
            List<Membresia> membresias = fachada.obtenerTodas();
            List<EntradaReporteDTO> entradas = new ArrayList<>();
            int totalUsuarios = 0;
            double totalVentas = 0;

            for (Membresia membresia : membresias) {
                List<Suscripcion> suscripciones = fachada.obtenerSuscripcionesPorMembresia(membresia.getIdMembresia());
                int usuariosMembresia = suscripciones.size();
                double ventasMembresia = 0;
                for (Suscripcion s : suscripciones) {
                    ventasMembresia += s.getPrecioPagado();
                }
                totalUsuarios += usuariosMembresia;
                totalVentas += ventasMembresia;

                ImagenDTO imagenDTO = null;
                if (membresia.getImagen() != null) {
                    imagenDTO = new ImagenDTO(
                            membresia.getImagen().getImagen(),
                            membresia.getImagen().getTamanio()
                    );
                }

                entradas.add(new EntradaReporteDTO(
                        membresia.getIdMembresia(),
                        membresia.getNombre(),
                        imagenDTO,
                        usuariosMembresia,
                        ventasMembresia
                ));
            }

            ReporteMembresiaDTO reporte = DtosAEntidadesAdapter.adaptarReporte(totalUsuarios, totalVentas, "GENERAL", null, null);
            reporte.setMembresias(entradas);
            return reporte;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al generar reporte general", e);
        }
    }

    /**
     * Genera un reporte filtrando suscripciones cuyas fechas caen
     * dentro del rango indicado agrupadas por membresía.
     * @param  filtros DTO con fechaInicio y fechaFin del período
     * @return ReporteMembresiaDTO con entradas por membresía y totales del período
     * @throws NegocioException si falla la persistencia
     */
    @Override
    public ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException {
        try {
            List<Suscripcion> suscripcionesPeriodo = fachada.obtenerHistorialPorPeriodo(filtros.getFechaInicio(), filtros.getFechaFin());

            Map<String, List<Suscripcion>> porMembresia = new HashMap<>();
            for (Suscripcion s : suscripcionesPeriodo) {
                porMembresia.computeIfAbsent(s.getIdMembresia(), k -> new ArrayList<>()).add(s);
            }

            List<EntradaReporteDTO> entradas = new ArrayList<>();
            int totalUsuarios = 0;
            double totalVentas = 0;

            for (Map.Entry<String, List<Suscripcion>> entry : porMembresia.entrySet()) {
                String idMembresia = entry.getKey();
                List<Suscripcion> suscripciones = entry.getValue();

                Membresia membresia = fachada.obtenerMembresiaPorId(idMembresia);
                if (membresia == null) 
                    continue;

                int usuariosMembresia = suscripciones.size();
                double ventasMembresia = 0;
                for (Suscripcion s : suscripciones) {
                    ventasMembresia += s.getPrecioPagado();
                }
                totalUsuarios += usuariosMembresia;
                totalVentas += ventasMembresia;

                ImagenDTO imagenDTO = null;
                if (membresia.getImagen() != null) {
                    imagenDTO = new ImagenDTO(
                            membresia.getImagen().getImagen(),
                            membresia.getImagen().getTamanio()
                    );
                }

                entradas.add(new EntradaReporteDTO(
                        idMembresia,
                        membresia.getNombre(),
                        imagenDTO,
                        usuariosMembresia,
                        ventasMembresia
                ));
            }

            ReporteMembresiaDTO reporte = DtosAEntidadesAdapter.adaptarReporte(
                    totalUsuarios, totalVentas, "PERIODO",
                    filtros.getFechaInicio(), filtros.getFechaFin());
            reporte.setMembresias(entradas);
            return reporte;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al generar reporte por período", e);
        }
    }

    /**
     * Calcula el total de usuarios suscritos a una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Número total de usuarios
     * @throws NegocioException si falla la persistencia
     */
    @Override
    public Integer calcularTotalUsuariosPorMembresia(String idMembresia) throws NegocioException {
        try {
            return fachada.obtenerSuscripcionesPorMembresia(idMembresia).size();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular total de usuarios", e);
        }
    }

    /**
     * Calcula el total de ventas generadas por una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Total de ventas como Float
     * @throws NegocioException si falla la persistencia
     */
    @Override
    public Float calcularTotalVentasPorMembresia(String idMembresia) throws NegocioException {
        try {
            List<Suscripcion> suscripciones = fachada.obtenerSuscripcionesPorMembresia(idMembresia);
            float total = 0f;
            for (Suscripcion s : suscripciones) {
                total += s.getPrecioPagado();
            }
            return total;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular total de ventas", e);
        }
    }

    /**
     * Imprime en consola la información del reporte.
     * @param  reporteMembresiaDTO Reporte a exportar
     * @throws NegocioException si el reporte es nulo
     */
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
