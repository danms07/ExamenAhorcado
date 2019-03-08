/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ahorcado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static utils.Codes.SERVER_REQUEST_PORT;
import utils.Conexion;
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
    Conexion con;

    public Cliente() {
            JFrame frame = new JFrame("Dificultad");
            frame.setLayout(null);
            frame.setSize(300,220);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JLabel tex1 = new JLabel("Nombre");
            JTextField tex2 = new JTextField("");
            JLabel tex3 = new JLabel("Edad");
            JTextField tex4 = new JTextField("");
            JLabel tex5 = new JLabel("Dificultad");
            JTextField tex6 = new JTextField("");
            
            JButton bot1 = new JButton("Enviar");
            
//            tex1.setEnabled(false);
//            tex3.setEnabled(false);
//            tex5.setEnabled(false);
                    
            tex1.setSize(70,30);
            tex2.setSize(170,30);
            tex3.setSize(70,30);
            tex4.setSize(70,30);
            tex5.setSize(70,30);
            tex6.setSize(70,30);
            bot1.setSize(100,30);
            
            tex1.setLocation(10,10);
            tex2.setLocation(100,10);
            tex3.setLocation(10,50);
            tex4.setLocation(100,50);
            tex5.setLocation(50,90);
            tex6.setLocation(140,90);
            bot1.setLocation(100,130);
            
            frame.add(tex1);
            frame.add(tex2);
            frame.add(tex3);
            frame.add(tex4);
            frame.add(tex5);
            frame.add(tex6);
            frame.add(bot1);
            
            frame.setVisible(true);
            
            bot1.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        cl = new Socket("127.0.0.1",SERVER_REQUEST_PORT);
                        in = new ObjectInputStream(cl.getInputStream());
                        out = new ObjectOutputStream(cl.getOutputStream());
                        transfer = new TCPTransfer(out,in);
                        con = new Conexion(tex2.getText(),Integer.parseInt(tex4.getText()),Integer.parseInt(tex6.getText()));
                        frame.dispose();
                        w = transfer.getWord(con);
                        intento = new Intento(w.getAdivinado());
                        System.out.println(intento.adivinado);

                        Game gm = new Game(cli);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                
            });

            
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
