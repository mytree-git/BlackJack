/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackcc;

/**
 *
 * @author mytree
 */
public class Card {
    private Suit suit;
    private int faceValue;
    private int gameValue;
    private boolean isAce=false;
    private String cardSprite = "";
    
    public Card(int value, Suit suit){
        this.suit = suit;
        this.faceValue = value;
        
        switch (value){
            case 1:
                this.gameValue=11;
                this.isAce=true;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                this.gameValue=10;
                break;
            default:
                this.gameValue=value;
        }
        
        cardSprite += "cardPictures/card";
        if(this.suit == Suit.CLUB){
            cardSprite += "Clubs";
        }
        else if(this.suit == Suit.DIAMONDS){
            cardSprite += "Diamonds";
        }
        else if(this.suit == Suit.HEART){
            cardSprite += "Hearts";
        }
        else if(this.suit == Suit.SPADE){
            cardSprite += "Spades";
        }
        
        if(this.faceValue == 1){
            cardSprite += "A";
        }
        else if(this.faceValue == 11){
            cardSprite += "J";
        }
        else if(this.faceValue == 12){
            cardSprite += "Q";
        }
        else if(this.faceValue == 13){
            cardSprite += "K";
        }
        else{
            cardSprite += Integer.toString(this.faceValue);
        }
        cardSprite += ".png";
    }

    public String getCardSprite() {
        return cardSprite;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        switch (this.faceValue){
            case 1:
                sb.append("Ace");
                break;
            case 11:
                sb.append("Jack");
                break;
            case 12:
                sb.append("Queen");
                break;
            case 13:
                sb.append("King");
                break;
            default:
                sb.append(this.faceValue);
        }
        sb.append(" of "+this.suit.toString());
        return sb.toString();
    }
    
    public Suit getSuit() {
        return suit;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getGameValue() {
        return gameValue;
    }

    public boolean isIsAce() {
        return isAce;
    }
    
    
}
