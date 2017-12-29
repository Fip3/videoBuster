/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felip
 */
public class VentanaIT {
    
    public VentanaIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of mostrarListado method, of class Ventana.
     */
    @Test
    public void testMostrarListado() {
        System.out.println("mostrarListado");
        Ventana instance = new Ventana();
        instance.mostrarListado();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Ventana.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Ventana.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
