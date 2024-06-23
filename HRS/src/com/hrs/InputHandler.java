package com.hrs;

import java.util.Scanner;
/**
 * <p>
 * The <code>InputHandler</code> class provides methods to handle various types of user inputs
 * via a <code>Scanner</code> object. It includes methods to confirm user choices, retrieve positive
 * integers, integers within a specified range, positive doubles, and doubles greater than or equal to
 * a specified minimum. It also provides a method to retrieve a unique hotel name from the user,
 * checking its uniqueness against a <code>HotelReservationSystem</code> instance.
 * </p>
 * <p>
 * This class ensures robust input handling by validating user inputs and prompting users to re-enter
 * inputs if they are invalid. It provides methods to handle different types of numeric inputs and
 * confirmation prompts.
 * </p>
 */
 public class InputHandler {
  
	private Scanner scn;

    /**
     * Constructs an <code>InputHandler</code> object with the provided <code>Scanner</code> instance.
     *
     * @param scn The <code>Scanner</code> instance used for input operations.
     */
	public InputHandler(Scanner scn){
		this.scn = scn;
	}
	
	 /**
     * Prompts the user with a confirmation message and waits for a valid 'Y' or 'N' response.
     *
     * @param prompt The confirmation message to display.
     * @return <code>true</code> if the user confirms with 'Y', <code>false</code> otherwise.
     */
    public boolean confirmation( String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String answer = scn.nextLine().trim().toUpperCase();

            if (answer.equals("Y")) {
                return true;
            } else if (answer.equals("N")) {
                return false;
            } else {
                System.out.println("\n\tInvalid input. Enter Y or N.");
            }
        }
    }
    /**
     * Retrieves a positive integer input from the user.
     *
     * @param prompt The message prompt to display.
     * @return The positive integer input from the user.
     */
    public int getPositiveIntegerInput(String prompt) {
        int number = 0;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scn.nextLine();
                if (input.isBlank()) {
                    return -1; // Signal for no input
                }
                number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("\n\tInput should be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\tInvalid input. Please enter a valid positive integer.");
            }
        }
    }
    /**
     * Retrieves a valid integer within a specified range from the user.
     *
     * @param min    The minimum value allowed for the input.
     * @param max    The maximum value allowed for the input.
     * @param prompt The message prompt to display.
     * @return The valid integer input within the specified range from the user.
     */
    public int getValidIntegerInRange(int min, int max, String prompt) {
        while (true) {
            int number = getPositiveIntegerInput( prompt);
            if (number == -1) {
                return -1; // No input case handled
            }
            if (number >= min && number <= max) {
                return number;
            } else {
                System.out.println("\n\tInput should be between " + min + " and " + max + ".");
            }
        }
    }
    /**
     * Retrieves a positive double input from the user.
     *
     * @param prompt The message prompt to display.
     * @return The positive double input from the user.
     */
    public  double getPositiveDoubleInput(String prompt) {
        double number = 0;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scn.nextLine();
                if (input.isBlank()) {
                    return -1; // Signal for no input
                }
                number = Double.parseDouble(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("\n\tInput should be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\"\\n\\tInvalid input. Please enter a valid positive number.");
            }
        }
    }
    /**
     * Retrieves a double value greater than or equal to a specified minimum from the user.
     *
     * @param min    The minimum value allowed for the input.
     * @param prompt The message prompt to display.
     * @return The double input greater than or equal to the specified minimum from the user.
     */
    public double getMinDouble(double min, String prompt) {
        while (true) {
            double number = getPositiveDoubleInput(prompt);
            if (number == -1) {
                return -1; // No input case handled
            }
            if (number >= min) {
                return number;
            } else {
                System.out.println("\n\tInput should be greater than or equal to " + min + ".");
            }
        }
    }
    
    /**
     * Retrieves a unique hotel name from the user, ensuring its uniqueness against the provided
     * <code>HotelReservationSystem</code> instance.
     *
     * @param HRS    The <code>HotelReservationSystem</code> instance to check for uniqueness.
     * @param prompt The message prompt to display.
     * @return The unique hotel name input from the user.
     */
    public String getUniqueHotelName(HotelReservationSystem HRS, String prompt) {
        String hotelName;
        while(true) {
            System.out.print(prompt);
            hotelName = scn.nextLine();
            if(hotelName.isBlank()) {
            	return null;
            }
            if (HRS.isHotelNameUnique(hotelName)) {
            	 return hotelName;
            }
            System.out.println("\n\t" +hotelName + " is not unique.");
        } 
  
    }
}