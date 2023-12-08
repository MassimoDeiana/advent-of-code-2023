package com.massimo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static List<Long> seeds = new ArrayList<>();
    public static Almanac almanac = new Almanac();
    public static List<Long> locations = new ArrayList<>();

    public static void main(String[] args) {
        try {
            File file = new File("day5/src/main/resources/input.txt");
            Scanner reader = new Scanner(file);
            readInput(reader);
            System.out.println(findMinLocationPartTwo());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long findMinLocationPartTwo() {
        for(int i = 0; i <seeds.size(); i+=2) {
            Long seed = seeds.get(i);
            Long range = seeds.get(i+1);
            for(Long j = seed; j < seed+range; seed++) {
                locations.add(findLocation(j));
            }
        }
        Long min = Long.valueOf(999999999);
        for(Long location : locations) {
            if(location < min){
                min = location;
            }
        }
        return min;
    }

    public static Long findMinLocation() {
        for(Long seed : seeds) {
            locations.add(findLocation(seed));
        }
        Long min = Long.valueOf(999999999);
        for(Long location : locations) {
            if(location < min){
                min = location;
            }
        }
        return min;
    }

    public static Long findLocation(Long seed) {
        Long source = seed;
        Long destination = Long.valueOf(0);
        for(Map.Entry<String, List<AlmanacItem>> entry : almanac.getMap().entrySet()) {
            for(AlmanacItem item : entry.getValue()) {
                if(item.isInRange(source)) {
                    destination = item.findDestination(source);
                    break;
                }
            }
            source = destination;
        }

        return source;
    }



    public static void readInput(Scanner reader) {
        String mapType = "";
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if(line.contains("seeds")) {
                seeds = Arrays.stream(line.split("[:]")[1].trim().split("\\s+")).map(Long::parseLong).toList();
            }
            else if(line.contains("map")) {
                mapType = line;
            }
            else {
                if(line != "") {
                    String[] mapItem = line.split(" ");
                    almanac.getMap().get(mapType).add(
                            AlmanacItem.builder()
                                    .destinationStart(Long.parseLong(mapItem[0]))
                                    .sourceStart(Long.parseLong(mapItem[1]))
                                    .range(Long.parseLong(mapItem[2]))
                                    .build());
                }
            }
        }
    }
}