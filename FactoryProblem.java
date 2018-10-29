/*
 * Brent Lee (blee96)
 * Kaitlin Bleich (kbleich)
 * 10/22/2018
 * Project 3
 */

import java.util.*;
import java.io.*;

public class FactoryProblem {

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
        int n = scanner.nextInt();  //number of stations
        int e1 = scanner.nextInt(); //enter time for line 1
        int e2 = scanner.nextInt(); //enter time for line 2
        int x1 = scanner.nextInt(); //exit time for line 1
        int x2 = scanner.nextInt(); //exit time for line 2
        int[] a1 = new int[n];      //time for each station for line 1
        int[] a2 = new int[n];      //time for each station for line 2
        int[] t1 = new int[n-1];    //transfer time from line 1 to 2
        int[] t2 = new int[n-1];    //transfer time from line 2 to 1
        int[][] l = new int[3][n];
        int[] f1 = new int[n];
        int[] f2 = new int[n];
        for (int i = 0; i < n; i++) {
            a1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a2[i] = scanner.nextInt();
        }
        for (int i = 0; i < n-1; i++) {
            t1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n-1; i++) {
            t2[i] = scanner.nextInt();
        }
        int[] ans = assemble(a1, a2, t1, t2, e1, e2, x1, x2, n, l, f1, f2);
        System.out.println("Fastest time is: " + ans[1]); //f*
        System.out.println("\nThe optimal route is:");
        printSolution(l, n, n, ans[0]);
        System.out.println("\n-----------------------\nEnd of Output\n");
    }

    private static int[] assemble(int[] a1, int[] a2, int[] t1, int[] t2, int e1, int e2, int x1, int x2, int n, int[][] l, int[] f1, int[] f2) {
        int first, second;
        int fStar;
        int lStar;
        f1[0] = e1 + a1[0];
        l[1][0] = 1;
        f2[0] = e2 + a2[0];
        l[2][0] = 2;
        for (int i = 1; i < n; i++) {
            first = f1[i-1] + a1[i];
            second = f2[i-1] + t2[i-1] + a1[i];
            if (first < second) {
                f1[i] = first;
                l[1][i] = 1;
            } else {
                f1[i] = second;
                l[1][i] = 2;
            }
            first = f2[i-1] + a2[i];
            second = f1[i-1] + t1[i-1] + a2[i];
            if (first < second) {
                f2[i] = first;
                l[2][i] = 2;
            } else {
                f2[i] = second;
                l[2][i] = 1;
            }
        }
        first = f1[n-1]+x1;
        second = f2[n-1]+x2;
        int[] ans = new int[2]; //ans[0] = l* and ans[1] = f*
        if (first < second) {
            ans[0] = 1;
            ans[1] = first;
        } else {
            ans[0] = 2;
            ans[1] = second;
        }
        return ans;
    }

    private static void printSolution(int[][] l, int n, int cons, int last) {
        if (n > 0) {
            printSolution(l, n-1, cons, l[last][n-1]);
            if (n == cons) {
                System.out.println("Station: " + n + ", line: " + last);
            } else {
                System.out.println("Station: " + n + ", line: " + l[last][n]);
            }
        }
    }
}
