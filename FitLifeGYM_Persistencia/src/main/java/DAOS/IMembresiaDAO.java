
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
    
    // == Membresia == 
    
    /**
     * Obtiene todas las membresías sin importar su estado.
     * @return Lista de membresías
     * @throws PersistenciaException si falla la consulta
     */
    public abstract List<Membresia> obtenerTodas() throws PersistenciaException;
    
    /**
     * Obtiene solo las membresías con estado ACTIVA.
     * @return Lista de membresías activas
     * @throws PersistenciaException si falla la consulta
     */
    public abstract List<Membresia> obtenerActivas() throws PersistenciaException;
    
    /**
     * Busca una membresía por su ObjectId.
     * @param  idMembresia ID de la membresía
     * @return Membresía encontrada o null si no existe
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Membresia obtenerPorId(String idMembresia) throws PersistenciaException;
    
    /**
     * Inserta una nueva membresía. 
     * @param  membresia Entidad con los datos a guardar
     * @return Membresía guardada 
     * @throws PersistenciaException si falla la inserción
     */
    public abstract Membresia guardar(Membresia membresia) throws PersistenciaException;
    
    /**
     * Actualiza nombre, precio, estado y beneficios de una membresía.
     * @param  membresia Entidad con los nuevos datos y el _id ya existente
     * @return Membresía actualizada
     * @throws PersistenciaException si falla la actualización
     */ 
    public abstract Membresia editar(Membresia membresia) throws PersistenciaException;
    
    /**
     * Elimina una membresía por su ID.
     * @param  idMembresia ID de la membresía
     * @return true si se eliminó, false si no se encontró
     * @throws PersistenciaException si falla la eliminación
     */
    public abstract Boolean eliminar(String idMembresia) throws PersistenciaException;

    // == PeriodoMembresia ==
    
    /**
     * Obtiene el periodo embebido dentro del documento de la membresía.
     * @param  idMembresia ID de la membresía
     * @return PeriodoMembresia embebido o null si no tiene
     * @throws PersistenciaException si falla la conexión
     */
    public abstract PeriodoMembresia obtenerPeriodoPorMembresia(String idMembresia) throws PersistenciaException;
    
    /**
     * Guarda un nuevo periodo embebido en la membresía.
     * @param  idMembresia ID de la membresía
     * @param  fechaInicio Fecha de inicio del periodo
     * @param  fechaFin Fecha de fin del periodo
     * @return PeriodoMembresia guardado
     * @throws PersistenciaException si falla la actualización
     */
    public abstract PeriodoMembresia guardarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
    /**
     * Edita el periodo embebido filtrando por el ID de la membresía.
     * Recalcula automáticamente si el periodo es vigente.
     * @param  idMembresia ID de la membresía
     * @param  fechaInicio Nueva fecha de inicio
     * @param  fechaFin Nueva fecha de fin
     * @return PeriodoMembresia actualizado
     * @throws PersistenciaException si falla la actualización
     */
    public abstract PeriodoMembresia editarPeriodo(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
    /**
     * Verifica si la fecha actual está dentro del periodo de la membresía.
     * @param  idMembresia ID de la membresía
     * @return true si hoy está entre fechaInicio y fechaFin
     * @throws PersistenciaException si falla la consulta
     */
    public abstract Boolean estaVigente(String idMembresia) throws PersistenciaException; 
}
