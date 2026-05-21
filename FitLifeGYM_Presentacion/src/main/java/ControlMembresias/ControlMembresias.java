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
import com.mycompany.fitlifegym_dtos.ReporteMembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.IMembresiaBO;
import BOS.IPeriodoMembresiaBO;
import BOS.IReporteMembresiaBO;
import BOS.ISuscripcionBO;
import BOS.IUsuarioBO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.NuevoUsuarioDTO;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import com.mycompany.fitlifegym_gestionmembresias.IFuncionalidadGestionMembresias;
import com.mycompany.fitlifegym_gestionmembresias.IFuncionalidadReportesMembresias;
import com.mycompany.fitlifegym_login.FuncionalidadLogin;
import com.mycompany.fitlifegym_login.IFuncionalidadLogin;
import com.mycompany.fitlifegym_presentacion.PantallaAlerta;
import com.mycompany.fitlifegym_presentacion.PantallaBeneficios;
import com.mycompany.fitlifegym_presentacion.PantallaBienvenida;
import com.mycompany.fitlifegym_presentacion.PantallaConfirmarSuscripcion;
import com.mycompany.fitlifegym_presentacion.PantallaCrearEditarMembresia;
import com.mycompany.fitlifegym_presentacion.PantallaEliminarMembresia;
import com.mycompany.fitlifegym_presentacion.PantallaGestionMembresias;
import com.mycompany.fitlifegym_presentacion.PantallaIniciarSesion;
import com.mycompany.fitlifegym_presentacion.PantallaMenuAdmin;
import com.mycompany.fitlifegym_presentacion.PantallaPeriodosDeReporte;
import com.mycompany.fitlifegym_presentacion.PantallaRegistrarse;
import com.mycompany.fitlifegym_presentacion.PantallaReporteGeneral;
import com.mycompany.fitlifegym_presentacion.PantallaReportePeriodo;
import com.mycompany.fitlifegym_presentacion.PantallaReportesMembresia;
import com.mycompany.fitlifegym_presentacion.PantallaSuscripcionExitosa;
import com.mycompany.fitlifegym_seleccionarmembresia.IFuncionalidadConfirmarSuscripcion;
import com.mycompany.fitlifegym_seleccionarmembresia.IFuncionalidadSeleccionarMembresia;
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
    
    // CU Cliente 
    private final IFuncionalidadSeleccionarMembresia funcionalidadSeleccionar;
    private final IFuncionalidadConfirmarSuscripcion funcionalidadConfirmar;
    
    // CU ADMIN 
    private final IFuncionalidadGestionMembresias funcionalidadGestion;
    private final IFuncionalidadReportesMembresias funcionalidadReportes;
    
    // CU LOGIN
    private final IFuncionalidadLogin funcionalidadLogin; 

    public ControlMembresias(IMembresiaBO membresiaBO, ISuscripcionBO suscripcionBO, IPeriodoMembresiaBO periodoBO, IReporteMembresiaBO reporteBO, IUsuarioBO usuarioBO, IFuncionalidadGestionMembresias funcionalidadGestion, IFuncionalidadReportesMembresias funcionalidadReportes, IFuncionalidadSeleccionarMembresia funcionalidadSeleccionar, IFuncionalidadConfirmarSuscripcion funcionalidadConfirmar, IFuncionalidadLogin funcionalidadLogin) {
        this.membresiaBO = membresiaBO;
        this.suscripcionBO = suscripcionBO;
        this.periodoBO = periodoBO;
        this.reporteBO = reporteBO;
        this.usuarioBO = usuarioBO;
        this.funcionalidadGestion = funcionalidadGestion;
        this.funcionalidadReportes = funcionalidadReportes;
        this.funcionalidadSeleccionar = funcionalidadSeleccionar;
        this.funcionalidadConfirmar = funcionalidadConfirmar;
        this.funcionalidadLogin = funcionalidadLogin;
    }
    
    // Pantallas 

    public void mostrarPantallaRegistrarse() {
        new PantallaRegistrarse(this).setVisible(true);
    }
    
    public void mostrarPantallaIniciarSesion() {
        new PantallaIniciarSesion(this).setVisible(true);
    }
    
    public void mostrarPantallaBienvenida(){
        new PantallaBienvenida(this).setVisible(true);
    }

    public void mostrarMenuAdministrativo() {
        new PantallaMenuAdmin(this).setVisible(true);
    }

    public void mostrarGestionMembresias() {
        new PantallaGestionMembresias(this).setVisible(true);
    }
    
   public void mostrarCrearMembresia() {
        new PantallaCrearEditarMembresia(this, null).setVisible(true);
    }

    public void mostrarEditarMembresia(String idMembresia) {
        new PantallaCrearEditarMembresia(this, idMembresia).setVisible(true);
    }

    public void mostrarEliminarMembresia(String idMembresia, String nombreMembresia) {
        new PantallaEliminarMembresia(null, true, this, idMembresia, nombreMembresia).setVisible(true);
    }

    public void mostrarReportesMembresia() {
        new PantallaReportesMembresia(null, true, this).setVisible(true);
    }

    public void mostrarReporteGeneral() {
        new PantallaReporteGeneral(this).setVisible(true);
    }

    public void mostrarPeriodosDeReporte() {
        new PantallaPeriodosDeReporte(null, true, this).setVisible(true);
    }

    public void mostrarReportePeriodo(ReporteMembresiaDTO reporte, LocalDate fechaInicio, LocalDate fechaFin) {
        new PantallaReportePeriodo(this, reporte, fechaInicio, fechaFin).setVisible(true);
    }
    
    public void mostrarPantallaBeneficios() {
        new PantallaBeneficios(this).setVisible(true);
    }

    public void mostrarPantallaConfirmarSuscripcion(MembresiaDTO membresia) {
        new PantallaConfirmarSuscripcion(null, true, this, membresia).setVisible(true);
    }

    public void mostrarPantallaSuscripcionExitosa() {
        new PantallaSuscripcionExitosa(null, true, this).setVisible(true);

    }

    public void mostrarAlerta(String mensaje) {
        new PantallaAlerta(null, true, mensaje).setVisible(true);
    }

    // == Registro ==
    
    public void registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException {
         funcionalidadLogin.registrarUsuario(nuevoUsuarioDTO);
    }

    // ══ Login ══

    public UsuarioDTO iniciarSesion(LoginDTO loginDTO) throws NegocioException {
        this.usuarioActual = funcionalidadLogin.iniciarSesion(loginDTO);
        return this.usuarioActual;
    }

    public void navegarSegunRol(UsuarioDTO usuario) {
        if ("ADMIN".equals(usuario.getRol())) {
            mostrarMenuAdministrativo();
        } else {
            mostrarPantallaBienvenida();
        }
    }

    public UsuarioDTO getUsuarioActual() {
        return usuarioActual;
    }

    // ══ Membresías — Admin ══

    public List<MembresiaDTO> listarMembresias() throws NegocioException {
        return funcionalidadGestion.listarMembresias();
    }

    public List<MembresiaDTO> listarMembresiasActivas() throws NegocioException {
        return funcionalidadGestion.listarMembresiasActivas();
    }

    public MembresiaDTO consultarMembresiaPorID(String idMembresia) throws NegocioException {
        return funcionalidadGestion.consultarMembresiaPorID(idMembresia);
    }

    public MembresiaDTO agregarMembresia(NuevaMembresiaDTO membresiaDTO) throws NegocioException {
        return funcionalidadGestion.agregarMembresia(membresiaDTO);
    }

    public MembresiaDTO editarMembresia(String idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException {
        return funcionalidadGestion.editarMembresia(idMembresia, membresiaDTO);
    }

    public void eliminarMembresia(String idMembresia) throws NegocioException {
        funcionalidadGestion.eliminarMembresia(idMembresia);
    }
    
    public PeriodoMembresiaDTO obtenerPeriodoDeMembresia(String idMembresia) throws NegocioException {
        return funcionalidadGestion.obtenerPeriodoDeMembresia(idMembresia);
    }
    
    public Integer contarSuscripcionesActivas(String idMembresia) throws NegocioException {
        return funcionalidadGestion.contarSuscripcionesActivas(idMembresia);
    }

    // ══ Reportes — Admin ══

    public ReporteMembresiaDTO generarReporteGeneral() throws NegocioException {
        return funcionalidadReportes.generarReporteGeneral();
    }
    
    public ReporteMembresiaDTO generarReportePorPeriodo(FiltrosReportesDTO filtros) throws NegocioException {
        return funcionalidadReportes.generarReportePorPeriodo(filtros);
    }

    public Boolean exportarReportePDF(ReporteMembresiaDTO reporte) throws NegocioException {
        return funcionalidadReportes.exportarReportePDF(reporte);
    }

    // ══ Membresías — Cliente ══

    public MembresiaDTO obtenerMembresiaActivaDeUsuario() throws NegocioException {
        return funcionalidadSeleccionar.obtenerMembresiaActivaDeUsuario(usuarioActual.getIdUsuario());
    }

    public Boolean verificarSuscripcionActivaUsuario() throws NegocioException {
        return funcionalidadSeleccionar.verificarSuscripcionActivaUsuario(usuarioActual.getIdUsuario());
    }

    public SuscripcionDTO obtenerSuscripcionActiva() throws NegocioException {
        return funcionalidadSeleccionar.obtenerSuscripcionActiva(usuarioActual.getIdUsuario());
    }

    public SuscripcionDTO confirmarSuscripcion(String idMembresia) throws NegocioException {
        return funcionalidadConfirmar.confirmarSuscripcion(idMembresia, usuarioActual.getIdUsuario());
    }

    public void cancelarSuscripcion() {
        funcionalidadConfirmar.cancelarSuscripcion();
    }

    
}
