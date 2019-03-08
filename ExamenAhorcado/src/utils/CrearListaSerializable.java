/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author cavca
 */
public class CrearListaSerializable {
    public static void main(String args[]){
        ListaP lp = new ListaP();
        
        try{
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(".\\src\\utils\\objetos.out"));
            o.writeObject(lp);
            o.close();
        }
        catch(Exception e){
                
        }
    }
}
