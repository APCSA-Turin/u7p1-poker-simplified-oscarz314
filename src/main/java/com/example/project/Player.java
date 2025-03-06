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

    public void testCards(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
        // allCards.add(new Card("8", "♠"));
        // allCards.add(new Card("7", "♠"));
        // allCards.add(new Card("8", "♦"));
        // allCards.add(new Card("7", "♠"));
        // allCards.add(new Card("7", "♣"));

        // allCards.add(new Card("5", "♠"));
        // allCards.add(new Card("8", "♦"));
        // allCards.add(new Card("A", "♣"));
        // allCards.add(new Card("8", "♠"));
        // allCards.add(new Card("8", "♣"));

        // allCards.add(new Card("5", "♠"));
        // allCards.add(new Card("5", "♦"));
        // allCards.add(new Card("3", "♣"));
        // allCards.add(new Card("3", "♥"));
        // allCards.add(new Card("A", "♠"));

        allCards.add(new Card("5", "♠"));
        allCards.add(new Card("7", "♠"));
        allCards.add(new Card("10", "♠"));
        allCards.add(new Card("8", "♠"));
        allCards.add(new Card("9", "♠"));
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        // allCards = communityCards;
        allCards = new ArrayList<>();
        for(int i = 0; i < hand.size(); i++){
            allCards.add(hand.get(i));
        }

        for(int i = 0; i < communityCards.size(); i++){
            allCards.add(communityCards.get(i));
        }

        // 1 Royal Flush
        if (royalFlush() == true){
            return "Royal Flush";
        }

        // 2 Straight Flush
        if (straightFlush() == true){
            return "Straight Flush";
        }

        // 3 Four of a kind
        if (ofKinds() == 4){
            return "Four of a Kind";
        }

        // 4 Full House
        if (ofKinds() == 3 && onlyOnePair() == true){
            return "Full House";
        }

        // 5 Flush
        if(flush() == true){
            return "Flush";
        }

        // 6 Straight
        if(straight() == 5){
            return "Straight";
        }

        // 7 Three of a Kind
        if (ofKinds() == 3){
            return "Three of a Kind";
        }

        // 8 2 Pairs
        if(twoPairs() == 2){
            return "Two Pair";
        }

        // 9 A Pair
        if (ofKinds() == 2){
            return "A Pair";
        }

        // 10 HighCard;
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
        for (int i = 0; i < 15; i++){
            rankFrequency.add(0);
        }

        for(int i = 0; i < allCards.size(); i++){
            rankFrequency.set(getRankInt(allCards.get(i)), rankFrequency.get(getRankInt(allCards.get(i))) + 1);
        }

        return rankFrequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>();
        for (int i = 0; i < 4; i++){
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
        boolean isHighCard= false;

        for(int i = 0; i < allCards.size(); i++){
            if(hand.get(0) != allCards.get(i) && hand.get(1) != allCards.get(i)){
                if(getRankInt(hand.get(0)) > getRankInt(allCards.get(i)) || getRankInt(hand.get(1)) > getRankInt(allCards.get(i))){
                    isHighCard = true;
                }
                else{
                    isHighCard = false;
                }
            }
        }

        return isHighCard;
    }

    public int ofKinds(){
        int highestFreq = 1;
        ArrayList<Integer> freqList = findRankingFrequency();

        for(int i = 0; i < freqList.size(); i++){
            if(freqList.get(i) > highestFreq){
                highestFreq = freqList.get(i);
            }
        }

        return highestFreq;
    }

    public int twoPairs(){
        int numPairs = 0;
        ArrayList<Integer> freqList = findRankingFrequency();

        for(int i = 0; i < freqList.size(); i++){
            if(freqList.get(i) == 2){
                numPairs++;
            }
        }

        return numPairs;
    }

    public boolean onlyOnePair(){
        ArrayList<Integer> freqList = findRankingFrequency();

        for(int i = 0; i < freqList.size(); i++){
            if(freqList.get(i) == 2){
                return true;
            }
        }

        return false;
    }

    public int straight(){
        int highestChain = 1;
        int numChain = 1;
        ArrayList<Integer> freqList = findRankingFrequency();

        for(int i = 0; i < freqList.size() - 1; i++){
            if(freqList.get(i) > 0 && freqList.get(i) == freqList.get(i + 1)){
                numChain++;
            }
            else{
                numChain = 1;
            }

            if (numChain > highestChain){
                highestChain = numChain;
            }
        }
        
        return highestChain;
    }

    public boolean flush(){
        ArrayList<Integer> suitList = findSuitFrequency();
        
        for(int i = 0; i < suitList.size(); i++){
            if (suitList.get(i) == 5 && straight() != 5){
                return true;
            }
        }

        return false;
    }

    public boolean straightFlush(){
        ArrayList<Integer> suitList = findSuitFrequency();
        
        for(int i = 0; i < suitList.size(); i++){
            if (suitList.get(i) == 5 && straight() == 5){
                return true;
            }
        }

        return false;
    }

    public boolean royalFlush(){
        ArrayList<Integer> freqList = findRankingFrequency();
        for (int i = 0; i < freqList.size(); i++){
            if (i < 10 && freqList.get(i) > 0){
                return false;
            }
        }

        if (straightFlush()){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Player test = new Player();
        test.testCards();
        System.out.println(test.flush());
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }
}
