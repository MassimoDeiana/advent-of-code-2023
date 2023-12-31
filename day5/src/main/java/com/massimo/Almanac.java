package com.massimo;

import lombok.Data;

import java.util.*;

@Data
public class Almanac {

    private Map<String, List<AlmanacItem>> map = new LinkedHashMap<>() {{
        put("seed-to-soil map:", new ArrayList<>());
        put("soil-to-fertilizer map:", new ArrayList<>());
        put("fertilizer-to-water map:", new ArrayList<>());
        put("water-to-light map:", new ArrayList<>());
        put("light-to-temperature map:", new ArrayList<>());
        put("temperature-to-humidity map:", new ArrayList<>());
        put("humidity-to-location map:", new ArrayList<>());
    }};

}
