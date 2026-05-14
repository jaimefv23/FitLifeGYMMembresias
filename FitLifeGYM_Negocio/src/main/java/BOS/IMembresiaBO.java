/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.EditarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.MembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import Entidades.Membresia;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IMembresiaBO {
    
    // Admin y Cliente
    public abstract List<MembresiaDTO> listarTodas() throws NegocioException;
    public abstract List<MembresiaDTO> listarActivas() throws NegocioException;
    public abstract MembresiaDTO obtenerPorId(Long idMembresia) throws NegocioException;
    public abstract Boolean verificarEstadoMembresia(Long idMembresia) throws NegocioException;

    // Admin
    public abstract MembresiaDTO agregar(NuevaMembresiaDTO nuevaMembresiaDTO) throws NegocioException;
    public abstract MembresiaDTO editar(Long idMembresia, EditarMembresiaDTO membresiaDTO) throws NegocioException;
    public abstract void eliminar(Long idMembresia) throws NegocioException;
    public abstract Boolean validarCampos(String nombre, Float precio, String estado, List<String> beneficios) throws NegocioException;
    public abstract Boolean verificarSuscripcionesActivas(Long idMembresia) throws NegocioException;

}
