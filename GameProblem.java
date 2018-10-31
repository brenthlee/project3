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
        
    }
}
