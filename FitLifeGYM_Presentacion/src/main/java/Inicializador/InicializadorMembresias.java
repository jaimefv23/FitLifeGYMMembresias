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

/**
 *
 * @author Jaime
 */
public class InicializadorMembresias {
 
    public static ControlMembresias iniciar() {
        IMembresiaBO membresiaBO = FabricaBO.crearMembresiaBO();
        ISuscripcionBO suscripcionBO = FabricaBO.crearSuscripcionBO();
        IPeriodoMembresiaBO periodoBO = FabricaBO.crearPeriodoMembresiaBO();
        IReporteMembresiaBO reporteBO = FabricaBO.crearReporteMembresiaBO();
        IUsuarioBO usuarioBO = FabricaBO.crearUsuarioBO();
        
        return new ControlMembresias(membresiaBO, suscripcionBO, periodoBO, reporteBO, usuarioBO);
    }
    
}
