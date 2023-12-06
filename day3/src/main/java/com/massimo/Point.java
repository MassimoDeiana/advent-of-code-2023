package com.massimo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point {

    public int x;
    public int y;
    public int value;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAdjacent(Point p) {
        int xDifference = Math.abs(this.x - p.x);
        int yDifference = Math.abs(this.y - p.y);

        return xDifference <= 1 && yDifference <= 1;
    }
}
