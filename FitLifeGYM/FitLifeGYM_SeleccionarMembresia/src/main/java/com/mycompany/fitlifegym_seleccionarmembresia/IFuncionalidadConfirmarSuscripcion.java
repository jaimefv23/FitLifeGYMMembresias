/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadConfirmarSuscripcion {
    
    public abstract SuscripcionDTO confirmarSuscripcion(NuevaSuscripcionDTO nuevaSuscripcionDTO) throws NegocioException;
    public abstract void cancelarSuscripcion();
    
}
