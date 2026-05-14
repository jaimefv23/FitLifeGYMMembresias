/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fitlifegym_persistencia;

import DAOS.PersistenciaException;
import DAOS.MembresiaDAO;
import Entidades.Estado;
import Entidades.Membresia;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jaime
 */
public class MembresiaDAOTest {
    
    private MembresiaDAO dao;
    private Long idMembresiaCreada;

    @BeforeEach
    public void setUp() {
        dao = new MembresiaDAO();
    }

//    @AfterEach
//    public void tearDown() throws PersistenciaException {
//
//        if (idMembresiaCreada != null) {
//            dao.eliminar(idMembresiaCreada);
//            idMembresiaCreada = null;
//        }
//    }

    @Test
    public void testGuardarMembresia() throws PersistenciaException {
        Membresia m = new Membresia();
        m.setNombre("Membresía Test");
        m.setPrecio(299.99f);
        m.setEstado(Estado.ACTIVA);
        m.setBeneficios(List.of("Acceso a instalaciones", "Vestuarios"));
        m.setFechaCreacion(LocalDate.now());

        Membresia guardada = dao.guardar(m);
        idMembresiaCreada = guardada.getIdMembresia();

        assertNotNull(guardada.getIdMembresia());
        assertEquals("Membresía Test", guardada.getNombre());
        assertEquals(Estado.ACTIVA, guardada.getEstado());
        System.out.println("✔ testGuardarMembresia — ID: " + guardada.getIdMembresia());
    }
    
}
