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
    
    /**
     * Busca un usuario por su ID.
     * @param  idUsuario ID del Usuario
     * @return Usuario encontrado o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Usuario obtenerPorId(String idUsuario) throws PersistenciaException;
    
    /**
     * Busca un usuario por su nombre.
     * @param  nombre Nombre del usuario
     * @return Usuario encontrado o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Usuario buscarPorNombre(String nombre) throws PersistenciaException;
    
    /**
     * Inserta un nuevo usuario y
     * asigna fechaRegistro con la fecha actual.
     * @param  usuario Entidad con nombre, contraseña y rol
     * @return Usuario guardado
     * @throws PersistenciaException si falla la inserción
     */
    public abstract Usuario guardar(Usuario usuario) throws PersistenciaException;
    
    /**
     * Verifica si existe un usuario con ese nombre y contraseña.
     * @param  nombre Nombre del usuario
     * @param  contrasenia Contraseña del usuario
     * @return true si las credenciales son correctas
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Boolean validarCredenciales(String nombre, String contrasenia) throws PersistenciaException;
    
}
