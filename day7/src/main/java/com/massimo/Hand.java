package com.massimo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Hand {

    private String hand;
    private int bid;
    private Map<Character, Integer> strength;
    public static List<Character> cards = new ArrayList<>() {
        {
            add('A');
            add('K');
            add('Q');
            add('J');
            add('T');
            add('9');
            add('8');
            add('7');
            add('6');
            add('5');
            add('4');
            add('3');
            add('2');
        }
    };

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        this.strength = buildHandStrength();
    }

    public int getHandStrength() {
        if(strength.size() == 1) {
            return 1;
        }
        if(strength.size() == 2) {
            if(strength.containsValue(4)) {
                return 2;
            } else {
                return 3;
            }
        }
        if(strength.size() == 3){
            if(strength.containsValue(3)) {
                return 4;
            } else {
                return 5;
            }
        }
        if(strength.size() == 4) {
            return 6;
        }
        return 7;
    }

    public boolean isHandStronger(Hand h) {
        if(this.getHandStrength() == h.getHandStrength()) {
            for (int i = 0; i <hand.length(); i++){
                int thisIndex = cards.indexOf(this.hand.toCharArray()[i]);
                int hIndex = cards.indexOf(h.hand.toCharArray()[i]);
                if(thisIndex != hIndex) {
                    return thisIndex < hIndex;
                }
            }
        }
        return this.getHandStrength() < h.getHandStrength();
    }

    public Map<Character, Integer> buildHandStrength() {
        Map<Character, Integer> strength = new HashMap<>();
        for(char card : cards) {
            int count = 0;
            for(char c : hand.toCharArray()) {
                if(c == card) {
                    count++;
                }
            }
            if(count > 0) {
                strength.put(card, count);
            }
        }
        return strength;
    }

}
