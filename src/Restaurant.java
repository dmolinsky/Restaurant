import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<Owner> owners;

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printMainMenu () {
        System.out.printf(AnsiColors.ANSI_BLUE + "Welcome to " + name + ". " +
                "\nWhat would you like to do?\n" + AnsiColors.ANSI_RESET);
        System.out.printf("%-5s %10s%n","1.","Manage owners." );
        System.out.printf("%-5s %10s%n","2", "Manage employees." );
        System.out.printf("%-5s %10s%n", "3", "Print a summery." );
        System.out.printf("%-5s %10s%n","0", "Exit the program." );

        Scanner sc = new Scanner(System.in);
        int menuChoice = sc.nextInt();
        switch (menuChoice) {
            case 1 -> Owner.printOwnersMenu();
            case 2 -> Employee.printEmployeesMenu();
            case 3 -> this.printSummary();
            default -> System.out.println("Invalid menu choice. Try again > ");
        }

        sc.close();
    }

    private void printOwnersMenu () {
        System.out.println("What would you like to do?"+
                "\n1. Show all owners." +
                "\n2. Add new owner." +
                "\n3. Modify and owner." +
                "\n4. Remove an owner." +
                "\n0. Go back to the main menu." +
                "\nEnter your choice: ");
    }

    private void printEmployeesMenu () {

    }

    private void printSummary() {
        if (employees == null) {
            System.out.println(this.name + " has 0 employees.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        }

        System.out.println(AnsiColors.ANSI_BLUE + "--------------------" + AnsiColors.ANSI_RESET);

        if (owners == null) {
            System.out.println(this.name + " has 0 owners.");
        } else {
            for (Owner owner : owners) {
                System.out.println(owner.toString());
            }
        }
    }

    public String toString () {
        return "Restaurant name: " + name + ".";
    }


}


