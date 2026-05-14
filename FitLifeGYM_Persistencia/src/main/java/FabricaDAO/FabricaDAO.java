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
    
    public static IMembresiaDAO crearMembresiaDAO() {
        return new MembresiaDAO();
    }

    public static ISuscripcionDAO crearSuscripcionDAO() {
        return new SuscripcionDAO();
    }

    public static IUsuarioDAO crearUsuarioDAO() {
        return new UsuarioDAO();
    }
}
