public class Restaurant {
    private String name;

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
        System.out.println("Welcome to " + name + ". What would you like to do?" +
                "\n1. Manage owners." +
                "\n2. Manage employees." +
                "\n3. Print a summery of the restaurant." +
                "\n4. Exit the program.");
    }

    public String toString () {
        return "Restaurant name: " + name + ".";
    }


}
