package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Point> numericCoordinate = new ArrayList<>();
    public static List<Point> symbolIndexes = new ArrayList<>();
    public static List<Number> numbers = new ArrayList<>();
    public static List<Point> stars = new ArrayList<>();
    public static List<Integer> gearRatios = new ArrayList<>();
    public static char[][] engine;

    public static void main(String[] args) {
        try {
            File file = new File("day3/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);

            var firstResult = 0;
            var secondResult = 0;

            engine = buildEngine(reader);
            findSymbolIndexes();
            findNumber();
            findStars();
            System.out.println(symbolIndexes.toString());
            System.out.println(sumNumberAdjacentToSymbol());
            System.out.println(findGearRatios());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int findGearRatios() {
        int count = 0;
        List<Number> gearNumbers;
        for(Point star : stars) {
            gearNumbers = new ArrayList<>();
            for(Number number : numbers) {
                if(starIsAdjacentToNumber(number.getIndexes(), star)) {
                    gearNumbers.add(number);
                }
                if(gearNumbers.size() == 2) {
                    break;
                }
            }
            int gearRatios = 1;
            if(gearNumbers.size() >= 2) {
                for(Number num : gearNumbers) {
                    gearRatios *= num.getValue();
                }
            }
            count += gearRatios;
        }
        return count;
    }


    public static boolean starIsAdjacentToNumber(List<Point> numberIndexes, Point star) {
        for(Point coordinate : numberIndexes) {
            if(coordinate.isAdjacent(star)) {
                return true;
            }
        }
        return false;
    }

    public static int sumNumberAdjacentToSymbol() {
        int result = 0;
        for(Number number : numbers) {
            if(isNumberAdjacentToSymbol(number)) {
                result += number.getValue();
            }
        }
        return result;
    }

    public static boolean isNumberAdjacentToSymbol(Number number) {
        for(Point index : number.getIndexes()) {
            for(Point symbolIndex : symbolIndexes) {
                if(index.isAdjacent(symbolIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void findSymbolIndexes() {
        for (int i = 0; i < engine.length; i++) {
            for(int j = 0; j < engine[i].length ; j++) {
                if(isSymbol(engine[i][j])) {
                    symbolIndexes.add(new Point(i,j));
                }
            }
        }
    }

    public static void findStars() {
        for (int i = 0; i < engine.length; i++) {
            for(int j = 0; j < engine[i].length ; j++) {
                if(isGear(engine[i][j])) {
                    stars.add(new Point(i,j));
                }
            }
        }
    }

    public static void findNumber() {
        boolean isCreatingNumber = false;
        int id = 0;
        List<Point> numberIndexes = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < engine.length; i++) {
            for(int j = 0; j < engine[i].length ; j++) {
                if(isNumeric(String.valueOf(engine[i][j]))) {
                    isCreatingNumber = true;
                    numberIndexes.add(new Point(i,j));
                    characters.add(engine[i][j]);
                }
                else {
                    if(isCreatingNumber) {
                        StringBuilder textValue = new StringBuilder();
                        for(Character c : characters) {
                            textValue.append(c);
                        }
                        numbers.add(new Number(id, Integer.parseInt(String.valueOf(textValue)), numberIndexes));
                        numberIndexes = new ArrayList<>();
                        characters = new ArrayList<>();
                        id++;
                        isCreatingNumber = false;
                    }
                }
            }
        }
    }

    public static boolean isGear(char c) {
        return c == '*';
    }

    public static boolean isSymbol(char c) {
        return !(c == '.' || isNumeric(String.valueOf(c)));
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static char[][] buildEngine(Scanner reader) {
        char[][] engine = new char[140][];
        int count=0;
        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            char[] chars = line.toCharArray();
            engine[count] = chars;
            count++;
        }
        return engine;
    }



}