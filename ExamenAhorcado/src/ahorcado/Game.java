/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import utils.Intento;

/**
 *
 * @author dany
 */
public class Game extends JFrame{
    
    JTextField tex[];
    
    static Cliente clien;
    static JLabel horca;
    static int i=0;
    public Game(Cliente cli) {
        
        horca = new JLabel();
        
        horca.setSize(200,250);
        horca.setLocation(50,50);
        
        switch(cli.intento.nErrores){
            case 0:
                horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca0.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                break;
            case 1:
                horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca1.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                break;
            case 2:
                horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca2.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                break;
            case 3:
                Timer timer = new Timer(10000000, new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        switch(i){
                            case 0:
                                horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca3.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
////                                i++;
                                break;
                            case 1:
                                horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca4.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                                i++;
                                break;                            
                            case 2:
                                i++;
                                break;
                            case 3:
                                exit(0);
                                break;
                        }
                    }
                
                });
//                timer.start();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(250+(cli.intento.adivinado.length()*50),400);
        setLocationRelativeTo(null);
        
        JTextField inten = new JTextField();
        JButton bot1 = new JButton("Realizar intento");
        
        tex = new JTextField[cli.intento.getAdivinado().length()];
        for(int i=0;i<tex.length;i++){
            tex[i] = new JTextField(cli.intento.getAdivinado().charAt(i)+"");
            tex[i].setSize(30,50);
            tex[i].setLocation(250+(i*40),100);
            tex[i].setHorizontalAlignment(JTextField.CENTER);
            tex[i].setEditable(false);
            add(tex[i]);
        }
        
        inten.setSize(30,50);
        inten.setLocation((250+(cli.intento.getAdivinado().length()*40)/2),210);
        inten.setHorizontalAlignment(JTextField.CENTER);
        add(inten);
        
        bot1.setSize(150,40);
        bot1.setLocation((250+(cli.intento.getAdivinado().length()*40)/2)-75,270);
        add(bot1);
        
        add(horca);
                
        setVisible(true);
        
        inten.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if(inten.getText().length() == 1){
                    int codigo=e.getKeyChar();
                    if(codigo>=32){
                        e.consume();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        bot1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                if(!inten.getText().isEmpty()){
                    cli.intento.intento=inten.getText().charAt(0);
                    clien = cli;
                    realizarIntento();
                    
                    switch(clien.intento.nErrores){
                        case 0:
                            horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca0.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                            break;
                        case 1:
                            horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca1.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                            break;
                        case 2:
                            horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca2.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                            break;
                        case 3:
                            Timer timer = new Timer(1000, new ActionListener(){
                    
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                switch(i){
                                    case 0:
                                        horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca3.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                                        i++;
                                        break;
                                    case 1:
                                        horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\horca4.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                                        i++;
                                        break;                            
                                    case 2:
                                        i++;
                                        break;
                                    case 3:
                                        exit(0);
                                        break;
                                }
                            }
                        });
                        timer.start();
                    }
                    
                    for(int i=0;i<clien.intento.adivinado.length();i++){
                        tex[i].setText(clien.intento.adivinado.charAt(i)+"");
                    }
                    
                    inten.setText("");
                }
            }
        });
    }
    
    static void realizarIntento() {
        System.out.println(clien.intento.adivinado);
        if(clien.intento.nErrores<4 && clien.intento.adivinado.contains("_")){
            clien.transfer.sendIntento(clien.intento);
            clien.intento = clien.transfer.getIntento();
            System.out.println(clien.intento.nErrores+"  "+clien.intento.adivinado);
            if(clien.intento.nErrores<4 && !clien.intento.adivinado.contains("_")){
                i=0;
                Timer timer = new Timer(1000, new ActionListener(){
                    
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                switch(i){
                                    case 0:
                                        horca.setIcon(new ImageIcon(new ImageIcon(".\\src\\img\\win.png").getImage().getScaledInstance(horca.getWidth(), horca.getHeight(), Image.SCALE_DEFAULT)));
                                        i++;
                                        break;
                                    case 1:
                                        exit(0);
                                        break;
                                }
                            }
                        });
                timer.start();
            }
        }
    }
}
