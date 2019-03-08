/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.Serializable;

/**
 *
 * @author cavca
 */
public class Conexion implements Serializable{
    String nombre;
    int edad;
    int dificultad;

    public Conexion(String nombre, int Edad, int Dificultad) {
        this.nombre = nombre;
        this.edad = Edad;
        this.dificultad = Dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int Edad) {
        this.edad = Edad;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int Dificultad) {
        this.dificultad = Dificultad;
    }
    
    
}
