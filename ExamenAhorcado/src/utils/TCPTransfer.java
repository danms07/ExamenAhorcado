package utils;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Codes.*;


public class TCPTransfer{
  ObjectInputStream ois;
  ObjectOutputStream oos;
  public TCPTransfer(ObjectOutputStream oos,ObjectInputStream ois){
    this.oos=oos;
    this.ois=ois;
  }

  /**
  *@param f The file to be transfered
  */

  public int sendWord(Words w){
    try{
        oos.writeObject(w);
        oos.flush();
        
        return Codes.SUCCESFULL;

    }
    catch(IOException e){
        return Codes.ERROR;
    }
  }
  /**
  *@param path The path to save the file, if not exists, will be created.
  */
  public Words getWord(){
    try{
        
        return (Words)ois.readObject();
    }
    catch(IOException e){
        e.printStackTrace();
        return null;
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
          return null;
      }
  }
  
  
  
  public ObjectOutputStream getOutput(){
      return oos;
  }
  
  public ObjectInputStream getInput(){
      return ois;
  }

    public int sendIntento(Intento intento) {
        try{
            oos.writeObject(intento);
            oos.flush();

            return Codes.SUCCESFULL;

        }
        catch(IOException e){
            return Codes.ERROR;
        }
    }
    
    public Intento getIntento() {
        try{
            Intento intento = (Intento)ois.readObject();

            return intento;

        }
        catch(IOException e){
            return null;
        } catch (ClassNotFoundException ex) {
          return null;
      }
    }
}


