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
    static ObjectInputStream in;
    static ObjectOutputStream out;
    static TCPTransfer transfer;
    static Words w;
    static Intento intento;
    static Cliente cli;

    public Cliente() {
        try{
            cl = new Socket("127.0.0.1",SERVER_REQUEST_PORT);
            in = new ObjectInputStream(cl.getInputStream());
            out = new ObjectOutputStream(cl.getOutputStream());
            transfer = new TCPTransfer(out,in);
            w = transfer.getWord();
            intento = new Intento(w.getAdivinado());
            System.out.println(intento.adivinado);
            Game gm = new Game(cli);
        }
        catch(Exception e){
            
        }
    }
    
    public static void main(String args[]) {
        cli = new Cliente();
    }

    public Socket getCl() {
        return cl;
    }

    public void setCl(Socket cl) {
        this.cl = cl;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public TCPTransfer getTransfer() {
        return transfer;
    }

    public void setTransfer(TCPTransfer transfer) {
        this.transfer = transfer;
    }

    public Words getW() {
        return w;
    }

    public void setW(Words w) {
        this.w = w;
    }

    public Intento getIntento() {
        return intento;
    }

    public void setIntento(Intento intento) {
        this.intento = intento;
    }    
}
