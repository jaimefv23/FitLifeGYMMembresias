/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import com.mycompany.fitlifegym_dtos.NuevoUsuarioDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;

/**
 *
 * @author Jaime
 */
public interface IUsuarioBO {
    
    public abstract UsuarioDTO obtenerPorId(Long idUsuario) throws NegocioException;
    public abstract UsuarioDTO buscarPorNombre(String nombre) throws NegocioException;
    public abstract Boolean validarCredenciales(String correo, String contrasenia) throws NegocioException;
    public abstract UsuarioDTO registrar(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException;
    
}
