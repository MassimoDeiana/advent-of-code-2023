package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static final Integer RED = 12;
    public static final Integer GREEN = 13;
    public static final Integer BLUE = 14;

    public static void main(String[] args) {
        try {
            File file = new File("day2/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);

            var firstResult = 0;
            var secondResult = 0;

            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                firstResult += getPossibleGameId(line);
                secondResult += getPowerOfCubes(line);
            }

            System.out.println(firstResult);
            System.out.println(secondResult);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getPowerOfCubes(String line) {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;
        String[] game = line.split(":");
        String[] set = game[1].split("[,;]");
        for(String cube : set) {
            String[] s = cube.trim().split(" ");
            int amount = Integer.parseInt(s[0]);
            String color = s[1];
            if(color.equals("red") && amount > minRed) { minRed = amount; }
            if(color.equals("green") && amount > minGreen) { minGreen = amount; }
            if(color.equals("blue") && amount > minBlue) { minBlue = amount; }
        }
        return minRed * minGreen * minBlue;
    }


    public static int getPossibleGameId(String line) {
        String[] games = line.split(":");
        int gameId = Integer.parseInt(games[0].split(" ")[1]);
        String[] set = games[1].split("[,;]");
        for(String cube : set) {
            String[] s = cube.trim().split(" ");
            Integer amount = Integer.parseInt(s[0]);
            String color = s[1];
            if(!checkIfOk(amount, color)) {
                return 0;
            }
        }
        return gameId;
    }

    public static boolean checkIfOk(Integer amount, String color) {
        switch (color) {
            case "red": if(amount > RED) return false;
            case "green": if(amount > GREEN) return false;
            case "blue": if(amount > BLUE) return false;
            default: return true;
        }
    }
}