/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arranque;


import Inicializador.InicializadorMembresias;
import ControlMembresias.ControlMembresias;

/**
 *
 * @author Jaime
 */
public class Iniciar {
    
    public static void main(String[] args) {
        ControlMembresias control = InicializadorMembresias.iniciar();
        control.mostrarPantallaIniciarSesion();
    }
}
