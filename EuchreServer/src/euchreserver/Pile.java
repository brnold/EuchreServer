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
public class Pile {
     List<Card> pile;
     boolean[] enableFlag;
     
     public Pile(List<Card> cards, boolean[] enableInfo)
     {
         pile = cards;
         enableFlag = enableInfo;
     }
}
