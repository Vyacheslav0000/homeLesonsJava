package ru.geerbrains;

import java.util.Random;
import java.util.Scanner;

public class HomeworkLesson4 {
    final int size = 9;                     //размерность поля
    char[][] map = new char[size][size];    //матрица игры
    final char cNull = '*', player = 'X', cpu1 = 'O', cpu2 = 'W', cpu3 = 'Z';   //игроки
    final int winningLineMinus = 2;    //переменная для уменьшения выйгрышной линии
    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();

    public static void main(String[] args) {
        HomeworkLesson4 g = new HomeworkLesson4();
        g.newMap();
        g.printMap();

        while (true) {
            //Ход человека
            g.playerTurn();
            g.printMap();
            if (g.checkWin(g.player)) { System.out.println("Замечательно! Вы выйграли"); break; }
            if (g.isMapFull()) { System.out.println("Игра окончена. Ничья"); break; }

/**         //AI-1 turn
 g.aiTurn(g.cpu1);
 g.printMap();
 if (g.checkWin(g.cpu1)) { System.out.println("Игра окончена. CPU1 выйграл");
 break; }
 if (g.isMapFull()) { System.out.println("Игра окончена. Ничья");
 break; }*/

/**         Too much players
 //AI-2 turn
 g.aiTurn(g.cpu2);
 g.printMap();
 if (g.checkWin(g.cpu2)) { System.out.println("Игра окончена. CPU2 выйграл");
 break; }
 if (g.isMapFull()) { System.out.println("Игра окончена. Ничья");
 break; }*/


            //AI-3 turn
            g.aiTurn(g.cpu3);
            g.printMap();
            if (g.checkWin(g.cpu3)) { System.out.println("Игра окончена. CPU3 выйграл");
                break; }
            if (g.isMapFull()) { System.out.println("Игра окончена. Ничья");
                break; }
        }
    }

