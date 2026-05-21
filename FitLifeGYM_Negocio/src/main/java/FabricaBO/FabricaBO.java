/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FabricaBO;

import Fachada.IFachada;
import BOS.IMembresiaBO;
import BOS.IPeriodoMembresiaBO;
import BOS.IReporteMembresiaBO;
import BOS.ISuscripcionBO;
import BOS.IUsuarioBO;
import BOS.MembresiaBO;
import BOS.PeriodoMembresiaBO;
import BOS.ReporteMembresiaBO;
import BOS.SuscripcionBO;
import BOS.UsuarioBO;
import Fachada.FabricaFachada;

/**
 *
 * @author Jaime
 */

/**
 * Fábrica que usa FabricaFachada para crear cada BO
 * con su fachada ya dada.
 */
public class FabricaBO {
    
    /**
     * Crea un MembresiaBO con su fachada inyectada.
     * @return IMembresiaBO listo para usar
     */
    public static IMembresiaBO crearMembresiaBO() {
        return new MembresiaBO(FabricaFachada.crearFachada());
    }

    /**
     * Crea un SuscripcionBO con su fachada inyectada.
     * @return ISuscripcionBO listo para usar
     */
    public static ISuscripcionBO crearSuscripcionBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new SuscripcionBO(fachada);
    }

    /**
     * Crea un PeriodoMembresiaBO con su fachada inyectada.
     * @return IPeriodoMembresiaBO listo para usar
     */
    public static IPeriodoMembresiaBO crearPeriodoMembresiaBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new PeriodoMembresiaBO(fachada);
    }

    /**
     * Crea un ReporteMembresiaBO con su fachada inyectada.
     * @return IReporteMembresiaBO listo para usar
     */
    public static IReporteMembresiaBO crearReporteMembresiaBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new ReporteMembresiaBO(fachada);
    }

    /**
     * Crea un UsuarioBO con su fachada inyectada.
     * @return IUsuarioBO listo para usar
     */
    public static IUsuarioBO crearUsuarioBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new UsuarioBO(fachada);
    }
    
    
}
