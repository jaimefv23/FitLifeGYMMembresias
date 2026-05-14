/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMembresias;

import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.FiltrosReportesDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.IMembresiaBO;
import BOS.IPeriodoMembresiaBO;
import BOS.IReporteMembresiaBO;
import BOS.ISuscripcionBO;
import BOS.IUsuarioBO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_presentacion.PantallaBeneficios;
import com.mycompany.fitlifegym_presentacion.PantallaBienvenida;
import com.mycompany.fitlifegym_presentacion.PantallaIniciarSesion;
import com.mycompany.fitlifegym_presentacion.PantallaSuscripcionExitosa;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class ControlMembresias {
    
    // BOs
    private final IMembresiaBO membresiaBO;
    private final ISuscripcionBO suscripcionBO;
    private final IPeriodoMembresiaBO periodoBO;
    private final IReporteMembresiaBO reporteBO;
    private final IUsuarioBO usuarioBO;

    // Usuario en sesion
    private UsuarioDTO usuarioActual;

    public ControlMembresias(IMembresiaBO membresiaBO,
                              ISuscripcionBO suscripcionBO,
                              IPeriodoMembresiaBO periodoBO,
                              IReporteMembresiaBO reporteBO,
                              IUsuarioBO usuarioBO) {
        this.membresiaBO = membresiaBO;
        this.suscripcionBO = suscripcionBO;
        this.periodoBO = periodoBO;
        this.reporteBO = reporteBO;
        this.usuarioBO = usuarioBO;
    }

    // ══ Navegación ══

    public void mostrarPantallaIniciarSesion() {
        new PantallaIniciarSesion(this).setVisible(true);
    }
    
    public void mostrarPantallaBienvenida(){
        new PantallaBienvenida(this).setVisible(true);
    }
//
//    public void mostrarMenuAdministrativo() {
//        new PantallaMenuAdmin(this).setVisible(true);
//    }
//
//    public void mostrarGestionMembresias() {
//        new PantallaGestionMembresias(this).setVisible(true);
//    }
//
//    public void mostrarCrearMembresia() {
//        new PantallaCrearMembresia(this).setVisible(true);
//    }
//
//    public void mostrarEditarMembresia(Long idMembresia) {
//        new PantallaEditarMembresia(this, idMembresia).setVisible(true);
//    }
//
//    public void mostrarEliminarMembresia(Long idMembresia) {
//        new PantallaEliminarMembresia(this, idMembresia).setVisible(true);
//    }
//
//    public void mostrarReportesMembresia() {
//        new PantallaReportesMembresia(this).setVisible(true);
//    }
//
    
    public void mostrarPantallaBeneficios() {
        new PantallaBeneficios(this).setVisible(true);
    }
//
//    public void mostrarPantallaSeleccionMembresia() {
//        new PantallaSeleccionMembresia(this).setVisible(true);
//    }
//
//    public void mostrarPantallaDetalleMembresia(Long idMembresia) {
//        new PantallaDetalleMembresia(this, idMembresia).setVisible(true);
//    }
//
//    public void mostrarPantallaConfirmarSuscripcion(Long idMembresia) {
//        new PantallaConfirmarSuscripcion(this, idMembresia).setVisible(true);
//    }
//
    public void mostrarPantallaSuscripcionExitosa() {
        new PantallaSuscripcionExitosa(null, true, this).setVisible(true);

    }
//
//    public void mostrarPantallaMembresiaActiva() {
//        new PantallaMembresiaActiva(this).setVisible(true);
//    }
//
//    public void mostrarPantallaExito(String mensaje) {
//        new PantallaExito(this, mensaje).setVisible(true);
//    }
//
//    public void mostrarAlerta(String mensaje) {
//        new PantallaAlerta(this, mensaje).setVisible(true);
//    }

    // ══ Login ══

    public UsuarioDTO iniciarSesion(LoginDTO loginDTO) throws NegocioException {
        if (loginDTO.getNombre() == null || loginDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío");
        }
        if (loginDTO.getContrasenia() == null || loginDTO.getContrasenia().trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía");
        }
        Boolean validas = usuarioBO.validarCredenciales(
                loginDTO.getNombre(), loginDTO.getContrasenia());
        if (!validas) {
            throw new NegocioException("Nombre o contraseña incorrectos");
        }
        this.usuarioActual = usuarioBO.buscarPorNombre(loginDTO.getNombre());
        return this.usuarioActual;
    }

    public void navegarSegunRol(UsuarioDTO usuario) {
        if ("ADMIN".equals(usuario.getRol())) {
//            mostrarMenuAdministrativo();
        } else {
            mostrarPantallaBienvenida();
        }
    }

    public UsuarioDTO getUsuarioActual() {
        return usuarioActual;
    }

    // ══ Membresías — Admin ══

    public List<MembresiaDTO> listarMembresias() throws NegocioException {
        return membresiaBO.listarTodas();
    }

    public List<MembresiaDTO> listarMembresiasActivas() throws NegocioException {
        return membresiaBO.listarActivas();
    }

    public MembresiaDTO consultarMembresiaPorID(Long idMembresia) throws NegocioException {
        return membresiaBO.obtenerPorId(idMembresia);
    }

    public MembresiaDTO agregarMembresia(NuevaMembresiaDTO membresiaDTO) throws NegocioException {
        Boolean camposValidos = membresiaBO.validarCampos(
                membresiaDTO.getNombre(), membresiaDTO.getPrecio(),
                membresiaDTO.getEstado(), membresiaDTO.getBeneficios());
        if (!camposValidos) {
            throw new NegocioException("Los campos de la membresía no son válidos");
        }
        Boolean fechasValidas = periodoBO.validarRangoFechas(
                membresiaDTO.getFechaInicio(), membresiaDTO.getFechaFin());
        if (!fechasValidas) {
            throw new NegocioException("El rango de fechas no es válido");
        }
        return membresiaBO.agregar(membresiaDTO);
    }

    public MembresiaDTO editarMembresia(Long idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException {
        Boolean camposValidos = membresiaBO.validarCampos(
                membresiaDTO.getNombre(), membresiaDTO.getPrecio(),
                membresiaDTO.getEstado(), membresiaDTO.getBeneficios());
        if (!camposValidos) {
            throw new NegocioException("Los campos de la membresía no son válidos");
        }
        Boolean fechasValidas = periodoBO.validarRangoFechas(
                membresiaDTO.getFechaInicio(), membresiaDTO.getFechaFin());
        if (!fechasValidas) {
            throw new NegocioException("El rango de fechas no es válido");
        }
        return membresiaBO.editar(idMembresia, membresiaDTO);
    }

    public void eliminarMembresia(Long idMembresia) throws NegocioException {
        Boolean tieneSuscripciones = membresiaBO.verificarSuscripcionesActivas(idMembresia);
        if (tieneSuscripciones) {
            throw new NegocioException(
                    "No se puede eliminar una membresía con suscripciones activas");
        }
        membresiaBO.eliminar(idMembresia);
    }

    // ══ Reportes — Admin ══

    public ReporteMembresiaDTO generarReporteGeneral() throws NegocioException {
        return reporteBO.generarReporteGeneral();
    }

    public ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException {
        if (filtros.getFechaInicio() == null || filtros.getFechaFin() == null) {
            throw new NegocioException("Las fechas del período no pueden ser nulas");
        }
        if (!filtros.getFechaFin().isAfter(filtros.getFechaInicio())) {
            throw new NegocioException("La fecha fin debe ser posterior a la fecha inicio");
        }
        return reporteBO.generarReportePorPeriodo(filtros);
    }

    public Boolean exportarReportePDF(ReporteMembresiaDTO reporte) throws NegocioException {
        if (reporte == null) {
            throw new NegocioException("El reporte no puede ser nulo");
        }
        reporteBO.exportarPDF(reporte);
        return true;
    }

    // ══ Membresías — Cliente ══

    public MembresiaDTO obtenerMembresiaActivaDeUsuario() throws NegocioException {
        return suscripcionBO.obtenerMembresiaActivaDeUsuario(usuarioActual.getIdUsuario());
    }

    public Boolean verificarEstadoMembresia(Long idMembresia) throws NegocioException {
        return membresiaBO.verificarEstadoMembresia(idMembresia);
    }

    public Boolean verificarSuscripcionActivaUsuario() throws NegocioException {
        return suscripcionBO.verificarSuscripcionActiva(usuarioActual.getIdUsuario());
    }

    public SuscripcionDTO obtenerSuscripcionActiva() throws NegocioException {
        return suscripcionBO.obtenerActivaPorUsuario(usuarioActual.getIdUsuario());
    }

    public SuscripcionDTO confirmarSuscripcion(Long idMembresia) throws NegocioException {
        Boolean yaActiva = suscripcionBO.verificarSuscripcionActiva(
                usuarioActual.getIdUsuario());
        if (yaActiva) {
            throw new NegocioException("El usuario ya tiene una suscripción activa");
        }
        MembresiaDTO membresia = membresiaBO.obtenerPorId(idMembresia);
        NuevaSuscripcionDTO nuevaSuscripcion = new NuevaSuscripcionDTO(
                usuarioActual.getIdUsuario(),
                idMembresia,
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                membresia.getPrecio()
        );
        SuscripcionDTO suscripcion = suscripcionBO.registrar(nuevaSuscripcion);
        suscripcionBO.guardarEnHistorial(suscripcion);
        return suscripcion;
    }

//    public void cancelarSuscripcion() {
//        mostrarPantallaBeneficios();
//    }
    
}
