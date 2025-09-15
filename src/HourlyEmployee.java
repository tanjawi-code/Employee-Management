import java.util.InputMismatchException;
import java.util.Scanner;

public class HourlyEmployee extends Employee implements DisplayAble{

    private double HourlySalary;
    private int NumbersOfHours;

    HourlyEmployee(){}

    HourlyEmployee(String name , int ssn , String address, Gender sex, int ID, employeeType type){
        super(name,ssn,address,sex,ID,type);
        this.HourlySalary = 100;
    }

    HourlyEmployee(HourlyEmployee other){
        super(other);
        this.HourlySalary = other.HourlySalary;
        this.NumbersOfHours = other.NumbersOfHours;
    }

    // Setters.
    void setNumbersOfHours(int numbersOfHours) {
        NumbersOfHours = numbersOfHours;
    }

    @Override
    double earning(){
        return HourlySalary * NumbersOfHours;
    }
    @Override
    public void displayAllDetails(){
        super.info();
        System.out.println("The number of hours : "+NumbersOfHours+" || Hourly salary : "+HourlySalary);
    }

    // Saving the employees in a file.
    @Override
    public String displayEmployeeFile(){
        return super.toString()+NumbersOfHours+","+HourlySalary+"\n";
    }

    // This is a function from the class Employee. Its jib to modify the data of the employee.
    @Override
    void manageDetails(){
        Scanner input = new Scanner(System.in);
        System.out.println("Modifying the details of the employee " + getName()+", type "+getType()+" employee.\n");

        int choice;
        while(true){
            try{
                System.out.print("Do you want to change the number of working hours (yes : 1 || no : 2) : ");
                choice = input.nextInt();
                if(choice == 1){
                    System.out.print("Change the current number of working hours : ");
                    int newNumberOfHours = input.nextInt();
                    if(newNumberOfHours >= 5 && NumbersOfHours != newNumberOfHours){
                        NumbersOfHours = newNumberOfHours;
                        System.out.println("The employee "+getName()+" works : "+this.NumbersOfHours);
                        break;
                    }
                    else{
                        System.out.println("Could not change, the number of hours cannot be less than 5 or equal the old one.");
                    }
                }
                else if(choice == 2){
                    break;
                }
                else{
                    System.out.println("The choice must be  1or 2.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Cannot enter letters or symbols. ");
                input.nextLine();
            }
        }
        System.out.println("\n");
    }
}
