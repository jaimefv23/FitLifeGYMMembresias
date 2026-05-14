
package DAOS;

import Entidades.Membresia;
import Entidades.PeriodoMembresia;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IMembresiaDAO {
    
    // Membresia
    public abstract List<Membresia> obtenerTodas() throws PersistenciaException;
    public abstract List<Membresia> obtenerActivas() throws PersistenciaException;
    public abstract Membresia obtenerPorId(Long idMembresia) throws PersistenciaException;
    public abstract Membresia guardar(Membresia membresia) throws PersistenciaException;
    public abstract Membresia editar(Membresia membresia) throws PersistenciaException;
    public abstract Boolean eliminar(Long idMembresia) throws PersistenciaException;

    // PeriodoMembresia 
    public abstract PeriodoMembresia obtenerPeriodoPorMembresia(Long idMembresia) throws PersistenciaException;
    public abstract PeriodoMembresia guardarPeriodo(Long idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract PeriodoMembresia editarPeriodo(Long idPeriodo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    public abstract Boolean estaVigente(Long idMembresia) throws PersistenciaException; 
}
