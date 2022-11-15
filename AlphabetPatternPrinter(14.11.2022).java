import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Patter to print alphabet 'A'.
 */ 
public class AlphabetPatternPrinter {
    public static int printAlphabet(int number) {
        for (int index = 0; index <= number; index++) {
            for (int element = 0; element <= number / 2; element++) {
                if ((element == 0 || element == number / 2) && index != 0 
                     || index == 0  && element != number / 2 || index == number / 2) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        return number;
    }

    public static void main(String[] args) {
        boolean continueAgain = true;
        Scanner scanner = new Scanner(System.in);          
        do {
            try {
                System.out.println("Enter size of a alphabet ");
                int number = scanner.nextInt();
                printAlphabet(number);
                continueAgain = false;
            } catch (InputMismatchException exception) {
                System.out.println("Enter wrong formate");    
            }
        } while(continueAgain);
    }
}