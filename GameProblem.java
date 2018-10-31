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

        for (int i = n-1; i >= 0; i--)
        {
           for (int j = m-1; j >= 0; j--)
           {
              if (i == n-1 && j == m-1)
              {
                 S[i][j] = A[n-1][m-1];
                 R[i][j] = 'e';
              }
              else if (j == m-1)
              {
                 if(S[i+1][m-1] > 0)
                 {
                    S[i][j] = S[i+1][m-1] + A[i][m-1];
                    R[i][j] = 'd';
                 }
                 else
                 {
                    S[i][j] = A[i][m-1];
                    R[i][j] = 'e';
                 }
              }
              else if (i == n-1)
              {
                  if(S[n-1][j+1] > 0)
                 {
                    S[i][j] = S[n-1][j+1] + A[n-1][j];
                    R[i][j] = 'r';
                 }
                 else
                 {
                    S[i][j] = A[n-1][j];
                    R[i][j] = 'e';
                 }
              }
              else
              {
                 if(S[i+1][j] > S[i][j+1])
                 {
                    S[i][j] = S[i+1][j] + A[i][j];
                    R[i][j] = 'd';
                 }
                 else
                 {
                    S[i][j] = S[i][j+1] + A[i][j];
                    R[i][j] = 'e';
                 }
              }
           }
        }
    }
}
