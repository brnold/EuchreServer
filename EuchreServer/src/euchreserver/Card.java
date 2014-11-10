/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchreserver;

/**
 *
 * @author brnold
 */
public class Card {
    public static enum Face { Ace, Nine, Ten, Jack, Queen, King };
    public static enum Suit { Clubs, Diamonds, Hearts, Spades } ;
    
    private final Face face;
    private final Suit suit;
    
    public Card(Face cardFace, Suit cardSuit)
    {
        face = cardFace;
        suit = cardSuit;
    }
    
    public Face getFace()
    {
        return face;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    public String toSting()
    {
        return String.format( "%s of %s", face, suit);
    }
}
