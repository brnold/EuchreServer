/*
 * This is the thread class for the handling the client running on the server 
 * Launch me, but keep a reference becuase I can be stopped my calling stop
 */
package euchreserver;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author brnold
 */
public class clientThread implements Runnable
{

    private volatile boolean running = true;
    private Socket s;
    private LinkedBlockingQueue qIn, qOut;
    private Object o;
    
    private int player;
    private EuchreServer serverReference;

    public clientThread(Socket s, LinkedBlockingQueue qIn, LinkedBlockingQueue qOut, EuchreServer serverReference)
    {
        this.s = s;
        this.qIn = qIn;
        this.qOut = qOut;
        this.serverReference = serverReference;
    }

    @Override
    public void run()
    {
        try
        {

            OutputStream output = s.getOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(output);

            InputStream input = s.getInputStream();
            ObjectInputStream objectin = new ObjectInputStream(input);

            int i = 0;
            boolean noObject = true;

            while (i < 50 && noObject)
            { // until we get some data 5ms * 50 = 250ms //Might need more time.

                if (objectin.available() > 0)
                {
                    o = objectin.readObject();
                    
                    
                    if (o instanceof java.lang.String)
                    {
                        //set the name of the client
                        noObject = false;
                        player =  serverReference.getPlayerNumber(this, qIn, qOut);
                        
                        //set player number
                        
                    } else
                    { // this must not be a real client
                        running = false;
                        noObject = false;
                        System.out.println("This wasn't a real client");
                    }

                }
                i++;
                Thread.sleep(5);
            }

            while (running)
            { //#Epic. So when an object comes in I'll put it in the queue,
                //and the main will Peek the object and add it to the top
                //This is its life. Take object, put on queue, take off queue send it out the socket

                if (objectin.available() > 0)
                {
                    o = objectin.readObject();
                    //wrapper = new wrapperForQueue(false, player, o);
                    qIn.put(o);
                    //System.out.println((String) o);
                    Thread.sleep(50); //sleep thread so the main can take the data

                } else if (!qOut.isEmpty())
                {
                    
                    objectOut.writeObject(qOut.take());
                    
                    
                    
                }

            }

            //Need to put in socket close logic
            objectOut.close();
            objectin.close();
            s.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void stop() //Stop da loop
    {
        running = false;
    }

}
