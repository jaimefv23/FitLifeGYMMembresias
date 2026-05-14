/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Entidades.Usuario;

/**
 *
 * @author Jaime
 */
public interface IUsuarioDAO {
    
    public abstract Usuario obtenerPorId(Long idUsuario) throws PersistenciaException;
    public abstract Usuario buscarPorNombre(String nombre) throws PersistenciaException;
    public abstract Usuario guardar(Usuario usuario) throws PersistenciaException;
    public abstract Boolean validarCredenciales(String correo, String contrasenia) throws PersistenciaException;
    
}
