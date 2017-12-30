/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author felip
 */

import basedatos.*;
import modelo.*;
import java.util.ArrayList;
import java.sql.*;

public class Registro {
    
    public static boolean agregarPelicula(Pelicula p){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "INSERT INTO PELICULA VALUES (?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            ps.setInt(1, p.getCodigo());
            ps.setInt(2, p.getPrecio());
            ps.setInt(3, p.getIdCategoria());
            ps.setString(4, p.getFormato4k());
            ps.setString(5, p.getNombre());
            
            ps.executeUpdate();
            
            cc.cerrar();
            return true;
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al agregar pelicula -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar pelicula -- " + e.getMessage());
            return false;
        }
    }
    
    public static boolean eliminarPelicula(int c){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "DELETE FROM PELICULA WHERE "
                    + "codigo='" + c + "'";
            
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            return (ps.executeUpdate()>0);

        } catch (SQLException se) {
            System.out.println("ErrorSQL al eliminar pelicula -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar pelicula -- " + e.getMessage());
            return false;
        }
    }
    
    public static boolean modificarPelicula(Pelicula p){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "UPDATE PELICULA SET "
                    + "precio=?,"
                    + "id_categoria=?,"
                    + "formato4k=?,"
                    + "nombre=?"
                    + "WHERE codigo=" + p.getCodigo();
            
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            ps.setInt(1, p.getPrecio());
            ps.setInt(2, p.getIdCategoria());
            ps.setString(3, p.getFormato4k());
            ps.setString(4, p.getNombre());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al actualizar pelicula -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al actualizar pelicula -- " + e.getMessage());
            return false;
        }
    }
    
    public static Pelicula buscarPelicula(int codigo){
        
        Pelicula p = new Pelicula();
        try {
            
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM PELICULA WHERE codigo='" + codigo + "'";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                p = new Pelicula();
                p.setCodigo(rs.getInt("codigo"));
                p.setPrecio(rs.getInt("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setFormato4k(rs.getString("formato4k"));
                p.setNombre(rs.getString("nombre"));
            }
            cc.cerrar();
        } catch (SQLException se) {
            System.out.println("ErrorSQL al buscar pelicula -- " + se.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al buscar pelicula -- " + e.getMessage());
        }    
        
        return p;
    }
    
    public static ArrayList<Pelicula> buscarPelicula(String cadena){
        
        ArrayList<Pelicula> lp = new ArrayList<>();
        Pelicula p;
        try {
            
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM PELICULA WHERE nombre LIKE '%" + cadena + "%'";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                p = new Pelicula();
                p.setCodigo(rs.getInt("codigo"));
                p.setPrecio(rs.getInt("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setFormato4k(rs.getString("formato4k"));
                p.setNombre(rs.getString("nombre"));
                lp.add(p);
            }
            cc.cerrar();
        } catch (SQLException se) {
            System.out.println("ErrorSQL al buscar pelicula por nombre -- " + se.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al buscar pelicula por nombre -- " + e.getMessage());
        }    
        
        return lp;
    }
    
    public static ArrayList<Pelicula> buscarPeliculaPorCategoria(int id_cat){
        
        ArrayList<Pelicula> listado = new ArrayList<>();
            
        Pelicula p;
        
        try {
            
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM PELICULA WHERE id_categoria=" + id_cat;
            Statement stm = cn.createStatement();
            
            ResultSet rs = stm.executeQuery(consulta);
            
            while(rs.next()){
                p = new Pelicula();
                p.setCodigo(rs.getInt("codigo"));
                p.setPrecio(rs.getInt("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setFormato4k(rs.getString("formato4k"));
                p.setNombre(rs.getString("nombre"));
                
                if(listado.add(p)){
                    System.out.println("Registro listado exitosamente");
                } else {
                    System.out.println("Error al listar el registro");
                }
            }
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al listar peliculas -- " + se.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al listar peliculas -- " + e.getMessage());
            
        }
        return listado;
    }

    public static ArrayList<Pelicula> buscarPeliculaPorCategoria(String cadena){
        
        ArrayList<Pelicula> listado = new ArrayList<>();
        
        Pelicula p;
        
        try {
            
            int id_cat = Registro.buscarCategoria(cadena).getId();
            
            listado = Registro.buscarPeliculaPorCategoria(id_cat);
            
            /*
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM PELICULA WHERE id_categoria=" + id_cat + "ORDER BY codigo ";
            Statement stm = cn.createStatement();
            
            ResultSet rs = stm.executeQuery(consulta);
            
            while(rs.next()){
                p = new Pelicula();
                p.setCodigo(rs.getInt("codigo"));
                p.setPrecio(rs.getInt("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setFormato4k(rs.getString("formato4k"));
                p.setNombre(rs.getString("nombre"));
                
                if(listado.add(p)){
                    System.out.println("Registro listado exitosamente");
                } else {
                    System.out.println("Error al listar el registro");
                }
            }
            */
            
        } catch (Exception e) {
            System.out.println("Error al listar peliculas -- " + e.getMessage());
            
        }
        return listado;
    }
    
    public static ArrayList<Pelicula> listarPeliculas(){
        
        ArrayList<Pelicula> listado = new ArrayList<>();
            
        Pelicula p;
        
        try {
            
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM PELICULA ORDER BY codigo";
            Statement stm = cn.createStatement();
            
            ResultSet rs = stm.executeQuery(consulta);
            
            while(rs.next()){
                p = new Pelicula();
                p.setCodigo(rs.getInt("codigo"));
                p.setPrecio(rs.getInt("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setFormato4k(rs.getString("formato4k"));
                p.setNombre(rs.getString("nombre"));
                
                if(listado.add(p)){
                    System.out.println("Registro listado exitosamente");
                } else {
                    System.out.println("Error al listar el registro");
                }
            }
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al listar peliculas -- " + se.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al listar peliculas -- " + e.getMessage());
            
        }
        return listado;
    }
    
    public static boolean peliculaExiste(int c){
        try {
            if(String.valueOf(buscarPelicula(c).getCodigo()).equals("")){
                System.out.println("Codigo no existe");
                return false;
            } else {
                System.out.println("Codigo existente");
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al rastrear codigo -- " + e.getMessage());
            return false;
        }
    }
    
    public static boolean categoriaExiste(int id){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT id FROM CATEGORIA WHERE id=" + id ;
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();
            
            return rs.next();
        
        } catch (SQLException se) {
            System.out.println("ErrorSQL al verificar categoria -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al verificar categoria -- " + e.getMessage());
            return false;
        }
    }
    
    public static ArrayList<Categoria> listarCategoria(){
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        Categoria c;
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM CATEGORIA ORDER BY descripcion";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                listadoCategorias.add(c);
            }
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al listar categoria -- " + se.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar categoria -- " + e.getMessage());
        }
        
        return listadoCategorias;
    }
    
    public static ArrayList<Categoria> listarCategoriaId(){
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        Categoria c;
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT * FROM CATEGORIA ORDER BY id";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                listadoCategorias.add(c);
            }
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al listar categoria -- " + se.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar categoria -- " + e.getMessage());
        }
        
        return listadoCategorias;
    }
    
    public static Categoria buscarCategoria(String d){
        Categoria c = new Categoria();
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT id FROM CATEGORIA WHERE descripcion='" + d + "'";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescripcion(d);
            }
            
            cc.cerrar();
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al buscar categoria -- " + se.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al buscar categoria -- " + e.getMessage());
            
        }
        return c;
    }
    
    public static Categoria buscarCategoria(int id){
        Categoria c = new Categoria();
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "SELECT descripcion FROM CATEGORIA WHERE id='" + id + "'";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                c = new Categoria();
                c.setId(id);
                c.setDescripcion(rs.getString("descripcion"));
            }
            
            cc.cerrar();
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al buscar categoria -- " + se.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar categoria -- " + e.getMessage());
        }
        return c;
    }
    
    public static boolean agregarCategoria(Categoria cat){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "INSERT INTO CATEGORIA VALUES (?,?)";
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            ps.setInt(1, cat.getId());
            ps.setString(2, cat.getDescripcion());
            
            ps.executeUpdate();
            
            cc.cerrar();
            return true;
            
        } catch (SQLException se) {
            System.out.println("ErrorSQL al agregar categoria -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar categoria -- " + e.getMessage());
            return false;
        }
    }
    
    public static boolean eliminarCategoria(int c){
        try {
            Conexion cc = new Conexion();
            cc.conectar();
            Connection cn = cc.getConnection();
            
            String consulta = "DELETE FROM CATEGORIA WHERE "
                    + "id='" + c + "'";
            
            PreparedStatement ps = cn.prepareStatement(consulta);
            
            return (ps.executeUpdate()>0);

        } catch (SQLException se) {
            System.out.println("ErrorSQL al eliminar categoria -- " + se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar categoria -- " + e.getMessage());
            return false;
        }
    }
}
