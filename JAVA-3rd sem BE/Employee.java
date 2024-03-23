public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double percent) {
        if (percent > 0) {
            double raiseAmount = salary * (percent / 100);
            salary += raiseAmount;
            System.out.println(name + "'s salary raised by " + percent + "%. New salary: $" + salary);
        } else {
            System.out.println("Invalid percentage. Salary remains unchanged.");
        }
    }

    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Salary: $" + salary;
    }

    public static void main(String[] args) {
        // Creating an Employee object
        Employee employee = new Employee(1, "John Doe", 50000.0);

        // Displaying employee details
        System.out.println("Initial Employee Details:");
        System.out.println(employee);

        // Raising salary by 10%
        employee.raiseSalary(10);

        // Displaying updated employee details
        System.out.println("\nEmployee Details after Salary Raise:");
        System.out.println(employee);
    }
}
