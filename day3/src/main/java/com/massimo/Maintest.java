package com.massimo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Maintest {
    private static int[][] directions=new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
    public static void main(String[]args) {
        long sum=0;
        Map<Point,Integer> map=new HashMap<>();
        Map<Point,Integer> counts=new HashMap<>();
        try {
            File file = new File("day3/src/main/resources/input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> list=br.lines().collect(Collectors.toList());
            char[][] css=new char[list.size()][];
            IntStream.range(0,list.size()).forEach(i-> css[i]=list.get(i).toCharArray());
            for (int i=0;i< css.length;i++) {
                int num = 0;
                boolean isGear = false;
                Point gearCoord=new Point(-1,-1);
                for (int j=0;j<css[i].length;j++) {
                    int tmp = findNum(css[i][j]);
                    if (tmp == -1) {
                        if(isGear){
                            System.out.println("row "+(i+1)+": "+num+"; "+ gearCoord);
                            map.put(gearCoord,map.getOrDefault(gearCoord,1)*num);
                            counts.put(gearCoord,counts.getOrDefault(gearCoord,0)+1);
                            gearCoord=new Point(-1,-1);
                            isGear=false;
                        }
                        num = 0;
                    } else {
                        num=num*10+tmp;
                        for(int[] dir:directions){
                            if(i+dir[0]>=0&&i+dir[0]< css.length&&j+dir[1]>=0&&j+dir[1]<css[i+dir[0]].length){
                                if(!isGear&&css[i+dir[0]][j+dir[1]]=='*'){
                                    gearCoord=new Point(i+dir[0],j+dir[1]);
                                    isGear=true;
                                }
                            }
                        }
                    }
                }
                if(isGear){
                    map.put(gearCoord,map.getOrDefault(gearCoord,1)*num);
                    counts.put(gearCoord,counts.getOrDefault(gearCoord,0)+1);
                }
            }
        } catch (Exception e){System.out.println(e);}
        for(Point p:map.keySet()) sum+=counts.get(p)==2 ? map.get(p) : 0;
        System.out.println(sum);
    }

    private static int findNum(char c){
        if(c>='0'&&c<='9') return c-'0';
        return -1;
    }


}