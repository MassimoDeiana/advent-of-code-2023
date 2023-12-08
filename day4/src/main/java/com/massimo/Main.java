package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        try {
            File file = new File("day4/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);
            readInput(reader);
            System.out.println(getTotalPoints());
            updateNumberOfCards();
            System.out.println(getTotalOfCards());
            var firstResult = 0;
            var secondResult = 0;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getTotalOfCards() {
        int totalCards = 0;
        for(Card card: cards) {
            totalCards += card.getNumberOfCards();
        }
        return totalCards;
    }

    public static void updateNumberOfCards() {
        for(Card card : cards) {
            int numberOfCopy = card.getNumberOfGivenScratchCard();
            for(int j = 0; j < card.getNumberOfCards(); j++) {
                for(int i = card.getCardNumber() + 1 ; i <= card.getCardNumber() + numberOfCopy; i++) {
                    Card c = findCardByNumber(i);
                    if(c != null) {
                        findCardByNumber(i).setNumberOfCards(c.getNumberOfCards()+1);
                    }
                }
            }
        }
    }

    public static Card findCardByNumber(int number) {
        for(Card card : cards) {
            if(card.getCardNumber() == number) {
                return card;
            }
        }
        return null;
    }


    public static int getTotalPoints() {
        int totalPoints = 0;
        for(Card card : cards) {
            totalPoints += card.getCardPoint();
        }
        return totalPoints;
    }

    public static void readInput(Scanner reader) {
        while(reader.hasNextLine()) {
            String[] line = reader.nextLine().split("[:|]");
            Integer cardNumber = Integer.valueOf(line[0].replaceAll("[^0-9]", ""));

            List<Integer> winningNumbers = Arrays.stream(line[1].trim().split("\\s+")).map(Integer::parseInt).toList();
            List<Integer> scratchedNumbers = Arrays.stream(line[2].trim().split("\\s+")).map(Integer::parseInt).toList();
            cards.add(Card.builder()
                    .cardNumber(cardNumber)
                    .numberOfCards(1)
                    .scratchedNumbers(scratchedNumbers)
                    .winningNumbers(winningNumbers)
                    .build());
        }
    }


}