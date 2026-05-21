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
    
    /**
     * Obtiene un usuario por su ID.
     * @param  idUsuario ID del usuario
     * @return UsuarioDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    public abstract UsuarioDTO obtenerPorId(String idUsuario) throws NegocioException;
    
    /**
     * Busca un usuario por su nombre.
     * @param  nombre Nombre del usuario
     * @return UsuarioDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    public abstract UsuarioDTO buscarPorNombre(String nombre) throws NegocioException;
    
    /**
     * Valida que el nombre y contraseña coincidan en la base de datos.
     * @param  nombre Nombre del usuario
     * @param  contrasenia Contraseña del usuario
     * @return true si las credenciales son correctas
     * @throws NegocioException si falla la persistencia
     */
    public abstract Boolean validarCredenciales(String correo, String contrasenia) throws NegocioException;
    
    /**
     * Registra un nuevo usuario verificando que el nombre no exista.
     * Asigna la fechaRegistro con la fecha actual.
     * @param nuevoUsuarioDTO DTO con nombre, contraseña y rol
     * @return UsuarioDTO registrado
     * @throws NegocioException si el nombre ya existe o falla la persistencia
     */
    public abstract UsuarioDTO registrar(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException;
    
}
