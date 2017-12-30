/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelo.Categoria;
import modelo.Pelicula;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author felip
 */
public class RegistroIT {
    
    public RegistroIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Inicializando pruebas de integración...");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Pruebas de integración terminadas");
    }
    
    @Before
    public void setUp() {
        System.out.print("Iniciando prueba de metodo ");
    }
    
    @After
    public void tearDown() {
        System.out.println("Terminando prueba");
        
    }

    /**
     * Test of agregarPelicula method, of class Registro.
     */
    @Test
    public void testAgregarPelicula() {
        System.out.println("agregarPelicula");
        Pelicula p = new Pelicula();
        int codPrueba = Math.round((float)(Math.random()*90000))+10000;
        int idCat = Math.round((float)(Math.random()*14))+101;
        p.setCodigo(codPrueba);
        p.setIdCategoria(idCat);
        p.setNombre("PELICULA PRUEBA " + String.valueOf(Double.hashCode(Math.random())).substring(2,7));
        p.setFormato4k("S");
        p.setPrecio(Math.round((float)(Math.random()*9000 + 1000)));
        boolean expResult = true;
        boolean result = Registro.agregarPelicula(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Eliminando " + p.toString());
        Registro.eliminarPelicula(codPrueba);
    }
    
    /**
     * Test of agregarCategoria method, of class Registro.
     */
    @Test
    public void testAgregarCategoria() {
        System.out.println("agregarCategoria");
        Categoria cat = new Categoria();
        int idPrueba = Math.round((float)(Math.random()*800))+200;
        cat.setId(idPrueba);
        cat.setDescripcion("CATEGORIA PRUEBA " + String.valueOf(Double.hashCode(Math.random())).substring(1,4));
        boolean expResult = true;
        boolean result = Registro.agregarCategoria(cat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Eliminando " + cat.toString());
        Registro.eliminarCategoria(idPrueba);
    }

    
    /**
     * Test of buscarPelicula method, of class Registro.
     */
    @Test
    
    public void testBuscarPelicula_String() {
        
        System.out.println("buscarPelicula");
        String cadena;
        cadena = String.valueOf(Double.hashCode(Math.random())).substring(1,5);
        
        ArrayList<Pelicula> lp = new ArrayList<>();
        
        for (int i=0; i < 4; i++){
            Pelicula p = new Pelicula();
            int codPrueba = Math.round((float)(Math.random()*90000))+10000;
            p.setCodigo(codPrueba);
            p.setIdCategoria(Math.round((float)(Math.random()*10))+101);
            p.setNombre("PELICULA PRUEBA "+ cadena + " " + String.valueOf(Double.hashCode(Math.random())).substring(2,7));
            if (Math.random() < 0.5 ){ p.setFormato4k("S"); } else { p.setFormato4k("N"); }
            p.setPrecio(Math.round((float)(Math.random()*9000 + 1000)));
            lp.add(p);
        }
        
        for (Pelicula p: lp){
           System.out.println("Agregando " + p.toString());
           Registro.agregarPelicula(p);
           
        }
        System.out.println("Comparando elementos");
        
        ArrayList<Pelicula> expResult = lp;
        ArrayList<Pelicula> result = Registro.buscarPelicula(cadena);
        assertEquals(expResult, result);
        
        System.out.println("Comparacion exitosa");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
        for (Pelicula p: lp){
           System.out.println("Eliminando " + p.toString());
           Registro.eliminarPelicula(p.getCodigo());
        }
    }
    
    /**
     * Test of buscarPeliculaPorCategoria method, of class Registro.
     */
    @Test
    
    public void testBuscarPeliculaPorCategoria_String() {
        
        System.out.println("buscarPeliculaPorCategoria");
        String cadena = "FICCION";
        String ad = String.valueOf(Double.hashCode(Math.random())).substring(1,5);
        
        ArrayList<Pelicula> lp = new ArrayList<>();
        
        for (int i=0; i < 4; i++){
            Pelicula p = new Pelicula();
            int codPrueba = Math.round((float)(Math.random()*90000))+10000;
            p.setCodigo(codPrueba);
            p.setIdCategoria(104);
            p.setNombre("PELICULA PRUEBA " + ad + " " + String.valueOf(Double.hashCode(Math.random())).substring(2,7));
            p.setFormato4k("S");
            p.setPrecio(Math.round((float)(Math.random()*9000 + 1000)));
            lp.add(p);
        }
        
        for (Pelicula p: lp){
           System.out.println("Agregando " + p.toString());
           Registro.agregarPelicula(p);
        }
        ArrayList<Pelicula> expResult = lp;
                
        ArrayList<Pelicula> result = Registro.buscarPeliculaPorCategoria(cadena);
        
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        for (Pelicula p: lp){
           System.out.println("Eliminando " + p.toString());
           Registro.eliminarPelicula(p.getCodigo());
        }
    }    
    
    /**
     * Test of modificarPelicula method, of class Registro.
     */
    @Test
    
    public void testModificarPelicula() {
        System.out.println("modificarPelicula");
        
        Pelicula p = new Pelicula();
        int codPrueba = Math.round((float)(Math.random()*90000))+10000;
        p.setCodigo(codPrueba);
        p.setIdCategoria(110);
        p.setNombre("PELICULA PRUEBA " + String.valueOf(Double.hashCode(Math.random())).substring(2,7));
        p.setFormato4k("S");
        p.setPrecio(Math.round((float)(Math.random()*9000 + 1000)));
        Registro.agregarPelicula(p);
        
        Pelicula q = p;
        q.setIdCategoria(400);
        
        boolean expResult = false;
        boolean result = Registro.modificarPelicula(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("Eliminando " + p.toString());
        Registro.eliminarPelicula(codPrueba);
    }

    /**
     * Test of eliminarPelicula method, of class Registro.

    
    @Test
    @Ignore
    public void testEliminarPelicula() {
        System.out.println("eliminarPelicula");
        int c = 0;
        boolean expResult = false;
        boolean result = Registro.eliminarPelicula(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    
    /**
     * Test of buscarPeliculaPorCategoria method, of class Registro.

    @Test
    @Ignore
    public void testBuscarPeliculaPorCategoria() {
        System.out.println("buscarPeliculaPorCategoria");
        int id_cat = 0;
        ArrayList<Pelicula> expResult = null;
        ArrayList<Pelicula> result = Registro.buscarPeliculaPorCategoria(id_cat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of listarPeliculas method, of class Registro.

    @Test
    @Ignore
    public void testListarPeliculas() {
        System.out.println("listarPeliculas");
        ArrayList<Pelicula> expResult = null;
        ArrayList<Pelicula> result = Registro.listarPeliculas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of peliculaExiste method, of class Registro.

    @Test
    @Ignore
    public void testPeliculaExiste() {
        System.out.println("peliculaExiste");
        int c = 0;
        boolean expResult = false;
        boolean result = Registro.peliculaExiste(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of categoriaExiste method, of class Registro.

    @Test
    @Ignore
    public void testCategoriaExiste() {
        System.out.println("categoriaExiste");
        int id = 0;
        boolean expResult = false;
        boolean result = Registro.categoriaExiste(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of listarCategoria method, of class Registro.

    @Test
    @Ignore
    public void testListarCategoria() {
        System.out.println("listarCategoria");
        ArrayList<Categoria> expResult = null;
        ArrayList<Categoria> result = Registro.listarCategoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of buscarCategoria method, of class Registro.

    @Test
    @Ignore
    public void testBuscarCategoria_String() {
        System.out.println("buscarCategoria");
        String d = "";
        Categoria expResult = null;
        Categoria result = Registro.buscarCategoria(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of buscarCategoria method, of class Registro.

    @Test
    @Ignore
    public void testBuscarCategoria_int() {
        System.out.println("buscarCategoria");
        int id = 0;
        Categoria expResult = null;
        Categoria result = Registro.buscarCategoria(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of eliminarPelicula method, of class Registro.

    @Test
    public void testEliminarPelicula() {
        System.out.println("eliminarPelicula");
        int c = 0;
        boolean expResult = false;
        boolean result = Registro.eliminarPelicula(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */


    /**
     * Test of buscarPelicula method, of class Registro.
     * 

    @Test
    @Ignore
    public void testBuscarPelicula_int() {
        System.out.println("buscarPelicula");
        
        
        
        int codigo = 0;
        Pelicula expResult = null;
        Pelicula result = Registro.buscarPelicula(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
     */

    
}
