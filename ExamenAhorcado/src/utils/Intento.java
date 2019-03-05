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
public class Intento implements Serializable{
    public int nErrores;
    public String adivinado; 
    public char intento;

    public Intento(String adv) {
        nErrores=0;
        adivinado=adv;
    }

    public int getnErrores() {
        return nErrores;
    }

    public void setnErrores(int nErrores) {
        this.nErrores = nErrores;
    }

    public String getAdivinado() {
        return adivinado;
    }

    public void setAdivinado(String adivinado) {
        this.adivinado = adivinado;
    }

    public char getIntento() {
        return intento;
    }

    public void setIntento(char intento) {
        this.intento = intento;
    }
}
