package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards = communityCards;

        if(highCard() == true){
            return "High Card";
        }

        return "Nothing";
    }

    public void sortAllCards(){
        for(int i = 0; i < allCards.size() - 1; i++){
            int j = i + 1;
            while(j > 0 && getRankInt(allCards.get(j)) < getRankInt(allCards.get(j - 1))){
                Card temp = allCards.set(j, allCards.get(j - 1));
                allCards.set(j - 1, temp);
            }
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankFrequency = new ArrayList<>();
        for (int i = 0; i < rankFrequency.size(); i++){
            rankFrequency.add(0);
        }

        for(int i = 0; i < allCards.size(); i++){
            rankFrequency.set(getRankInt(allCards.get(i)), rankFrequency.get(getRankInt(allCards.get(i))) + 1);
        }

        return rankFrequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>();
        for (int i = 0; i < suitFrequency.size(); i++){
            suitFrequency.add(0);
        }

        for (int i = 0; i < allCards.size(); i++){
            if (allCards.get(i).getSuit().equals(Utility.getSuits()[0])){
                suitFrequency.set(0, suitFrequency.get(0) + 1);
            }
            else if (allCards.get(i).getSuit().equals(Utility.getSuits()[1])){
                suitFrequency.set(1, suitFrequency.get(1) + 1);
            }
            else if (allCards.get(i).getSuit().equals(Utility.getSuits()[2])){
                suitFrequency.set(2, suitFrequency.get(2) + 1);
            }
            else if (allCards.get(3).getSuit().equals(Utility.getSuits()[3])){
                suitFrequency.set(3, suitFrequency.get(3) + 1);
            }
        }

        return suitFrequency; 
    }

    public int getRankInt(Card card){
        return Utility.getRankValue(card.getRank());
    }

    public Boolean highCard(){
        for(int i = 0; i < allCards.size(); i++){
            if(hand.get(0) != allCards.get(i) && hand.get(1) != allCards.get(i)){
                if(getRankInt(hand.get(0)) > getRankInt(allCards.get(i)) || getRankInt(hand.get(1)) > getRankInt(allCards.get(i))){
                    return true;
                }
            }
        }

        return false;
    }

    Public String countRepeats(){
        int highestRepeat
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
