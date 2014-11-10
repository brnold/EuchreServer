/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author brnold
 */
public class DeckOfCards {
    private List<Card> list;
    
    /**
     * Constructor, creates a deck of 24 cards, shuffled.
     */
    public DeckOfCards()
    {
        Card[] deck = new Card [24];
        int count = 0;
        
        for (Card.Suit suit : Card.Suit.values())
        {
            for (Card.Face face : Card.Face.values())
            {
                deck[count] = new Card(face, suit);
                ++count;
            }
        }
        
        list = Arrays.asList( deck);
        Collections.shuffle(list);
    }
    
    /**
     * NEEDS TEST, returns and removes the first 5 cards from the list.
     * @return a List of 5 cards
     */
    public List<Card> getFiveCards()
    {
        List temp = list.subList(0, 4);
        for(int i = 0; i <5; i++)
        list.remove(1);
        return temp;
    }
    
    /**
     * basically toString
     */
    public void printCards()
    {
        for(int i = 0; i < list.size(); i++)
            System.out.printf("%-19s%s", list.get(i), ( ( i+1 )% 4 == 0) ? "\n" : "");
       // for(int i = 0; i < list.size(); i++)
         //   System.out.println(list.get(i));
    }
    
  
}
