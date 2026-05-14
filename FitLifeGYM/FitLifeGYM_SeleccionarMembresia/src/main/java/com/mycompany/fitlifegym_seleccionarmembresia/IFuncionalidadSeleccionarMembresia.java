/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_seleccionarmembresia;

import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import BOS.NegocioException;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IFuncionalidadSeleccionarMembresia {
    
    public abstract List<MembresiaDTO> obtenerMembresiasDisponibles() throws NegocioException;
    public abstract MembresiaDTO seleccionarMembresia(Long idMembresia) throws NegocioException;
    public abstract Boolean verificarEstadoMembresia(Long idMembresia) throws NegocioException;
    public abstract Boolean verificarSuscripcionActivaUsuario(Long idUsuario) throws NegocioException;
    public abstract SuscripcionDTO obtenerSuscripcionActiva(Long idUsuario) throws NegocioException;
    
}
