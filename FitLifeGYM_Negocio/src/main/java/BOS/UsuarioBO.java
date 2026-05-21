/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOS;

import Adapter.DtosAEntidadesAdapter;
import Fachada.Fachada;
import Fachada.IFachada;
import com.mycompany.fitlifegym_dtos.NuevoUsuarioDTO;
import com.mycompany.fitlifegym_dtos.UsuarioDTO;
import DAOS.IUsuarioDAO;
import DAOS.PersistenciaException;
import Entidades.Usuario;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class UsuarioBO implements IUsuarioBO{

    private final IFachada fachada;

    /**
     * @param fachada Fachada de persistencia dada por FabricaBO
     */
    public UsuarioBO(IFachada fachada) {
        this.fachada = fachada;
    }

    /**
     * Obtiene un usuario por su ID.
     * @param  idUsuario ID del usuario
     * @return UsuarioDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    @Override
    public UsuarioDTO obtenerPorId(String idUsuario) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarUsuario(fachada.obtenerUsuarioPorId(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener usuario por ID", e);
        }
    }

    /**
     * Busca un usuario por su nombre.
     * @param  nombre Nombre del usuario
     * @return UsuarioDTO o null si no existe
     * @throws NegocioException si falla la consulta
     */
    @Override
    public UsuarioDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarUsuario(fachada.buscarUsuarioPorNombre(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por nombre", e);
        }
    }

    /**
     * Valida que el nombre y contraseña coincidan en la base de datos.
     * @param  nombre Nombre del usuario
     * @param  contrasenia Contraseña del usuario
     * @return true si las credenciales son correctas
     * @throws NegocioException si falla la persistencia
     */
    @Override
    public Boolean validarCredenciales(String nombre, String contrasenia) throws NegocioException {
        try {
            return fachada.validarCredenciales(nombre, contrasenia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar credenciales", e);
        }
    }

    /**
     * Registra un nuevo usuario verificando que el nombre no exista.
     * Asigna la fechaRegistro con la fecha actual.
     * @param  nuevoUsuarioDTO DTO con nombre, contraseña y rol
     * @return UsuarioDTO registrado
     * @throws NegocioException si el nombre ya existe o falla la persistencia
     */
    @Override
    public UsuarioDTO registrar(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException {
        try {
            Usuario existente = fachada.buscarUsuarioPorNombre(nuevoUsuarioDTO.getNombre());
            if (existente != null) {
                throw new NegocioException("Ya existe un usuario con ese nombre");
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(nuevoUsuarioDTO.getNombre());
            usuario.setContrasenia(nuevoUsuarioDTO.getContrasenia());
            usuario.setRol(nuevoUsuarioDTO.getRol());
            usuario.setFechaRegistro(LocalDate.now());
            
            return DtosAEntidadesAdapter.adaptarUsuario(fachada.guardarUsuario(usuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el usuario", e);
        }
    }
    
}
