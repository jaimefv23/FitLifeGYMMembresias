/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Suscripcion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ISuscripcionDAO {
    
    // Suscripcion
    public abstract Integer contarActivasPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract List<Suscripcion> obtenerPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract Suscripcion obtenerActivaPorUsuario(Long idUsuario) throws PersistenciaException;
    public abstract Boolean existeActiva(Long idUsuario, Long idMembresia) throws PersistenciaException;
    public abstract Suscripcion guardar(Suscripcion suscripcion) throws PersistenciaException;

    // Historial 
    public abstract List<HistorialSuscripcionesMembresias> obtenerTodoHistorial() throws PersistenciaException;
    public abstract List<HistorialSuscripcionesMembresias> obtenerHistorialPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract List<HistorialSuscripcionesMembresias> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract HistorialSuscripcionesMembresias guardarHistorial(HistorialSuscripcionesMembresias historial) throws PersistenciaException;
    
}
