/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ahorcado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;
import static utils.Codes.*;
import utils.Conexion;
import utils.Intento;
import utils.ListaP;
import utils.TCPTransfer;
import utils.Words;

/**
 *
 * @author cavca
 */
public class Servidor {
    
    
    public Servidor(){
        ArrayList<Words> palabras = obtenerPalabras();
        try{
            ServerSocket ss=new ServerSocket(SERVER_REQUEST_PORT);
            int palabra;
            for(;;){
                System.out.println("Preparado para nueva conexión");
                Socket s=ss.accept();
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                TCPTransfer transfer=new TCPTransfer(oos,ois);
                Conexion con = (Conexion)ois.readObject();
                System.out.println("Usuario Conectado\n\tNombre:"+con.getNombre()+"\n\tEdad:"+con.getEdad()+"\n\tDificultad:"+con.getDificultad());
                if(con.getDificultad()==1){
                    do{
                        palabra = (int)(Math.random()*palabras.size());
                    }while(palabras.get(palabra).getPalabra().length()>5);
                }
                else{
                    do{
                        palabra = (int)(Math.random()*palabras.size());
                    }while(palabras.get(palabra).getPalabra().length()<=5);
                }
                
                
                transfer.sendWord(palabras.get(palabra));
                Intento intento;
                do{
                    intento = transfer.getIntento();
                    StringBuilder str;
                    if(palabras.get(palabra).getPalabra().contains(intento.getIntento()+"")){
                        str = new StringBuilder(intento.getAdivinado());
                        for(int i=0;i<palabras.get(palabra).getPalabra().length();i++){
                            if(palabras.get(palabra).getPalabra().charAt(i) == intento.getIntento()){
                                str.setCharAt(i,intento.getIntento());
                            }
                            intento.adivinado = str.toString();
                        }
                    }
                    else{
                        intento.nErrores++;
                    }
                    transfer.sendIntento(intento);
                }while(intento.nErrores<3 && !intento.adivinado.equals(palabras.get(palabra).getPalabra()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String []args){
        Servidor ser = new Servidor();
    }

    private ArrayList<Words> obtenerPalabras() {
        ArrayList<Words> pa = new ArrayList<Words>();
        ListaP lp;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(".\\src\\utils\\objetos.out"));
            lp = (ListaP)in.readObject();
            pa = lp.getList();
            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(pa.size());
        return pa;
    }
}
