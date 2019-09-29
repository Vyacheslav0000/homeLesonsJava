package ru.geerbrains;

public class Animal {
    private String name;
    private int runDistance = 0;
    private int MAX_SWIM_LENGTH = 0;
    private double jumpHeight = 0;


    public Animal(String name, int runDistance, double jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    public void run(int LENGTH) {
        if ((LENGTH >= 0) && (LENGTH <= runDistance)) {
            System.out.println("run: true");
        } else {
            System.out.println("run: false");
        }
    }

    public void swim(int LENGTH) {
        if ((LENGTH >= 0) && (LENGTH <= MAX_SWIM_LENGTH)) {
            System.out.println("swim: false");
        }
        else {
            System.out.println("swim: true");
        }
    }

    public void jump(double HEIGTH) {
        if ((HEIGTH >= 0) && (HEIGTH <= jumpHeight)) {
            System.out.println("jump: fase");
        }
        else {
            System.out.println("jump: true");
        }
    }

}
