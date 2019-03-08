/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author dany
 */
public class Words implements Serializable{
    String palabra;
    String adivinado="";
    int idPalabra;

    public Words(String palabra, int idPalabra) {
        this.palabra = palabra;
        this.idPalabra = idPalabra;
        
        for(int i=0;i<palabra.length();i++){
            if(palabra.charAt(i)==' '){
                adivinado+=" ";
            }
            else{
                adivinado+="_";
            }
        }
    }
    
    

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getAdivinado() {
        return adivinado;
    }

    public void setAdivinado(String adivinado) {
        this.adivinado = adivinado;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }
    
    
}
