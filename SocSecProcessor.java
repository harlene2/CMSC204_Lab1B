import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SocSecProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name, ssn;
        char continueChoice;

        do {
            System.out.print("Name? ");
            name = scanner.nextLine();
            System.out.print("SSN? ");
            ssn = scanner.nextLine();

            try {
                if (isValid(ssn)) {
                    System.out.println(name + " " + ssn + " is valid");
                }
            } catch (SocSecException e) {
                System.out.println("Invalid social security number: " + e.getMessage());
            }

            System.out.print("Continue? (y/n): ");
            continueChoice = scanner.nextLine().charAt(0);

        } while (continueChoice == 'y' || continueChoice == 'Y');

        scanner.close();
    }

    public static boolean isValid(String ssn) throws SocSecException {
        // Check if SSN has 11 characters
        if (ssn.length() != 11) {
            throw new SocSecException("wrong number of characters");
        }

        // Check if SSN has dashes in the right positions (3rd and 6th characters)
        if (ssn.charAt(3) != '-' || ssn.charAt(6) != '-') {
            throw new SocSecException("dashes at wrong positions");
        }

        // Check if all characters except dashes are digits
        for (int i = 0; i < ssn.length(); i++) {
            if (i != 3 && i != 6) {
                char c = ssn.charAt(i);
                if (!Character.isDigit(c)) {
                    throw new SocSecException("contains a character that is not a digit");
                }
            }
        }

        return true;
    }
}
