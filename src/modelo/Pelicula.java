/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author felip
 */
public class Pelicula {
    private int codigo;
    private int precio;
    private int idCategoria;
    private String formato4k;
    private String nombre;

    public Pelicula() {
    }

    public Pelicula(int codigo, int precio, int id_categoria, String formato4k, String nombre) {
        this.codigo = codigo;
        this.precio = precio;
        this.idCategoria = id_categoria;
        this.formato4k = formato4k;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getFormato4k() {
        return formato4k;
    }

    public void setFormato4k(String formato4k) {
        this.formato4k = formato4k;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = Double.hashCode(Math.random());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula other = (Pelicula) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.precio != other.precio) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if (!Objects.equals(this.formato4k, other.formato4k)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
}
