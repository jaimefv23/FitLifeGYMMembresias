/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import DAOS.IMembresiaDAO;
import DAOS.ISuscripcionDAO;
import DAOS.IUsuarioDAO;
import DAOS.MembresiaDAO;
import DAOS.PersistenciaException;
import DAOS.SuscripcionDAO;
import DAOS.UsuarioDAO;
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
public class Fachada implements IFachada{

    private IMembresiaDAO membresiaDAO;
    private ISuscripcionDAO suscripcionDAO;
    private IUsuarioDAO usuarioDAO;

    public Fachada(IMembresiaDAO membresiaDAO, ISuscripcionDAO suscripcionDAO, IUsuarioDAO usuarioDAO) {
        this.membresiaDAO = membresiaDAO;
        this.suscripcionDAO = suscripcionDAO;
        this.usuarioDAO = usuarioDAO;
    }

    // Membresia
    @Override 
    public List<Membresia> obtenerTodas() throws PersistenciaException { 
        return membresiaDAO.obtenerTodas(); 
    }
    
    @Override
    public List<Membresia> obtenerActivas() throws PersistenciaException {
        return membresiaDAO.obtenerActivas(); 
    }
    
    @Override
    public Membresia obtenerMembresiaPorId(String id) throws PersistenciaException { 
        return membresiaDAO.obtenerPorId(id); 
    }
    
    @Override 
    public Membresia guardarMembresia(Membresia m) throws PersistenciaException { 
        return membresiaDAO.guardar(m);
    }
    
    @Override 
    public Membresia editarMembresia(Membresia m) throws PersistenciaException { 
        return membresiaDAO.editar(m); 
    }
    
    @Override 
    public Boolean eliminarMembresia(String id) throws PersistenciaException { 
        return membresiaDAO.eliminar(id); 
    }

    // Periodo
    @Override 
    public PeriodoMembresia obtenerPeriodoPorMembresia(String id) throws PersistenciaException { 
        return membresiaDAO.obtenerPeriodoPorMembresia(id); 
    }
    
    @Override 
    public PeriodoMembresia guardarPeriodo(String id, LocalDate fi, LocalDate ff) throws PersistenciaException {
        return membresiaDAO.guardarPeriodo(id, fi, ff);
    }
    
    @Override
    
    public PeriodoMembresia editarPeriodo(String id, LocalDate fi, LocalDate ff) throws PersistenciaException { 
        return membresiaDAO.editarPeriodo(id, fi, ff); 
    }
    
    @Override 
    public Boolean estaVigente(String id) throws PersistenciaException { 
        return membresiaDAO.estaVigente(id); 
    }

    // Suscripcion
    @Override 
    public Integer contarActivasPorMembresia(String id) throws PersistenciaException {
        return suscripcionDAO.contarActivasPorMembresia(id); 
    }
    
    @Override 
    public List<Suscripcion> obtenerSuscripcionesPorMembresia(String id) throws PersistenciaException { 
        return suscripcionDAO.obtenerPorMembresia(id); 
    }
    
    @Override 
    public Suscripcion obtenerSuscripcionActivaPorUsuario(String id) throws PersistenciaException { 
        return suscripcionDAO.obtenerActivaPorUsuario(id); 
    }
    
    @Override 
    public Boolean existeSuscripcionActiva(String idU, String idM) throws PersistenciaException { 
        return suscripcionDAO.existeActiva(idU, idM); 
    }
    
    @Override 
    public Suscripcion guardarSuscripcion(Suscripcion s) throws PersistenciaException { 
        return suscripcionDAO.guardar(s); 
    }

    @Override
    public Suscripcion agregarAlHistorial(String idUsuario, HistorialSuscripcion historial) throws PersistenciaException {
        return suscripcionDAO.agregarAlHistorial(idUsuario, historial);
    }

    @Override
    public List<Suscripcion> obtenerHistorialPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
       return suscripcionDAO.obtenerHistorialPorPeriodo(fechaInicio, fechaFin);
    }

    // Usuario
    @Override 
    public Usuario obtenerUsuarioPorId(String id) throws PersistenciaException { 
        return usuarioDAO.obtenerPorId(id); 
    }
    @Override 
    public Usuario buscarUsuarioPorNombre(String nombre) throws PersistenciaException {
        return usuarioDAO.buscarPorNombre(nombre); 
    }
    @Override 
    public Usuario guardarUsuario(Usuario u) throws PersistenciaException { 
        return usuarioDAO.guardar(u);
    }
    @Override 
    public Boolean validarCredenciales(String n, String c) throws PersistenciaException {
        return usuarioDAO.validarCredenciales(n, c); 
    }

    
}
