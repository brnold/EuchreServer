/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

/**
 *
 * @author brnold
 */
public class EuchreServer {

    ServerSocket server;
    
    /**
     * @param args the command line arguments
     */
    public void EuchreServer() {
        System.out.println("Hello World!");
        System.out.println("add to a branch, now lets put it online");
        System.out.println("Well is this going to make it too?");
        
        DeckOfCards deck = new DeckOfCards();
        
        
        
        //Hand hand1;
       // hand1 = new Hand(Arrays.copyOfRange(deck, 0, 3));
        
    }
    
    
public void listenSocket(int port){
  try{
    server = new ServerSocket(port);
  } catch (IOException e) {
    System.out.println("Could not listen on port " + port);
    System.exit(-1);
  }
  while(true){
    //ClientWorker w;
    try{
//server.accept returns a client connection
      //w = new ClientWorker(server.accept(), textArea);
      //Start a new client object
        Thread t = new Thread(w);
      t.start();
    } catch (IOException e) {
      System.out.println("Accept failed: " + port);
      System.exit(-1);
    }
  }
}

    
}
