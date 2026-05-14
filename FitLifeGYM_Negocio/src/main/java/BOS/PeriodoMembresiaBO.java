    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.Fachada;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.PeriodoMembresiaDTO;
import DAOS.IMembresiaDAO;
import DAOS.PersistenciaException;
import Entidades.PeriodoMembresia;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class PeriodoMembresiaBO implements IPeriodoMembresiaBO{

    private final IFachada fachada;

    public PeriodoMembresiaBO(IFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public PeriodoMembresiaDTO obtenerPorMembresia(Long idMembresia) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(
                    fachada.obtenerPeriodoPorMembresia(idMembresia));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener período de membresía", e);
        }
    }

    @Override
    public Boolean verificarVigente(Long idMembresia) throws NegocioException {
        try {
            return fachada.estaVigente(idMembresia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al verificar vigencia", e);
        }
    }

    @Override
    public PeriodoMembresiaDTO crear(PeriodoMembresiaDTO periodoMembresiaDTO)
            throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(
                    fachada.guardarPeriodo(
                            periodoMembresiaDTO.getIdMembresia(),
                            periodoMembresiaDTO.getFechaInicio(),
                            periodoMembresiaDTO.getFechaFin()
                    ));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear el período", e);
        }
    }

    @Override
    public PeriodoMembresiaDTO editar(Long idPeriodo, PeriodoMembresiaDTO periodoMembresiaDTO)
            throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarPeriodo(
                    fachada.editarPeriodo(
                            idPeriodo,
                            periodoMembresiaDTO.getFechaInicio(),
                            periodoMembresiaDTO.getFechaFin()
                    ));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar el período", e);
        }
    }

    @Override
    public Boolean validarRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        if (fechaInicio == null || fechaFin == null) 
            return false;
        return fechaFin.isAfter(fechaInicio);
    }

}
