/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_login;

import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.IUsuarioBO;
import BOS.NegocioException;



/**
 *
 * @author Jaime
 */
public class FuncionalidadLogin implements IFuncionalidadLogin{

   private final IUsuarioBO usuarioBO;

    public FuncionalidadLogin(IUsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

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
    
}
