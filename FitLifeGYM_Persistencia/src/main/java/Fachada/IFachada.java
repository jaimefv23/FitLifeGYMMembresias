/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import DAOS.PersistenciaException;
import Entidades.HistorialSuscripcionesMembresias;
import Entidades.Membresia;
import Entidades.PeriodoMembresia;
import Entidades.Suscripcion;
import Entidades.Usuario;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IFachada {
    
    // Membresia
    public abstract List<Membresia> obtenerTodas() throws PersistenciaException;
    public abstract List<Membresia> obtenerActivas() throws PersistenciaException;
    public abstract Membresia obtenerMembresiaPorId(Long idMembresia) throws PersistenciaException;
    public abstract Membresia guardarMembresia(Membresia membresia) throws PersistenciaException;
    public abstract Membresia editarMembresia(Membresia membresia) throws PersistenciaException;
    public abstract Boolean eliminarMembresia(Long idMembresia) throws PersistenciaException;

    // Periodo
    public abstract PeriodoMembresia obtenerPeriodoPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract PeriodoMembresia guardarPeriodo(Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract PeriodoMembresia editarPeriodo(Long idPeriodo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract Boolean estaVigente(Long idMembresia) throws PersistenciaException;

    // Suscripcion
    public abstract Integer contarActivasPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract List<Suscripcion> obtenerSuscripcionesPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract Suscripcion obtenerSuscripcionActivaPorUsuario(Long idUsuario) throws PersistenciaException;
    public abstract Boolean existeSuscripcionActiva(Long idUsuario, Long idMembresia) throws PersistenciaException;
    public abstract Suscripcion guardarSuscripcion(Suscripcion suscripcion) throws PersistenciaException;

    // Historial
    public abstract List<HistorialSuscripcionesMembresias> obtenerTodoHistorial() throws PersistenciaException;
    public abstract List<HistorialSuscripcionesMembresias> obtenerHistorialPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract List<HistorialSuscripcionesMembresias> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract HistorialSuscripcionesMembresias guardarHistorial(HistorialSuscripcionesMembresias historial) throws PersistenciaException;

    // Usuario
    public abstract Usuario obtenerUsuarioPorId(Long idUsuario) throws PersistenciaException;
    public abstract Usuario buscarUsuarioPorNombre(String nombre) throws PersistenciaException;
    public abstract Usuario guardarUsuario(Usuario usuario) throws PersistenciaException;
    public abstract Boolean validarCredenciales(String nombre, String contrasenia) throws PersistenciaException;
}
