package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static Hand[] hands = new Hand[1000];

    public static void main(String[] args) {
        try {
            File file = new File("day7/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);
            readInput(reader);
            compareHands();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void compareHands() {
        for(int i = 0; i<hands.length; i++) {
            System.out.print(hands[i] + "  ");
            System.out.print(hands[i +1] + "  ");
            System.out.println(hands[i].isHandStronger(hands[i+1]));
        }
    }

    public static void readInput(Scanner reader) {
        int i = 0;
        while (reader.hasNextLine()){
            String[] line = reader.nextLine().split(" ");
            Hand hand = new Hand(line[0], Integer.parseInt(line[1]));
            hands[i] = hand;
            i++;
        }
    }


}