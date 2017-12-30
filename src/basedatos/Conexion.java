/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author felip
 */
public class Conexion {
    private final String USUARIO = "felipe";
    //Contraseña del usuario de la bd
    private final String PASS = "N4n3rmosa";
    //SID de la bd, este lo registramos en la instalacion
    private final String SID = "XE";
    //Host donde se encuentra la bd
    private final String HOST = "localhost";
    //puerto 1521 es el estandar para este tipo de instalaciones
    private final int PUERTO = 1521;
    //Objeto donde se almacenará la conexión
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    /*
     * Instanciar objeto de tipo OracleDriver para regitrar y posterior uso de él
     * Este objeto lo provee el driver que se agrega al principio
     */
    public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }

    /*
     * Conexion a la bd.para esto nos
     * Asegurar que el objeto este null o que este cerrada la conexion.
     * 
     * cadenaConexion: string que se contruye a partir de los atributos
     * definidos.
     * 
     * Obtener conexion.
     * El metodo "getConnection" lanza excepcion la cual propagamos "throws SQLException".
     */
    public void conectar() throws SQLException {
        if (connection == null || connection.isClosed() == true) {
            String cadenaConexion = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SID;
            registrarDriver();
            connection = DriverManager.getConnection(cadenaConexion, USUARIO, PASS);
           
        }
    }

    /*
     * Método que cierra conexion, una vez hayamos terminado de usar la bd
     */
    public void cerrar() throws SQLException {
        if (connection != null && connection.isClosed() == false) {
            connection.close();
        }
    }

    
}
