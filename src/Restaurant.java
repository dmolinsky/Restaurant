import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<Owner> owners;

    public Restaurant(String name) {
        this.name = name;
        employees = new ArrayList<>();
        owners = new ArrayList<>();
    }

    public void addEmployee(String name, double salary) {
        Employee newEmployee = new Employee(name, salary);
        employees.add(newEmployee);
    }

    public void addOwnerToRestaurant() {
        Scanner sc = new Scanner(System.in);

        System.out.printf(AnsiColors.ANSI_BLUE + "\nEnter owner name: ");
        String name = sc.nextLine();
        //System.out.println(AnsiColors.ANSI_BLUE + "Enter ownership percentage: ");
        //int newOwnership = sc.nextInt();

        if (owners.size() == 0) {
            Owner newOwner = new Owner(name, 100);
            owners.add(newOwner);
            System.out.printf(AnsiColors.ANSI_BLUE + "Owner " + name + " added to restaurant.\n");
            System.out.printf("Ownership automatically set to 100 percent. \n\n" + AnsiColors.ANSI_RESET);

        } else {
            int possibleOwnership = 100 - owners.size();
            System.out.println(AnsiColors.ANSI_BLUE + "Enter ownership percentage: ");
            int newOwnership = sc.nextInt();
            if (newOwnership < possibleOwnership) {
                System.out.println("There's already " + owners.size());
            } else
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printMainMenu () {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
           System.out.printf(AnsiColors.ANSI_BLUE + "Welcome to " + name + ". " +
                   "\nWhat would you like to do?\n" + AnsiColors.ANSI_RESET);
           System.out.printf("%-5s %10s%n", "1.", "Manage owners.");
           System.out.printf("%-5s %10s%n", "2.", "Manage employees.");
           System.out.printf("%-5s %10s%n", "3.", "Print a summery.");
           System.out.printf("%-5s %10s%n", "0.", "Exit the program.");


           int menuChoice = sc.nextInt();
           switch (menuChoice) {
               case 1 -> printOwnersMenu();
               case 2 -> printEmployeesMenu();
               case 3 -> this.printSummary();
               case 0 -> {
                   System.out.println("Exiting program. Welcome back later!");
                   exit = true;
               }
               default -> System.out.println("Invalid menu choice. Try again > ");
           }
       }while(!exit);

        sc.close();
    }


    private void printOwnersMenu () {

        Scanner sc = new Scanner(System.in);
        boolean goBack = false;
        do {
            System.out.printf(AnsiColors.ANSI_BLUE + "What would you like to do?\n" + AnsiColors.ANSI_RESET);
            System.out.printf("%-5s %10s%n", "1.", "Show all owners.");
            System.out.printf("%-5s %10s%n", "2. ", "Add new owner.");
            System.out.printf("%-5s %10s%n", "3.,", "Modify and owner.");
            System.out.printf("%-5s %10s%n", "4. ", "Remove an owner.");
            System.out.printf("%-5s %10s%n", "0. ", "Go back to the main menu.");
            System.out.printf(AnsiColors.ANSI_BLUE + "Enter your choice: " + AnsiColors.ANSI_RESET);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> printOwners();
                case 2 -> addOwnerToRestaurant();
                case 3 -> System.out.println("Modify owner not created yet.");
                case 4 -> System.out.println("Remove owner not created yet.");
                case 0 -> goBack = true;
                default -> System.out.println("Invalid choice. Try again > ");
            }
        }while(!goBack);
    }

    private void printOwners () {
        if (owners.isEmpty()) {
            System.out.println("There are no owners.\n");

        } else {
            for (Owner owner : owners) {
                System.out.println(owner.toString());
            }
            System.out.println();
        }
    }

    private void printEmployeesMenu () {

    }

    private void printSummary() {
        if (employees.isEmpty()) {
            System.out.println(this.name + " has 0 employees.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        }

        System.out.println(AnsiColors.ANSI_BLUE + "--------------------" + AnsiColors.ANSI_RESET);

        if (owners.isEmpty()) {
            System.out.println(this.name + " has 0 owners.\n");

        } else {
            for (Owner owner : owners) {
                System.out.println(owner.toString());
            }
            System.out.println();
        }
    }

    public String toString () {
        return "Restaurant name: " + name + ".";
    }


}


