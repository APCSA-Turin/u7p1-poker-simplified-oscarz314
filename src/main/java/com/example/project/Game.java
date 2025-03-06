package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        // see hand points
        if (Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)){
            return "Player 1 wins!";
        }
        else if (Utility.getHandRanking(p1Hand) < Utility.getHandRanking(p2Hand)){
            return "Player 2 wins!";
        }

        // Order allCards for each Player
        ArrayList<Card> p1AllCards = organizePlayerCards(p1);
        ArrayList<Card> p2AllCards = organizePlayerCards(p2);

        // if straightFlush tie, fullHouse, flush, or straight
        if(Utility.getHandRanking(p1Hand) == 10 || Utility.getHandRanking(p1Hand) == 8 || Utility.getHandRanking(p1Hand) == 7 || Utility.getHandRanking(p1Hand) == 6){
            if (getRankInt(p1AllCards.get(4)) > getRankInt(p2AllCards.get(4))) {
                return "Player 1 wins!";
            }
            else if (getRankInt(p1AllCards.get(4)) < getRankInt(p2AllCards.get(4))){
                return "Player 2 wins!";
            }
        }

        // if high card
        Card p1HighCard;
        Card p2HighCard;

        if (getRankInt(p1.getHand().get(0)) > getRankInt(p1.getHand().get(1))){
            p1HighCard = p1.getHand().get(0);
        }
        else{
            p1HighCard = p1.getHand().get(1);
        }

        if (getRankInt(p2.getHand().get(0)) > getRankInt(p2.getHand().get(1))){
            p2HighCard = p2.getHand().get(0);
        }
        else{
            p2HighCard = p2.getHand().get(1);
        }

        if (getRankInt(p1HighCard) > getRankInt(p2HighCard)){
            return "Player 1 wins!";
        }
        else if (getRankInt(p1HighCard) < getRankInt(p2HighCard)){
            return "Player 2 wins!";
        }
        else if (getRankInt(p1HighCard) == getRankInt(p2HighCard)){
            return "Tie!";
        }



        return "Error";
    }

    public static ArrayList<Card> organizePlayerCards(Player player){
        ArrayList<Card> temp = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            temp.add(player.getAllCards().get(i));
        }

        for (int i = 0; i < 4; i++){
            int j = i + 1;

            while (j > 0 && getRankInt(temp.get(j - 1)) > getRankInt(temp.get(j))){
                Card sub = temp.set(j, temp.get(j - 1));
                temp.set(j - 1, sub);
            }
        }

        return temp;
    }

    public static int getRankInt(Card card){
        return Utility.getRankValue(card.getRank());
    }

    public static void play(){ //simulate card playing
        
    }
        
        

}