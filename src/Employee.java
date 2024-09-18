/*
*
*
 */

public class Employee {

    private final String name;
    private double salary;
    static private int nEmployees;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        nEmployees++;
    }

    public String getName() {return name;}

    public double getSalary() {return salary;}

    public void setSalary(double newSalary) {
        salary = newSalary;
    }

    public String toString() {
        return name + " tjänar " + salary + " kr/h.";
    }


}

