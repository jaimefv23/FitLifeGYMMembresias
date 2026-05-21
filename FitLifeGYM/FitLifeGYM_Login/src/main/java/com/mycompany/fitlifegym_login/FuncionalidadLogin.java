/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_login;

import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.IUsuarioBO;
import BOS.NegocioException;
import com.mycompany.fitlifegym_dtos.NuevoUsuarioDTO;



/**
 * Caso de uso que gestiona el acceso al sistema FitLife GYM.
 *
 * @author Jaime
 */
public class FuncionalidadLogin implements IFuncionalidadLogin{

    private final IUsuarioBO usuarioBO;

    /**
     * Construye el caso de uso inyectando el BO de usuario.
     *
     * @param usuarioBO BO que maneja la lógica de negocio de usuarios
     */
    public FuncionalidadLogin(IUsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    /**
     * Autentica a un usuario en el sistema validando su nombre y contraseña.
     * Si las credenciales son correctas devuelve el UsuarioDTO con su información
     * incluyendo el rol, que será usado para navegar a la pantalla correspondiente.
     *
     * @param  loginDTO DTO con el nombre y contraseña del usuario
     * @return UsuarioDTO con la información del usuario autenticado
     * @throws NegocioException si el nombre o contraseña están vacíos, si las credenciales son incorrectas, o si no se encontró el usuario
     */
    @Override
    public UsuarioDTO iniciarSesion(LoginDTO loginDTO) throws NegocioException {
        if (loginDTO.getNombre() == null || loginDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío");
        }
        if (loginDTO.getContrasenia() == null || loginDTO.getContrasenia().trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía");
        }
        Boolean validas = usuarioBO.validarCredenciales(
                loginDTO.getNombre(), loginDTO.getContrasenia());
        if (!validas) {
            throw new NegocioException("Nombre o contraseña incorrectos");
        }
        UsuarioDTO usuario = usuarioBO.buscarPorNombre(loginDTO.getNombre());
        if (usuario == null) {
            throw new NegocioException("No se encontró el usuario");
        }
        return usuario;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Valida que los campos no estén vacíos, que el rol sea válido
     * (ADMIN o CLIENTE) y que no exista ya un usuario con el mismo nombre.
     *
     * @param nuevoUsuarioDTO DTO con el nombre, contraseña y rol del nuevo usuario
     * @return UsuarioDTO con la información del usuario registrado
     * @throws NegocioException si el nombre o contraseña están vacíos, si el rol no es válido, o si ya existe un usuario con ese nombre
     */
    @Override
    public UsuarioDTO registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException {
        if (nuevoUsuarioDTO.getNombre() == null
                || nuevoUsuarioDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío");
        }

        if (nuevoUsuarioDTO.getContrasenia() == null
                || nuevoUsuarioDTO.getContrasenia().trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía");
        }

        if (nuevoUsuarioDTO.getRol() == null
                || nuevoUsuarioDTO.getRol().trim().isEmpty()) {
            throw new NegocioException("El rol es obligatorio");
        }
        if (!"ADMIN".equals(nuevoUsuarioDTO.getRol()) && !"CLIENTE".equals(nuevoUsuarioDTO.getRol())) {
            throw new NegocioException("El rol debe ser ADMIN o CLIENTE");
        }

        UsuarioDTO existente = usuarioBO.buscarPorNombre(nuevoUsuarioDTO.getNombre());
        if (existente != null) {
            throw new NegocioException("Ya existe un usuario con ese nombre");
        }
        
        return usuarioBO.registrar(nuevoUsuarioDTO);
        }
}
