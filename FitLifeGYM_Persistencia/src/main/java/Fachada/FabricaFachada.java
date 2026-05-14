/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import DAOS.IMembresiaDAO;
import DAOS.ISuscripcionDAO;
import DAOS.IUsuarioDAO;
import FabricaDAO.FabricaDAO;

/**
 *
 * @author Jaime
 */
public class FabricaFachada {
   
     public static IFachada crearFachada() {
        IMembresiaDAO membresiaDAO = FabricaDAO.crearMembresiaDAO();
        ISuscripcionDAO suscripcionDAO = FabricaDAO.crearSuscripcionDAO();
        IUsuarioDAO usuarioDAO = FabricaDAO.crearUsuarioDAO();
        
        return new Fachada(membresiaDAO, suscripcionDAO, usuarioDAO);
    }
}
