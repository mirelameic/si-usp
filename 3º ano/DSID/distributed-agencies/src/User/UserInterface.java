package User;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner;

    private UserInterface() {
        throw new AssertionError();
    }

    static {
        scanner = new Scanner(System.in);
    }

    public static String getUserCommand() {
        System.out.print(">> ");
        return scanner.nextLine().trim();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayError(String errorMessage, Exception exception) {
        System.err.println(errorMessage);
        System.err.println(exception.getMessage());
    }

    public static void printLine() {
        System.out.println();
    }
}