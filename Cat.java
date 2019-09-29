package ru.geerbrains;

import ru.geerbrains.Animal;

public class Cat extends Animal {
    int runDistance = 200;
    double jumpHeight = 2;

    public Cat(String name, int runDistance, double jumpHeight) {
        super(name, runDistance, jumpHeight);
    }
}
