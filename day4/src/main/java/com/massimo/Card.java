package com.massimo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Card {

    private Integer cardNumber;
    private Integer numberOfCards;
    private List<Integer> winningNumbers;
    private List<Integer> scratchedNumbers;

    public int getCardPoint() {
        int cardPoint = 0;
        for(Integer scratchedNumber : scratchedNumbers) {
            if(winningNumbers.contains(scratchedNumber)) {
                if(cardPoint == 0) {
                    cardPoint++;
                } else {
                    cardPoint = cardPoint*2;
                }
            }
        }
        return cardPoint;
    }

    public int getNumberOfGivenScratchCard() {
        int numberOfCopy = 0;
        for(Integer scratchedNumber: scratchedNumbers) {
            if(winningNumbers.contains(scratchedNumber)) {
                if(cardNumber + numberOfCopy <= 202) {
                    numberOfCopy++;
                }
            }
        }
        return numberOfCopy;
    }

}
