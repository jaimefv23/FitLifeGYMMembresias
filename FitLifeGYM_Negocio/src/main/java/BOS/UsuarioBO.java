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

/**
 *
 * @author Jaime
 */
public class UsuarioBO implements IUsuarioBO{

    private final IFachada fachada;

    public UsuarioBO(IFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public UsuarioDTO obtenerPorId(Long idUsuario) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarUsuario(
                    fachada.obtenerUsuarioPorId(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener usuario por ID", e);
        }
    }

    @Override
    public UsuarioDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            return DtosAEntidadesAdapter.adaptarUsuario(
                    fachada.buscarUsuarioPorNombre(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por nombre", e);
        }
    }

    @Override
    public Boolean validarCredenciales(String nombre, String contrasenia) throws NegocioException {
        try {
            return fachada.validarCredenciales(nombre, contrasenia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar credenciales", e);
        }
    }

    @Override
    public UsuarioDTO registrar(NuevoUsuarioDTO nuevoUsuarioDTO) throws NegocioException {
        try {
            Usuario existente = fachada.buscarUsuarioPorNombre(nuevoUsuarioDTO.getNombre());
            if (existente != null) {
                throw new NegocioException("Ya existe un usuario con ese nombre");
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(nuevoUsuarioDTO.getNombre());
            usuario.setCorreo(nuevoUsuarioDTO.getCorreo());
            usuario.setContrasenia(nuevoUsuarioDTO.getContrasenia());
            return DtosAEntidadesAdapter.adaptarUsuario(fachada.guardarUsuario(usuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el usuario", e);
        }
    }
    
}
