/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.util.List;

/**
 *
 * @author brnold
 */
public class Player {
    List<Card> hand;
    private String name;
    private int id;
    
    public Player(String n, int id, List<Card> hand)
    {
        name = n;
        id = this.id;
        hand = this.hand;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    
}
