/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author cavca
 */
public class ListaP implements Serializable{
    ArrayList<Words> list=new ArrayList<Words>();
    public ListaP(){
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
    
        }
    }

    public ArrayList<Words> getList() {
        return list;
    }

    public void setList(ArrayList<Words> list) {
        this.list = list;
    }
    
    
}
