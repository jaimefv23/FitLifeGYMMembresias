/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_login;

import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import BOS.NegocioException;


/**
 *
 * @author Jaime
 */
public interface IFuncionalidadLogin {
    
    UsuarioDTO iniciarSesion(LoginDTO loginDTO) throws NegocioException;
    
}
