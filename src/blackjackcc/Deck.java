/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackcc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author mytree
 */
public class Deck {
    private ArrayList<Card> cardsInDeck = new ArrayList<>();
    
    public Deck(){
        Suit[] suits = {Suit.CLUB,Suit.DIAMONDS,Suit.HEART,Suit.SPADE};
        for (int iSuit = 0; iSuit < suits.length; iSuit++) {
            for (int iValue = 1; iValue < 14; iValue++) {
                this.cardsInDeck.add(new Card(iValue,suits[iSuit]));
            }
        }
        shuffle();
    }
    
    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cardsInDeck, new Random(seed));
    }
    
    public Card drawCard(){
        int lastIndex = cardsInDeck.size()-1;
        Card tempCard = this.cardsInDeck.get(lastIndex);
        this.cardsInDeck.remove(lastIndex);
        return tempCard;
    }
}
