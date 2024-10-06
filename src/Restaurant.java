import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private final ArrayList<Employee> employees;
    private final ArrayList<Owner> owners;

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

        if (owners.isEmpty()) {
            createOwner(name);
        } else {
            int hasBeenDistributed = 0;
            int toRedistribute = 0;
            int possibleOwnership = 100 - owners.size();
            int newOwnerPercent = 0;
            int getOwnershipFrom = 0;
            boolean inputOkay = false;

            while (!inputOkay) {
                System.out.printf(AnsiColors.ANSI_BLUE);
                System.out.println("Enter ownership percentage: ");
                newOwnerPercent = sc.nextInt();
                if (newOwnerPercent > possibleOwnership) {
                    System.out.printf(AnsiColors.ANSI_RED);
                    System.out.println("There's already " + owners.size() +
                            " owners. \nThey need to keep atleast 1% ownership each. \n" +
                            "The maximum ownership for the new owner is therefore " +
                            possibleOwnership + "%.");
                    System.out.printf(AnsiColors.ANSI_RESET);
                } else if (newOwnerPercent < 0) {
                    System.out.printf(AnsiColors.ANSI_RED);
                    System.out.println("Please enter a positive value...");
                    System.out.printf(AnsiColors.ANSI_RESET);
                } else {
                    inputOkay = true;
                }
            }

            System.out.printf(AnsiColors.ANSI_MAGENTA);
            System.out.println("There's already " + owners.size() +
                    " owners with a total of 100% ownership. \nYou need to redistribute " +
                    newOwnerPercent + "% from them to the new owner.");

            while (newOwnerPercent > hasBeenDistributed) {
                System.out.printf(AnsiColors.ANSI_BLUE);
                System.out.println("The new owner " + name + " needs " + (newOwnerPercent - hasBeenDistributed) + "% more.");
                System.out.println("Which owner would you like to redistribute ownership from?" + AnsiColors.ANSI_RESET);
                printOwners();

                inputOkay = false;
                while (!inputOkay) {
                    System.out.println(AnsiColors.ANSI_BLUE + "Enter your choice: ");
                    getOwnershipFrom = sc.nextInt() - 1;
                    sc.nextLine();
                    if (owners.size() <= getOwnershipFrom) {
                        System.out.printf(AnsiColors.ANSI_RED);
                        System.out.println("Please enter a number which represents an owner in the list...");
                        System.out.printf(AnsiColors.ANSI_RESET);
                    } else {
                        inputOkay = true;
                    }
                }

                inputOkay = false;
                while (!inputOkay) {
                    System.out.printf(AnsiColors.ANSI_BLUE);
                    System.out.println("How much would you like to redistribute from "
                            + owners.get(getOwnershipFrom).getName() + "?");
                    System.out.println("Enter value: ");
                    toRedistribute = sc.nextInt();

                    if (toRedistribute > owners.get(getOwnershipFrom).getOwnership()) {
                        System.out.printf(AnsiColors.ANSI_RED);
                        System.out.println(owners.get(getOwnershipFrom).getName() +
                                "has to keep atleast 1%. Please try again...");
                        System.out.printf(AnsiColors.ANSI_RESET);
                    } else if (toRedistribute > newOwnerPercent - hasBeenDistributed || toRedistribute < 0) {
                        System.out.printf(AnsiColors.ANSI_RED);
                        System.out.println("Value has to be between 0 and " + (newOwnerPercent - hasBeenDistributed) + "%.");
                        System.out.println("Please try again...");
                        System.out.printf(AnsiColors.ANSI_RESET);
                    } else {
                        inputOkay = true;
                    }
                }

                //Updates ownership of owner who distributed % to new owner
                float previousOwnership = owners.get(getOwnershipFrom).getOwnership();
                owners.get(getOwnershipFrom).setOwnership(previousOwnership - toRedistribute);

                System.out.printf(AnsiColors.ANSI_CYAN);
                System.out.println(owners.get(getOwnershipFrom).getName() + "s new ownership is "
                + owners.get(getOwnershipFrom).getOwnership() + "%.");
                System.out.printf(AnsiColors.ANSI_RESET);

                hasBeenDistributed += toRedistribute;
            }

            createOwner(name, newOwnerPercent);
        }
    }

    public void createOwner (String name, float ownership) {
        Owner newOwner = new Owner(name, ownership);
        owners.add(newOwner);

        System.out.printf(AnsiColors.ANSI_CYAN);
        System.out.println("Owner " + name + " is added to the restaurant.");
        System.out.println(name +"s ownership is set to " + ownership + "% . \n");
        System.out.printf(AnsiColors.ANSI_RESET);
    }

    public void createOwner (String name) {
        Owner newOwner = new Owner(name, 100);
        owners.add(newOwner);
        System.out.printf(AnsiColors.ANSI_CYAN + "Owner " + name + " added to restaurant.\n");
        System.out.printf("Ownership automatically set to 100 percent. \n\n");
        System.out.printf(AnsiColors.ANSI_RESET);
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
            System.out.printf("%-5s %10s%n", "3.", "Modify and owner.");
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
            for (int i = 0; i < owners.size(); i++) {
                System.out.printf("%-5s %10s%n", (i+1) + ".", owners.get(i).toString());
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
            System.out.println(this.name + " has " + employees.size() + " employees:");
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        }

        System.out.println(AnsiColors.ANSI_BLUE + "--------------------" + AnsiColors.ANSI_RESET);

        if (owners.isEmpty()) {
            System.out.println(this.name + " has 0 owners.\n");

        } else {
            System.out.println(this.name + " has " + owners.size() + " owners:");
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


