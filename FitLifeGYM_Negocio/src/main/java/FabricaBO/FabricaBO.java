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
public class FabricaBO {
    
    public static IMembresiaBO crearMembresiaBO() {
        return new MembresiaBO(FabricaFachada.crearFachada());
    }

    public static ISuscripcionBO crearSuscripcionBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new SuscripcionBO(fachada);
    }

    public static IPeriodoMembresiaBO crearPeriodoMembresiaBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new PeriodoMembresiaBO(fachada);
    }

    public static IReporteMembresiaBO crearReporteMembresiaBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new ReporteMembresiaBO(fachada);
    }

    public static IUsuarioBO crearUsuarioBO() {
        IFachada fachada = FabricaFachada.crearFachada();
        return new UsuarioBO(fachada);
    }
}
