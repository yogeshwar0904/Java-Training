import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * To arrange the given number in 
 * accending order.
 */
public class NumberArrangement {
    private static void setNumberAccendingOrder(int [] number) {
        for(int row = 0; row <number.length; row++) {
            for(int col = row + 1; col < number.length; col++) {
                if(number[row] > number[col]) {
                    int temporary = number[row];
                    number[row] = number[col];
                    number[col] = temporary;
                }
            } 
        }
        System.out.println(Arrays.toString(number));
    }

    public static void main(String[] args) {
        int size;
        int [] number;
        boolean continueAgain = true;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("enter the size of a array");
                size = scanner.nextInt();
                number = new int[size];
                System.out.println("Enter the numbers to initialize");
                System.out.println();
                for(int index = 0;index < size; index++) {
                    number[index] = scanner.nextInt();
                }
                setNumberAccendingOrder(number);
                continueAgain = false;
            } catch (InputMismatchException exception) {
                System.out.println("Entered data is invalid!!");
                scanner.next();
            }
        } while (continueAgain);
    }
}