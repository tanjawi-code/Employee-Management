import java.util.InputMismatchException;
import java.util.Scanner;

public class BasePlusCommissionEmployee extends CommissionEmployee implements DisplayAble,soldProducts{

    private double salary;

    BasePlusCommissionEmployee(){}

    BasePlusCommissionEmployee(String name,int ssn,String address,Gender sex,int ID, employeeType type){
        super(name,ssn,address,sex,ID,type);
    }

    BasePlusCommissionEmployee(BasePlusCommissionEmployee other){
        super(other);
        this.salary = other.salary;
    }

    // Setters
    void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    void setCommissionRate(double commissionRate) {
        super.setCommissionRate(commissionRate);
    }
    @Override
    void setGrossSales(int grossSales) {
        super.setGrossSales(grossSales);
    }

    // Calling the method getSoldProduct from the interFace soldProducts.
    @Override
    public int getSoldProduct(){
        return super.getGrossSales();
    }

    @Override
    double earning(){
        return this.salary + ( getGrossSales() * getCommissionRate() );
    }

    @Override
    public void displayAllDetails(){
        super.info();
        System.out.println("Salary : "+salary+" || The amount of sales : "+getGrossSales()+" || The commission rate  :"+getCommissionRate());
    }

    // Saving the employees in a file.
    @Override
    public String displayEmployeeFile(){
        return super.toString()+
                "Salary = " + salary + " || Number of sold products = "+this.getSoldProduct()+" || Commission rate = "+this.getCommissionRate()+"%";
    }

    // This is a function from the class Employee. Its jib to modify the data of the employee.
    @Override
    void manageDetails(){
        Scanner input = new Scanner(System.in);
        System.out.println("Modifying the details of the employee " + getName()+", type "+getType()+" employee.\n");

        while(true){
            try{
                System.out.print("Did the employee sell other products (yes : 1 || no : 2) : ");
                int choice = input.nextInt();
                if(choice == 1){
                    System.out.print("How many did the employee sell : ");
                    int newProducts = input.nextInt();
                    if(newProducts > 0){
                        super.calculateProducts(newProducts);
                        System.out.println("The employee "+getName()+" sold : "+getGrossSales());
                        break;
                    }
                    else{
                        System.out.println("The products cannot be 0.");
                    }
                }
                else if(choice == 2){
                    break;
                }
                else {
                    System.out.println("The choice must be 1 or 2.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("The choice cannot have letters or symbols.");
                input.nextLine();
            }
        }
        System.out.println("\n");
    }

}
