import java.util.InputMismatchException;
import java.util.Scanner;

public class CommissionEmployee extends Employee implements DisplayAble, soldProducts{

    private int GrossSales;
    private double CommissionRate;

    CommissionEmployee(){}

    CommissionEmployee(String name, int ssn, String address,Gender sex, int ID, employeeType type){
        super(name,ssn,address,sex,ID,type);
    }

    CommissionEmployee(CommissionEmployee other){
        super(other);
        this.GrossSales = other.GrossSales;
        this.CommissionRate = other.CommissionRate;
    }

    // Setters and Getters.
    void setCommissionRate(double commissionRate) {
        CommissionRate = commissionRate;
    }
    void setGrossSales(int grossSales) {
        GrossSales = grossSales;
    }
    public int getGrossSales() {
        return GrossSales;
    }
    public double getCommissionRate() {
        return CommissionRate;
    }

    // Calling the method getSoldProduct from the interFace soldProducts.
    @Override
    public int getSoldProduct(){
        return this.GrossSales;
    }

    // Calculate the current products with the new products. It is used in BasePlusCommissionEmployee.
    void calculateProducts(int newProducts){
        this.GrossSales += newProducts;
    }

    @Override
    double earning(){
        return this.GrossSales * this.CommissionRate;
    }
    @Override
    public void displayAllDetails(){
        super.info();
        System.out.println("The amount of sales : "+this.GrossSales+" || The commission rate : "+this.CommissionRate);
    }

    // Saving the employees in a file.
    @Override
    public String displayEmployeeFile(){
        return super.toString()+
                "Number of sold products = " + GrossSales + " || Commission rate = " +CommissionRate+"%";
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
                        GrossSales += newProducts;
                        System.out.println("The employee "+getName()+" sold : "+this.GrossSales);
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
