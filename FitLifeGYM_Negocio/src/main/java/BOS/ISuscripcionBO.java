/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaSuscripcionDTO;
import com.mycompany.fitlifegym_dtos.SuscripcionDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ISuscripcionBO {
    
    // Admin
    public abstract Integer contarActivasPorMembresia(Long idMembresia) throws NegocioException;
    public abstract List<SuscripcionDTO> obtenerActivasPorMembresia(Long idMembresia) throws NegocioException;

    // Cliente
    public abstract Boolean verificarSuscripcionActiva(Long idUsuario) throws NegocioException;
    public abstract SuscripcionDTO obtenerActivaPorUsuario(Long idUsuario) throws NegocioException;
    public abstract SuscripcionDTO registrar(NuevaSuscripcionDTO nuevaSuscripcionDTO) throws NegocioException;
    public abstract void guardarEnHistorial(SuscripcionDTO suscripcionDTO) throws NegocioException;
    public abstract MembresiaDTO obtenerMembresiaActivaDeUsuario(Long idUsuario) throws NegocioException;
    
}
