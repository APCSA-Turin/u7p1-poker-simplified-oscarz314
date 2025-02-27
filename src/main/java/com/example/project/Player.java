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
        
    }

    public String playHand(ArrayList<Card> communityCards){
        return "Nothing";
    }

    public void sortAllCards(){} 

    public ArrayList<Integer> findRankingFrequency(){
        Array<Integer> rankFrequency = new ArrayList<Integer>(Arrays.asList(new int[12]));

        for(int i = 0; i < allCards.size(); i++){
            allCards.get(i).getRank()
        }
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
