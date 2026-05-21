/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Entidades.HistorialSuscripcion;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ISuscripcionDAO {
    
    /**
     * Inserta una nueva suscripción y asigna estado ACTIVA automáticamente.
     * @param  suscripcion Entidad con idUsuario, idMembresia, fechas, estado y precio
     * @return Suscripción guardada 
     * @throws PersistenciaException si falla la inserción
     */
    public abstract Suscripcion guardar(Suscripcion suscripcion) throws PersistenciaException;
    
    /**
     * Busca la suscripción activa de un usuario.
     * @param  idUsuario ID del usuario
     * @return Suscripción activa o null si no tiene
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Suscripcion obtenerActivaPorUsuario(String idUsuario) throws PersistenciaException;
    
    /**
     * Obtiene todas las suscripciones de una membresía específica.
     * @param  idMembresia ID de la membresía
     * @return Lista de suscripciones de esa membresía
     * @throws PersistenciaException si falla la consulta
     */
    public abstract List<Suscripcion> obtenerPorMembresia(String idMembresia) throws PersistenciaException;
    
    /**
     * Cuenta cuántas suscripciones activas tiene una membresía.
     * @param  idMembresia ID de la membresía
     * @return Número de suscripciones activas
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Integer contarActivasPorMembresia(String idMembresia) throws PersistenciaException;
    
    /**
     * Verifica si existe una suscripción activa para un usuario y membresía.
     * @param  idUsuario ID del usuario
     * @param  idMembresia ID de la membresía
     * @return true si existe una suscripción activa
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Boolean existeActiva(String idUsuario, String idMembresia) throws PersistenciaException;
    
    /**
     * Agrega un Array "historial" embebido en el documento de suscripción.
     * @param  idUsuario ID del usuario dueño de la suscripción
     * @param  historial Array con membresía, fechas, precio y estado
     * @return Suscripción actualizada con el nuevo historial
     * @throws PersistenciaException si falla la actualización
     */
    public abstract Suscripcion agregarAlHistorial(String idUsuario,HistorialSuscripcion historial) throws PersistenciaException;
    
    /**
     * Obtiene suscripciones cuya fechaInicio y fechaVencimiento
     * caen dentro del rango indicado.
     * @param  fechaInicio Inicio del rango de búsqueda
     * @param  fechaFin Fin del rango de búsqueda
     * @return Lista de suscripciones en ese periodo
     * @throws PersistenciaException si falla la conexión
     */
    public abstract List<Suscripcion> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}
