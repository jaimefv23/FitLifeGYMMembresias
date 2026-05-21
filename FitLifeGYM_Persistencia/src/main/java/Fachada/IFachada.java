/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import DAOS.PersistenciaException;
import Entidades.HistorialSuscripcion;
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
    public abstract Membresia obtenerMembresiaPorId(String idMembresia) throws PersistenciaException;
    public abstract Membresia guardarMembresia(Membresia membresia) throws PersistenciaException;
    public abstract Membresia editarMembresia(Membresia membresia) throws PersistenciaException;
    public abstract Boolean eliminarMembresia(String idMembresia) throws PersistenciaException;

    // Periodo
    public abstract PeriodoMembresia obtenerPeriodoPorMembresia(String idMembresia) throws PersistenciaException;
    public abstract PeriodoMembresia guardarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract PeriodoMembresia editarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract Boolean estaVigente(String idMembresia) throws PersistenciaException;

    // Suscripcion
    public abstract Integer contarActivasPorMembresia(String idMembresia) throws PersistenciaException;
    public abstract List<Suscripcion> obtenerSuscripcionesPorMembresia(String idMembresia) throws PersistenciaException;
    public abstract Suscripcion obtenerSuscripcionActivaPorUsuario(String idUsuario) throws PersistenciaException;
    public abstract Boolean existeSuscripcionActiva(String idUsuario, String idMembresia) throws PersistenciaException;
    public abstract Suscripcion guardarSuscripcion(Suscripcion suscripcion) throws PersistenciaException;
    public abstract Suscripcion agregarAlHistorial(String idUsuario, HistorialSuscripcion historial) throws PersistenciaException;
    public abstract List<Suscripcion> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

    // Usuario
    public abstract Usuario obtenerUsuarioPorId(String idUsuario) throws PersistenciaException;
    public abstract Usuario buscarUsuarioPorNombre(String nombre) throws PersistenciaException;
    public abstract Usuario guardarUsuario(Usuario usuario) throws PersistenciaException;
    public abstract Boolean validarCredenciales(String nombre, String contrasenia) throws PersistenciaException;
}
