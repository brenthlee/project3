/*
 * Brent Lee (blee96)
 * Kaitlin Bleich (kbleich)
 * 10/22/2018
 * Project 3
 */

import java.util.*;

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
        int n = Integer.parseInt(scanner.nextLine());
    }

    //array of times for each station, times to transfer, enter times, exit times, number of stations
    private static int timeAssembly(int[][] a, int[][] t, int[] e, int[] x, int nStation) {
        int[] t1 = new int[nStation]; // number of stations
        int[] t2 = new int[nStation]; // number of stations
        // initial time to leave line 1, station 1
        t1[0] = e[0] + a[0][0];
        // initial time to leave line 2, station 1
        t2[0] = e[1] + a[1][0];

        for (int i = 1; i < nStation; i++) {
            t1[i] = Math.min(
                        t1[i-1] + a[0][i],
                        t2[i-1] + t[1][i] + a[0][i]);
            t2[i] = Math.min(
                        t2[i-1] + a[1][i],
                        t1[i-1] + t[0][i] + a[1][i]);
        }
        return Math.min(t1[nStation-1] + x[0], t2[nStation-1] + x[1]);

    }
}
