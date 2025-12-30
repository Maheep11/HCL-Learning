package basics;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexValidation {

    // Validate mobile number (10 digits, starts with 6-9)
    static boolean validateMobile(String mobile) {
        return Pattern.matches("[6-9][0-9]{9}", mobile);
    }

    // Validate email id
    static boolean validateEmail(String email) {
        return Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email);
    }

    // Validate username (min 4 chars, letters and numbers only)
    static boolean validateUsername(String username) {
        return Pattern.matches("[a-zA-Z0-9]{4,}", username);
    }

    // Validate password (min 6 chars, at least one digit)
    static boolean validatePassword(String password) {
        return Pattern.matches("(?=.*[0-9]).{6,}", password);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            do {
                System.out.println("\n--- REGEX VALIDATION MENU ---");
                System.out.println("1. Validate Mobile Number");
                System.out.println("2. Validate Email ID");
                System.out.println("3. Validate Username");
                System.out.println("4. Validate Password");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {

                    case 1:
                        System.out.print("Enter Mobile Number: ");
                        String mobile = sc.nextLine();
                        if (validateMobile(mobile))
                            System.out.println("Welcome! Valid Mobile Number");
                        else
                            System.out.println("Invalid Mobile Number");
                        break;

                    case 2:
                        System.out.print("Enter Email ID: ");
                        String email = sc.nextLine();
                        if (validateEmail(email))
                            System.out.println("Welcome! Valid Email ID");
                        else
                            System.out.println("Invalid Email ID");
                        break;

                    case 3:
                        System.out.print("Enter Username: ");
                        String username = sc.nextLine();
                        if (validateUsername(username))
                            System.out.println("Welcome! Valid Username");
                        else
                            System.out.println("Invalid Username");
                        break;

                    case 4:
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        if (validatePassword(password))
                            System.out.println("Welcome! Valid Password");
                        else
                            System.out.println("Invalid Password");
                        break;

                    case 5:
                        System.out.println("Exiting program");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } while (choice != 5);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

