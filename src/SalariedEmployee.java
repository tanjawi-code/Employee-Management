import java.util.InputMismatchException;
import java.util.Scanner;

public class SalariedEmployee extends Employee implements DisplayAble{

    private double salary;
    private double bonus;
    private double deduction;

    SalariedEmployee(){}

    SalariedEmployee(String name, int ssn,String address, Gender sex, int ID, employeeType type){
        super(name,ssn,address,sex,ID,type);
    }

    SalariedEmployee(SalariedEmployee other){
        super(other);
        this.salary = other.salary;
        this.bonus = other.bonus;
        this.deduction = other.deduction;
    }

    void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    double earning(){
        return (salary + bonus) - deduction;
    }
    @Override
    public void displayAllDetails(){
        super.info();
        System.out.println("The salary : "+this.salary+" || Bonus : "+this.bonus+" || Deduction : "+this.deduction);
    }

    // Saving the employees in a file.
    @Override
    public String displayEmployeeFile(){
        return super.toString()+
                "Salary = " + salary + " || Bonus : " + bonus + " || deduction : " + deduction;
    }

    // This is a function from the class Employee. Its jib to modify the data of the employee.
    @Override
    void manageDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Modifying the details of the employee " + getName()+", type "+getType()+" employee.");
        System.out.println("\nDo want to change the salary or adding the bonus and the deduction.");
        System.out.println("Changing the salary : 1 || adding the bonus and the deduction : 2");
        int choice;
        do {
            try {
                System.out.print("Choose : ");
                choice = input.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                }
                else {
                    System.out.println("The choice must be 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Cannot enter letters or symbols.");
                input.nextLine();
            }
        } while (true);

        switch (choice) {
            case 1:
                System.out.print("Enter the new salary for the employee " + getName() + " : ");
                double newSalary = input.nextDouble();
                if (newSalary >= 5000) {
                    salary = newSalary;
                }
                System.out.println("The new salary of the employee " + getName() + " : " + this.salary);
                break;
            case 2:
                double setBonus, setDeduction;
                System.out.println("Bonus : 1 || Deduction : 2 || Both : 3");
                System.out.print("Choose : ");
                int choose = input.nextInt();
                if (choose == 1) {
                    System.out.print("Enter the bonus for the employee " + getName() + " : ");
                    setBonus = input.nextDouble();
                    this.bonus = setBonus;
                    System.out.println("The bonus of the employee " + getName() + " : " + this.bonus);
                } else if (choose == 2) {
                    System.out.print("Enter the deduction for the employee " + getName() + " : ");
                    setDeduction = input.nextDouble();
                    this.deduction = setDeduction;
                    System.out.println("The deduction of the employee " + getName() + " : " + this.deduction);
                } else if (choose == 3) {
                    System.out.print("Enter the bonus for the employee " + getName() + " : ");
                    setBonus = input.nextDouble();
                    System.out.print("Enter the deduction for the employee " + getName() + " : ");
                    setDeduction = input.nextDouble();
                    this.bonus = setBonus;
                    this.deduction = setDeduction;
                    System.out.println("The bonus of the employee " + getName() + " : " + this.bonus);
                    System.out.println("The deduction of the employee " + getName() + " : " + this.deduction);
                }
                break;
            default:
                System.out.println("Something went wrong.");
        }
        System.out.println("\n");
    }
}
