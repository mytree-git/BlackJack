/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacjackguicc;

import blackjackcc.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mytree
 */
public class BlacJackGUICC extends Application {

    Label titleLbl, dealerTotalLbl, playerTotalLbl;
    Button hitBtn, standBtn;
    HBox dealerCardPane, playerCardPane;

    //Game Fields
    Player player = new Player();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();
    boolean canHit = true;

    @Override
    public void start(Stage primaryStage) {

        titleLbl = new Label("BLACKJACK");
        titleLbl.setId("title");
        playerTotalLbl = new Label(" : Player Total  ");
        dealerTotalLbl = new Label("  Dealer Total : ");
        hitBtn = new Button("Hit");
        standBtn = new Button("Stand");
        playerCardPane = new HBox();
        dealerCardPane = new HBox();

        GridPane mainPane = new GridPane();
        Scene scene = new Scene(mainPane, 300, 380);
        scene.getStylesheets().add("css/style.css");

        mainPane.add(titleLbl, 0, 0, 2, 1);
        mainPane.add(dealerTotalLbl, 0, 1, 2, 1);
        mainPane.add(dealerCardPane, 0, 2, 2, 1);
        mainPane.add(playerCardPane, 0, 3, 2, 1);
        mainPane.add(playerTotalLbl, 0, 4, 2, 1);
        mainPane.add(standBtn, 0, 5, 1, 1);
        mainPane.add(hitBtn, 1, 5, 1, 1);

        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(10));

        hitBtn.setMaxWidth(Double.MAX_VALUE);
        standBtn.setMaxWidth(Double.MAX_VALUE);
        
        dealerCardPane.setPadding(new Insets(10));
        dealerCardPane.setSpacing(10);
        dealerCardPane.setAlignment(Pos.CENTER_RIGHT);

        playerCardPane.setPadding(new Insets(10));
        playerCardPane.setSpacing(10);
        playerCardPane.setAlignment(Pos.CENTER_LEFT);

        startGame();
        
        this.hitBtn.setOnAction(e->{
            if (canHit){
                this.player.addCardToHand(deck.drawCard());
                this.setPlayerCards();
                //bust
                if (this.player.getTotal()>21){
                    //they busted
                    canHit=false;
                    this.playerTotalLbl.setText(player.getTotal()+" : Busted! ");
                    this.dealerTotalLbl.setText(" Won : "+ dealer.getTotal());
                }
                
                //don't bust
                
            }
        });
        
        this.standBtn.setOnAction(e->{
            canHit=false;
           while (dealer.getTotal()<17){
               //draw cards
               dealer.addCardToHand(deck.drawCard());
               this.setDealerCards();
           }
           //check who won
           Player result = BlackJack.checkWinner(player, dealer);
           
           if (result instanceof Player){
               //player won
               this.playerTotalLbl.setText(player.getTotal()+" : Player Won! ");
               this.dealerTotalLbl.setText(" Dealer Lost! : "+dealer.getTotal());
           }else if (result instanceof Dealer){
               //dealer won
               this.playerTotalLbl.setText(player.getTotal()+" : Player Lost! ");
               this.dealerTotalLbl.setText(" Dealer Won! : "+dealer.getTotal());
           }else{
               //draw
               this.playerTotalLbl.setText(player.getTotal()+" : Player Draw! ");
               this.dealerTotalLbl.setText(" Dealer Draw! : "+dealer.getTotal());
           }
        });

        RowConstraints rowSml = new RowConstraints();
        RowConstraints rowLrg = new RowConstraints();
        rowSml.setPercentHeight(13);
        rowLrg.setPercentHeight(23);
        mainPane.getRowConstraints().addAll(rowSml, rowSml, rowLrg, rowLrg, rowSml, rowSml);

        ColumnConstraints colHalf = new ColumnConstraints();
        colHalf.setPercentWidth(50);
        mainPane.getColumnConstraints().addAll(colHalf, colHalf);

        GridPane.setHalignment(titleLbl, HPos.CENTER);
        GridPane.setHalignment(playerTotalLbl, HPos.RIGHT);
        GridPane.setHalignment(hitBtn, HPos.CENTER);
        GridPane.setHalignment(standBtn, HPos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void startGame() {
        deck.shuffle();
        
        player.addCardToHand(deck.drawCard());
        player.addCardToHand(deck.drawCard());

        dealer.addCardToHand(deck.drawCard());
        dealer.addCardToHand(deck.drawCard());

        setPlayerCards();
        setDealerCards();
    }

    private void setPlayerCards() {
        this.playerCardPane.getChildren().clear();
        ArrayList<ImageView> imgs = new ArrayList<>();

        for (int iCard = 0; iCard < this.player.getCardsInHand().size(); iCard++) {
            Image img = new Image(this.player.getCardsInHand().get(iCard).getCardSprite());
            ImageView imgView = new ImageView(img);

            imgView.setFitWidth(44);
            imgView.setPreserveRatio(true);
            imgView.setSmooth(true);
            imgView.setCache(true);
            imgs.add(imgView);
        }
        this.playerCardPane.getChildren().addAll(imgs);
        this.playerTotalLbl.setText(player.getTotal()+" : Player  ");
    }

    private void setDealerCards() {
        this.dealerCardPane.getChildren().clear();
        ArrayList<ImageView> imgs = new ArrayList<>();

        for (int iCard = 0; iCard < this.dealer.getCardsInHand().size(); iCard++) {
            Image img = new Image(this.dealer.getCardsInHand().get(iCard).getCardSprite());
            ImageView imgView = new ImageView(img);

            imgView.setFitWidth(44);
            imgView.setPreserveRatio(true);
            imgView.setSmooth(true);
            imgView.setCache(true);
            imgs.add(imgView);
        }
        this.dealerCardPane.getChildren().addAll(imgs);
        this.dealerTotalLbl.setText(" Dealer :  "+dealer.getTotal());
    }

}
