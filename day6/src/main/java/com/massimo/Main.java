package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static Long[] times;
    public static Long[] distances;
    public static Long time;
    public static Long distance;

    public static void main(String[] args) {

        try {
            File file = new File("day6/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);
            readInput(reader);
            int result = 1;
            for(int i = 0; i<4; i++) {
                QuadraticEquation equation = getEquation(times[i], distances[i]);
                result *= getWaysOfWin(equation.resolveEquation());
            }

            QuadraticEquation equation = getEquation(time, distance);
            int resultPartTwo = getWaysOfWin(equation.resolveEquation());

            System.out.println(result);
            System.out.println(resultPartTwo);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getWaysOfWin(int[] equationResponses) {
        return Math.abs(equationResponses[0] - equationResponses[1]);
    }

    public static QuadraticEquation getEquation(Long time, Long distance) {
        return new QuadraticEquation((long) -1, time, -distance);
    }

    public static void readInput(Scanner reader) {
        String line = reader.nextLine();
        times = Stream.of(line.split(":")[1].trim().split("\\s+")).map(Long::parseLong).toArray(Long[]::new);
        time = Long.parseLong(line.replaceAll("[^0-9]", ""));

        line = reader.nextLine();
        distances = Stream.of(line.split(":")[1].trim().split("\\s+")).map(Long::parseLong).toArray(Long[]::new);
        distance = Long.parseLong(line.replaceAll("[^0-9]", ""));

    }
}