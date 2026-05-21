/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inicializador;

import BOS.IMembresiaBO;
import BOS.IPeriodoMembresiaBO;
import BOS.IReporteMembresiaBO;
import BOS.ISuscripcionBO;
import BOS.IUsuarioBO;
import FabricaBO.FabricaBO;
import ControlMembresias.ControlMembresias;
import com.mycompany.fitlifegym_gestionmembresias.FuncionalidadGestionMembresias;
import com.mycompany.fitlifegym_gestionmembresias.FuncionalidadReportesMembresias;
import com.mycompany.fitlifegym_gestionmembresias.IFuncionalidadGestionMembresias;
import com.mycompany.fitlifegym_gestionmembresias.IFuncionalidadReportesMembresias;
import com.mycompany.fitlifegym_login.FuncionalidadLogin;
import com.mycompany.fitlifegym_login.IFuncionalidadLogin;
import com.mycompany.fitlifegym_seleccionarmembresia.FuncionalidadConfirmarSuscripcion;
import com.mycompany.fitlifegym_seleccionarmembresia.FuncionalidadSeleccionarMembresia;
import com.mycompany.fitlifegym_seleccionarmembresia.IFuncionalidadConfirmarSuscripcion;
import com.mycompany.fitlifegym_seleccionarmembresia.IFuncionalidadSeleccionarMembresia;


/**
 * Clase encargada de inicializar y ensamblar todos los componentes del sistem.
 * 
 * Sigue el patrón:
 * FabricaDAO → FabricaFachada → FabricaBO → InicializadorMembresias → ControlMembresias
 * 
 * @author Jaime
 */
public class InicializadorMembresias {
 
    /**
     * 1. Crea los BOs usando FabricaBO, que internamente usa FabricaFachada
     *    y FabricaDAO para construir toda la cadena de persistencia.
     * 2. Crea cada caso de uso (CU) inyectándole los BOs que necesita.
     * 3. Crea el ControlMembresias inyectándole todos los BOs y CUs.
     * 
     * @return ControlMembresias listo para arrancar la primera pantalla
     */
    public static ControlMembresias iniciar() {
        IMembresiaBO membresiaBO = FabricaBO.crearMembresiaBO();
        ISuscripcionBO suscripcionBO = FabricaBO.crearSuscripcionBO();
        IPeriodoMembresiaBO periodoBO = FabricaBO.crearPeriodoMembresiaBO();
        IReporteMembresiaBO reporteBO = FabricaBO.crearReporteMembresiaBO();
        IUsuarioBO usuarioBO = FabricaBO.crearUsuarioBO();
        
        IFuncionalidadLogin funcLogin = new FuncionalidadLogin(usuarioBO);
        IFuncionalidadSeleccionarMembresia funcSeleccionar = new FuncionalidadSeleccionarMembresia(membresiaBO, suscripcionBO);
        IFuncionalidadConfirmarSuscripcion funcConfirmar = new FuncionalidadConfirmarSuscripcion(suscripcionBO, membresiaBO);
        IFuncionalidadGestionMembresias funcGestion = new FuncionalidadGestionMembresias(membresiaBO, suscripcionBO, periodoBO);
        IFuncionalidadReportesMembresias funcReportes = new FuncionalidadReportesMembresias(reporteBO);
        
        return new ControlMembresias(membresiaBO, suscripcionBO, periodoBO, reporteBO, usuarioBO, funcGestion, funcReportes, funcSeleccionar, funcConfirmar, funcLogin);
    }
    
}
