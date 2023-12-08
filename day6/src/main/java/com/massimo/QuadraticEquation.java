package com.massimo;

public class QuadraticEquation {

    public Long a;
    public Long b;
    public Long c;

    public QuadraticEquation(Long a, Long b, Long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int[] resolveEquation() {
        double delta = calculateDelta();
        int firstResult = (int) Math.ceil((-b-Math.sqrt(delta))/(2*a));
        int secondResult = (int) Math.ceil((-b+Math.sqrt(delta))/(2*a));
        return new int[]{firstResult, secondResult};
    }

    public double calculateDelta() {
        return (Math.pow(b,2) - 4*a*c);
    }

}
