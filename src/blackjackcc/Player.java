/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackcc;

import java.util.ArrayList;

/**
 *
 * @author mytree
 */
public class Player {
    protected int total;
    protected ArrayList<Card> cardsInHand= new ArrayList<>();
    
    public Player(){
        this.total=0;
    }
    
    public void calculateTotal(){
        this.total=0;
        int aceCounter=0;
        
        for (Card card : cardsInHand) {
            this.total+=card.getGameValue();
            if (card.isIsAce()){
                aceCounter++;
            }
        }
        
        while (this.total>21&&aceCounter>0){
            this.total-=10;
            aceCounter--;
        }     
    }

    public int getTotal() {
        this.total=0;
        int aceCounter=0;
        
        for (Card card : cardsInHand) {
            this.total+=card.getGameValue();
            if (card.isIsAce()){
                aceCounter++;
            }
        }
        
        while (this.total>21&&aceCounter>0){
            this.total-=10;
            aceCounter--;
        }  
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    
    public void addCardToHand(Card toAdd){
        this.cardsInHand.add(toAdd);
    }
}
