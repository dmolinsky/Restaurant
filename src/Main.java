import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Hello! Let's create your restaurant.\n" +
                            "What would you like to name it? > ");

        boolean validInput = true;
        String inputName = "";
        int menuChoice;
        do {
            inputName = sc.nextLine();
            if (inputName.matches("[a-öA-Ö]+")) {
                Restaurant restaurant = new Restaurant(inputName);
            } else {
                System.out.println("The name contains non-letter characters.\n" +
                        "Please enter another name > ");
                validInput = false;
            }
        } while (!validInput);
        Restaurant restaurant = new Restaurant(inputName);

        restaurant.printMainMenu();

        menuChoice = sc.nextInt();
        switch(menuChoice)  {
            case 1 -> Owner.printOwnersMenu();
            case 2 -> Employee.printEmployeesMenu();
            //add case 3 to print summary
            default -> System.out.println("Invalid menu choice. Try again > ");
        }


    }

}