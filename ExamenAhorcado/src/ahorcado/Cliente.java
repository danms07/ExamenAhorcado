/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ahorcado;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import static utils.Codes.SERVER_REQUEST_PORT;
import utils.Intento;
import utils.TCPTransfer;
import utils.Words;

/**
 *
 * @author cavca
 */
public class Cliente {
    Socket cl;
    ObjectInputStream in;
    ObjectOutputStream out;
    TCPTransfer transfer;
    Words w;
    Intento intento;

    public Cliente() {
        Scanner leer = new Scanner(System.in);
        try{
            cl = new Socket("127.0.0.1",SERVER_REQUEST_PORT);
            in = new ObjectInputStream(cl.getInputStream());
            out = new ObjectOutputStream(cl.getOutputStream());
            transfer = new TCPTransfer(out,in);
            w = transfer.getWord();
            intento = new Intento(w.getAdivinado());
            System.out.println(intento.adivinado);
            
            while(intento.nErrores<4 && intento.adivinado.contains("_")){
                intento.intento = leer.next().charAt(0);

                transfer.sendIntento(intento);
                intento = transfer.getIntento();
                System.out.println(intento.nErrores+"  "+intento.adivinado);
            }
        }
        catch(Exception e){
            
        }
    }
    
    public static void main(String args[]) {
        Cliente cli = new Cliente();
    }
}
