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
public class BlackJack {
    
    public static Player checkWinner(Player player, Dealer dealer){
        if(player.getTotal() > 21 && dealer.getTotal() <= 21){
            return dealer;
        }
        else if(dealer.getTotal() > 21 && player.getTotal() <= 21){
            return player;
        }
        else if(player.getTotal() > dealer.getTotal()){
            return player;
        }
        else if(player.getTotal() < dealer.getTotal()){
            return dealer;
        }
            
        return null;
    }
    
}