    void newMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = cNull;
            }
        }
    }

    void printMap() {
        System.out.print(">X ");
        for (int i = 1; i <= size; i++) {
            System.out.print(" " + i + " ");
        }

        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println(i + 1);
        }
        System.out.print(" ^Y ");
        for (int i = 1; i <= size; i++) System.out.print(i + "  ");
        System.out.println();
    }

    void playerTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ячейки (X Y)");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            System.out.println("Вы ввели координатв: x = " + (x + 1) + ", y = " + (y + 1));
        } while (!isCellValid(x, y));
        map[y][x] = player;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        if (map[y][x] == cNull) return true;
        return false;
    }

    boolean checkWin(char c) {
        int countV;
        int countH;
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i <= size - 1; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j <= size - 1; j++) {
                //Проверяем горизонтальную линию
                if (map[i][j] == c) {
                    countH++;
                    if (countH == size - winningLineMinus) return true;
                }

                //Проверяем вертикальную линию
                if (map[j][i] == c) {
                    countV++;
                    if (countV == size - winningLineMinus) return true;
                }
            }

            // Проверяем диагональ А "\"
            if (map[i][i] == c) {
                countDiagonalA++;
                if (countDiagonalA == size - winningLineMinus) return true;
            } else countDiagonalA = 0;

            // Проверяем диагональ B "/"
            if (map[i][size - 1 - i] == c) {
                countDiagonalB++;
                if (countDiagonalB == size - winningLineMinus) return true;
            } else countDiagonalB = 0;
        }
        return false;
    }

    boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == cNull) return false;
            }
        }
        return true;
    }

    void aiTurn(char c) {
        int x = 0, y = 0, countH = 0, countHNull = 0, countV = 0, countVNull = 0, countDiagonalA = 0, countDiagonalB = 0, countDANull = 0, countDBNull = 0;

        System.out.println("Ход CPU  [" + c +"]:");

        // 1. Атака игрока
        for (int i = 0; i < size; i++) {
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < size; j++) {
                //Хороший вертикальный ход
                if (map[j][i] == c) countV++;
                else if (map[j][i] == cNull) countVNull++;
                if ((countV == size - 1) && (countVNull == 1)) {
                    //System.out.println("CPU почти выйграл! vert line = " + (i + 1)); // i - horiz line
                    for (int k = 0; k < size; k++) {
                        if (map[k][i] == cNull) {
                            map[k][i] = c;
                            return;
                        }
                    }
                }
                //Хороший горизонтальный ход
                if (map[i][j] == c) countH++;
                else if (map[i][j] == cNull) countHNull++;
                if ((countH == size - 1) && (countHNull == 1)) {
                    //System.out.println("CPU почти выйграл! horiz line = " + (i + 1)); // i - horiz line
                    for (int k = 0; k < size; k++) {
                        if (map[i][k] == cNull) {
                            map[i][k] = c;
                            return;
                        }
                    }
                }

            }

            // Хорошая диагональ A "\"
            if (map[i][i] == c) countDiagonalA++;
            else if (map[i][i] == cNull) countDANull++;
            if ((countDiagonalA == size - 1) && (countDANull == 1)) {
                //System.out.println("CPU ALMOST WIN! diagA line \\");
                for (int j = 0; j < size; j++) {
                    if (map[j][j] == cNull) {
                        map[j][j] = c;
                        return;
                    }
                }
            }

            // Хорошая диагональ B "/"
            if (map[i][size - 1 - i] == c) countDiagonalB++;
            else if (map[i][size - 1 - i] == cNull)  countDBNull++;
            if ((countDiagonalB == size - 1) && (countDBNull == 1)) {
                //System.out.println("CPU ALMOST WIN! diagB line /");
                for (int j = 0; j < size; j++) {
                    if (map[j][size - 1 - j] == cNull) {
                        map[j][size - 1 - j] = c;
                        return;
                    }
                }
            }
        }

        countH = 0;
        countHNull = 0;
        countV = 0;
        countVNull = 0;
        countDiagonalA = 0;
        countDiagonalB = 0;
        countDANull = 0;
        countDBNull = 0;

        // 2. блокировка игрока
        for (int i = 0; i < size; i++) {
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < size; j++) {
                //Хороший вертикальный ход
                if (map[j][i] == player) countV++;
                else if (map[j][i] == cNull) countVNull++;
                if ((countV == size - 1) && (countVNull == 1)) {
                    //System.out.println("WARNING FOR CPU! vert line = " + (i + 1)); // i - horiz line
                    for (int k = 0; k < size; k++) {
                        if (map[k][i] == cNull) {
                            map[k][i] = c;
                            return;
                        }
                    }
                }
                //Хороший горизонтальный ход
                if (map[i][j] == player) countH++;
                else if (map[i][j] == cNull) countHNull++;
                if ((countH == size - 1) && (countHNull == 1)) {
                    //System.out.println("WARNING FOR CPU! horiz line = " + (i + 1)); // i - horiz line
                    for (int k = 0; k < size; k++) {
                        if (map[i][k] == cNull) {
                            map[i][k] = c;
                            return;
                        }
                    }
                }

            }

            // Хорошая диагональ A "\"
            if (map[i][i] == player) countDiagonalA++;
            else if (map[i][i] == cNull) countDANull++;
            if ((countDiagonalA == size - 1) && (countDANull == 1)) {
                //System.out.println("WARNING FOR CPU! diagA line \\");
                for (int j = 0; j < size; j++) {
                    if (map[j][j] == cNull) {
                        map[j][j] = c;
                        return;
                    }
                }
            }

            // Хорошая диагональ B "/"
            if (map[i][size - 1 - i] == player) countDiagonalB++;
            else if (map[i][size - 1 - i] == cNull)  countDBNull++;
            if ((countDiagonalB == size - 1) && (countDBNull == 1)) {
                //System.out.println("WARNING FOR CPU! diagB line /");
                for (int j = 0; j < size; j++) {
                    if (map[j][size - 1 - j] == cNull) {
                        map[j][size - 1 - j] = c;
                        return;
                    }
                }
            }
        }

        // 3. центр карты
        if (!(size % 2 == 0)) {
            int center = (((size + 1) / 2) - 1);
            if (map[center][center] == cNull) {
                map[center][center] = c;
                return;
            }
        }

        // 4. диагональные точки карты
        if (map[0][0] == cNull) { map[0][0] = c; return; }
        if (map[0][map.length - 1] == cNull) { map[0][map.length - 1] = c; return; }
        if (map[map.length - 1][0] == cNull) { map[map.length - 1][0] = c; return; }
        if (map[map.length - 1][map.length - 1] == cNull) { map[map.length - 1][map.length - 1] = c; return; }

        // 5. случайный ход
        //System.out.println("AI random");
        do {
            x = r.nextInt(size);
            y = r.nextInt(size);
        } while (!isCellValid(x, y));
        map[y][x] = c;
        System.out.println("AI X: " + (x + 1) + " Y: " + (y + 1));
    }
}
