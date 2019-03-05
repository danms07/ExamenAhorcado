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
import static utils.Codes.*;
import utils.Intento;
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
                
                palabra = (int)(Math.random()*palabras.size());
                
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
                }while(intento.nErrores<4 && !intento.adivinado.equals(palabras.get(palabra).getPalabra()));
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
        ArrayList<Words> list=new ArrayList();
        try{
            File f = new File(".\\src\\utils\\listaPalabras");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            Words w;
            String linea = br.readLine();
            while(linea!=null && !"".equals(linea)){
                w = new Words(linea,list.size());
                System.out.println(linea);
                list.add(w);
                linea= br.readLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(list.size());
        return list;
    }
}
