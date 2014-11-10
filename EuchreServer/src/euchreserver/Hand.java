/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author brnold
 */
public class Hand {
    
    List<Card> cards;
    
    public Hand(Card[] c)
    {
        cards = Arrays.asList(c);

    }
    
}
