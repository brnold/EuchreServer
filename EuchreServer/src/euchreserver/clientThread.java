/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author brnold
 */
public class clientThread implements Runnable
{
    private Socket s;
    
    
    public clientThread(Socket s)
    {
        this.s = s;
    }
    
    @Override
    public void run() 
    {
      try{
      InputStream input = s.getInputStream();
      ObjectInputStream objectin = new ObjectInputStream(input);
      
      Object o = objectin.readObject();
      
      if(o instanceof java.lang.String)
      {
         //it's the person's name
      }
      
      if(o instanceof Pile)
      {
          //deal with pile
      }
      
      
      }catch(Exception e)
      {
          System.out.println(e);
      }
      
      
    }
    
}
