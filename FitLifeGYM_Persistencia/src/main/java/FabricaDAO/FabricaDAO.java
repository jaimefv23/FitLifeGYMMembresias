/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FabricaDAO;

import Fachada.Fachada;
import Fachada.IFachada;
import DAOS.IMembresiaDAO;
import DAOS.ISuscripcionDAO;
import DAOS.IUsuarioDAO;
import DAOS.MembresiaDAO;
import DAOS.SuscripcionDAO;
import DAOS.UsuarioDAO;

/**
 *
 * @author Jaime
 */
public class FabricaDAO {
    
    /**
     * Crea una nueva instancia de MembresiaDAO.
     * @return IMembresiaDAO listo para usar
     */
    public static IMembresiaDAO crearMembresiaDAO() {
        return new MembresiaDAO();
    }

    /**
     * Crea una nueva instancia de SuscripcionDAO.
     * @return ISuscripcionDAO listo para usar
     */
    public static ISuscripcionDAO crearSuscripcionDAO() {
        return new SuscripcionDAO();
    }
    
    /**
     * Crea una nueva instancia de UsuarioDAO.
     * @return IUsuarioDAO listo para usar
     */
    public static IUsuarioDAO crearUsuarioDAO() {
        return new UsuarioDAO();
    }
}
