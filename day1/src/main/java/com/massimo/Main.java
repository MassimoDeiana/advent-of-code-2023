package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            File file = new File("day1/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);
            System.out.println(resolveSecondPart(reader));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int resolveFirstPart(Scanner reader) {
        var result = 0;
        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            result += Integer.parseInt(getTwoDigitInWord(line));
        }
        return result;
    }

    public static String getTwoDigitInWord(String word) {
        String numberOnly = word.replaceAll("[^0-9]", "");
        if (numberOnly.length() == 1) {
            return numberOnly + numberOnly;
        }
        else if (numberOnly.length() > 2) {
            return String.valueOf(numberOnly.charAt(0)) + String.valueOf(numberOnly.charAt(numberOnly.length() - 1 ));
        }
        else {
            return numberOnly;
        }
    }

    public static Map<String, String> wordToNumber;
    static {
        wordToNumber = new HashMap<>();
        wordToNumber.put("one", "1");
        wordToNumber.put("two", "2");
        wordToNumber.put("three", "3");
        wordToNumber.put("four", "4");
        wordToNumber.put("five", "5");
        wordToNumber.put("six", "6");
        wordToNumber.put("seven", "7");
        wordToNumber.put("eight", "8");
        wordToNumber.put("nine", "9");
    }

    public static String convertTextNumberToDigit(String word) {
        String wordCopy = word;
        for(String textNumber: wordToNumber.keySet()) {
            if (word.contains(textNumber)) {
                wordCopy = wordCopy.replace(textNumber.substring(0,textNumber.length() - 1), wordToNumber.get(textNumber));
            }
        }
        return wordCopy;
    }


    public static int resolveSecondPart(Scanner reader) {
        var result = 0;
        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            result += Integer.parseInt(getTwoDigitInWord(convertTextNumberToDigit(line)));
        }
        return result;
    }
}