/*
 * Brent Lee (blee96)
 * Kaitlin Bleich (kbleich)
 * 10/30/2018
 * Project 3
 */

import java.util.*;
import java.io.*;

public class GameProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inFile;
        System.out.print("Enter a filename: ");
        inFile = scanner.nextLine();
        File file = new File(inFile);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.exit(0);
        }
        System.out.println("");
        int rows = scanner.nextInt(); //number of rows
        int cols = scanner.nextInt();
        int[][] A = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        game(rows, cols, A);
    }

    private static void game(int n, int m, int[][] A) {
        int[][] S = new int[n][m];                  //table to hold max scores
        char[][] R = new char[n][m];                //aux table to store decisions (d-down, r-right, e-exit)
        int x = n-1;
        int y = m-1;
        S[n-1][m-1] = A[n-1][m-1];
        R[n-1][m-1] = 'e';
        int max = S[n-1][m-1];
        for (int i = n-2; i >= 0; i--) {
            if (S[i+1][m-1] > 0) {
                S[i][m-1] = S[i+1][m-1] + A[i][m-1];
                R[i][m-1] = 'd';
            } else {
                S[i][m-1] = A[i][m-1];
                R[i][m-1] = 'e';
            }
            if (S[i][m-1] > max) {
                max = S[i][m-1];
                x = i;
                y = m-1;
            }
        }
        for (int j = m-2; j >= 0; j--) {
            if (S[n-1][j+1] > 0) {
                S[n-1][j] = S[n-1][j+1] + A[n-1][j];
                R[n-1][j] = 'r';
            } else {
                S[n-1][j] = A[n-1][j];
                R[n-1][j] = 'e';
            }
            if (S[n-1][j] > max) {
                max = S[n-1][j];
                x = n-1;
                y = j;
            }
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = m-2; j >= 0; j--) {
                if (S[i+1][j] > S[i][j+1]) {
                    S[i][j] = S[i+1][j] + A[i][j];
                    R[i][j] = 'd';
                } else {
                    S[i][j] = S[i][j+1] + A[i][j];
                    R[i][j] = 'r';
                }
                if (S[i][j] > max) {
                    max = S[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        System.out.print("Best score: " + max + "\nBest route: ");
        printSolution(R, x, y);
        System.out.print("exit\n");
        System.out.println("\n-----------------------\nEnd of Output\n");
    }

    private static void printSolution(char[][] R, int i, int j) {
        System.out.print("[" + (i+1) + "," + (j+1) + "] to ");
        if (R[i][j] == 'r') {
            printSolution(R, i, j+1);
        } else if (R[i][j] == 'd') {
            printSolution(R, i+1, j);
        }
    }
}
