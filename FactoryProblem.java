/*
 * Brent Lee (blee96)
 * Kaitlin Bleich (kbleich)
 * 10/22/2018
 * Project 3
 */

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
}
