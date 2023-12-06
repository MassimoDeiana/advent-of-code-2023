package com.massimo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Number {

    private int id;
    private int value;
    private List<Point> indexes;

}
