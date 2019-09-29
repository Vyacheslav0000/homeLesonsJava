import ru.geerbrains.Animal;
package ru.geerbrains;

public class Dog extends Animal {
    int runDistance = 500;
    int MAX_SWIM_LENGTH = 10;
    double jumpHeight = 0.5;

    public Dog(String name, int runDistance, double jumpHeight) {
        super(name, runDistance, jumpHeight);
    }
}
