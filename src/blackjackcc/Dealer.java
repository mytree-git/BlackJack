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
public class Dealer extends Player {
    public Dealer(){
        super();
    }
    
    public Action getDecision(){
        super.calculateTotal();
        
        if (this.total>17){
            return Action.STAND;
        }else{
            return Action.HIT;
        }
    }
}
