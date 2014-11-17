/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package euchreserver;

/**
 *
 * @author Benjamin
 */
public class wrapperForQueue
{
    private boolean dealt = false;
    private int player;
    private Object o;
    
    public wrapperForQueue(boolean dealt, int player, Object o)
    {
        this.o = o;
        this.player = player;
        this.dealt = dealt;
    }
    
    public Object getObject()
    {
        return o;
    }
     
    public boolean getDealtWith()
    {
        return dealt;
    }
}
