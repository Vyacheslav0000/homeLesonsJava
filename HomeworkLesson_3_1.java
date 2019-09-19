package ru.geerbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
  * 1. Написать программу, которая загадывает случайное число от 0 до 9,
 * и пользователю дается 3 попытки угадать это число. При каждой попытке компьютер
 * должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
 * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»
 * (1 – повторить, 0 – нет).
 */



public class HomeworkLesson_3_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            Random r = new Random();
            int x = r.nextInt(9);
            int f;
            System.out.println("Угадайте число от 0 до 9!");

            for (int i = 1; i <= 3; i++) {
                f = Integer.parseInt(br.readLine());

                if (f == x) {
                    System.out.println(f + " = " + x + " Вы выйграли!");
                    break;
                }
                else if (f > x) System.out.println(f + " Вы ввели слишком большое число ?");
                else if (f < x) System.out.println(f + " Вы ввели слишком маленькое число ?");

                if (i == 3) System.out.println("Вы проиграли! Загаданное число это: " + x);
            }

            System.out.println("Вы хотите сыграть еще? 1 - ДА / 0 - НЕТ");

            int repeat = Integer.parseInt(br.readLine());
            while ((repeat < 0) || (repeat > 1)) {
                System.out.println("Введитк число: 1 - ДА / 0 - НЕТ");
                repeat = Integer.parseInt(br.readLine());
            }

            if (repeat == 1) {
                System.out.println("Запускаю новую игру...");
            }
            else {
                System.out.println("До свидания!");
                return;
            }
        } while (true);
    }
}