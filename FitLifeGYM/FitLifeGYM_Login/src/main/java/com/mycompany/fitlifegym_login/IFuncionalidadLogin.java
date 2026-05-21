/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_login;

import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.NuevoUsuarioDTO;


/**
 *
 * @author Jaime
 */
public interface IFuncionalidadLogin {
    
    /**
     * Autentica a un usuario en el sistema validando su nombre y contraseña.
     * Si las credenciales son correctas devuelve el UsuarioDTO con su información
     * incluyendo el rol, que será usado para navegar a la pantalla correspondiente.
     *
     * @param  loginDTO DTO con el nombre y contraseña del usuario
     * @return UsuarioDTO con la información del usuario autenticado
     * @throws NegocioException si el nombre o contraseña están vacíos, si las credenciales son incorrectas, o si no se encontró el usuario
     */
    UsuarioDTO iniciarSesion(LoginDTO loginDTO) throws NegocioException;
    
    /**
     * Registra un nuevo usuario en el sistema.
     * Valida que los campos no estén vacíos, que el rol sea válido
     * (ADMIN o CLIENTE) y que no exista ya un usuario con el mismo nombre.
     *
     * @param nuevoUsuarioDTO DTO con el nombre, contraseña y rol del nuevo usuario
     * @return UsuarioDTO con la información del usuario registrado
     * @throws NegocioException si el nombre o contraseña están vacíos, si el rol no es válido, o si ya existe un usuario con ese nombre
     */
    UsuarioDTO registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException;
    
}
