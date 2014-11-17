/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brnold
 */
public class EuchreServer
{

    private ServerSocket server;
    //private LinkedBlockingQueue q;
    private static int numPlayers = 0;
    private DeckOfCards deck;
    private ArrayList<clientThread> players;
    private ArrayList<LinkedBlockingQueue> inQueue, outQueue;
    private LinkedBlockingQueue qIn, qOut; //Might not need this
    wrapperForQueue qFQ;

    /**
     * @param args the command line arguments
     */
    public void EuchreServer(int port)
    {
        System.out.println("Hello World!");
        System.out.println("add to a branch, now lets put it online");
        System.out.println("Well is this going to make it too?");

        players = new ArrayList<>(4);
        inQueue = new ArrayList<>(4);
        outQueue = new ArrayList<>(4);

        listenSocket(port);
        get4Clients();
        //okay, now we have a list of clients, and a queue we can do our game logic
        handleQueue();

        //deck = new DeckOfCards();

        /*
         We are going to need a wrapper class for the queue. Basically a boolean, int and object
         boolean if the main has dealt with it or not, int for which client and object of data
         Long live the generic class
         */
        //Hand hand1;
        // hand1 = new Hand(Arrays.copyOfRange(deck, 0, 3));
    }

    public void listenSocket(int port)
    {
        qIn = new LinkedBlockingQueue();
        qOut = new LinkedBlockingQueue();

        try
        {
            server = new ServerSocket(port); //Open the port

        } catch (IOException e)
        { //port can't be opened
            System.out.println(e);
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }

    }

    public void get4Clients()
    {
        int i = 0;
//while (numPlayers < 5)
        while(i < 4)
        {

            try
            {

                Socket socket = server.accept(); //server.accept returns a client connection

                (new Thread(new clientThread(socket, qIn, qOut, this))).start(); //Starts a client thread

            

            } catch (IOException e)
            {
                System.out.println("Accept failed: ");
                System.exit(-1);
            }
        i++;
        }
    }

    private void echo()
    {
        String s;
        System.out.println("do I get here?");
        try
        {
            String o = (String) qIn.take();
            //if(o instanceof java.lang.String){
            s = (String) o;
            s = "joe you left the server running";
            qOut.put(s);
            // }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public int getPlayerNumber(clientThread cT, LinkedBlockingQueue qIn, LinkedBlockingQueue qOut)
    {
        players.add(cT);
        inQueue.add(qIn);
        outQueue.add(qOut);
        numPlayers++;
        return (numPlayers - 1);
    }

    public void handleQueue()
    {
        //read queue, deal with item. 
        
        while (1 == 1)
        {

            for (int i = 0; i < 4; i++)
            {
                handleOneQueue(i);
            }
        }

    }

    /**
     * Attention Brian, this is where the game logic goes. check what the object
     * type is then deal with it.
     */
    private void handleOneQueue(int i)
    {
        LinkedBlockingQueue qIn = inQueue.get(i), qOut = outQueue.get(i);

        Object o;

        if (qIn.size() > 0)
        {
            try
            {
                o = qIn.take();

                if (o instanceof java.lang.String)
                {
                    //This is a test
                    String s = (String) o + "YAH YAH SERVER GOT THIS FROM " + i;
                    qOut.put(s);
                    outOtherQueues(i, s);
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(EuchreServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        /**
         *
         * @param i this is the same i from the for loop that called deal with
         * one queue
         * @param o This is the object to be put in the queue
         */
    private void outOtherQueues(int i, Object o) throws InterruptedException
    {
        LinkedBlockingQueue qOut; 
        
        if (i != 0)
        {
            qOut = outQueue.get(0);
            qOut.put(o);
        }

        if (i != 1)
        {
            //send oubject out this queue
            qOut = outQueue.get(1);
            qOut.put(o);
        }

        if (i != 2)
        {
            //send oubject out this queue
            qOut = outQueue.get(2);
            qOut.put(o);
        }

        if (i != 3)
        {
            //send oubject out this queue
            qOut = outQueue.get(3);
            qOut.put(o);
        }
    }

}
