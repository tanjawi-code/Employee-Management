import java.util.ArrayList;
import java.util.Comparator;

public class EmployeeManager {

    private final ArrayList<Employee> employees = new ArrayList<>();

    // Getters.
    String getEmployeeName(int index){
        return employees.get(index).getName();
    }
    int getEmployeeID(int index){
        return employees.get(index).getID();
    }
    double getEmployeeSalary(int index){
        return employees.get(index).earning();
    }
    void getManageDetails(int index){
        employees.get(index).manageDetails();
    }
    String employeesFile(int index){
        return employees.get(index).displayEmployeeFile();
    }

    // Saving an employee.
    void saveEmployee(Employee e){
        if(e instanceof SalariedEmployee){
            employees.add(new SalariedEmployee((SalariedEmployee) e));
        }
        else if(e instanceof HourlyEmployee){
            employees.add(new HourlyEmployee((HourlyEmployee) e));
        }
        else if (e instanceof BasePlusCommissionEmployee ){
            employees.add(new BasePlusCommissionEmployee((BasePlusCommissionEmployee) e));
        }
        else if(e instanceof CommissionEmployee){
            employees.add(new CommissionEmployee((CommissionEmployee) e));
        }
    }

    // The size of the employees.
    int employeesSize(){
        return employees.size();
    }

    // Check if the list of the employees is empty.
    boolean employeeIsEmpty(){
        return employees.isEmpty();
    }

    // remove an employee.
    void removeEmployee(int index){
        employees.remove(index);
    }

    // Show details of all types of the employees.
    void allEmployeesDetails(){
        for(Employee employee : employees){
            employee.displayAllDetails();
            System.out.println("The employee "+employee.getName()+" earns : "+employee.earning());
            System.out.println();
        }
    }

    // Show details of the fix employees.
    void fixEmployeesDetails(){
        for (Employee employee : employees) {
            if (employee instanceof SalariedEmployee) {
                employee.displayAllDetails();
                System.out.println("The employee "+employee.getName()+" earns monthly "+employee.earning());
                System.out.println();
            }
        }
    }

    // Show details of the hourly employees.
    void hourlyEmployeeDetails(){
        for (Employee employee : employees) {
            if (employee instanceof HourlyEmployee) {
                employee.displayAllDetails();
                System.out.println("The employee "+employee.getName()+" earns daily "+employee.earning());
                System.out.println();
            }
        }
    }

    // Show details of the commission employees.
    void commissionEmployeeDetails(){
        for(Employee employee : employees){
            if(employee instanceof CommissionEmployee && !(employee instanceof BasePlusCommissionEmployee)){
                employee.displayAllDetails();
                System.out.println("The employee "+employee.getName()+" earns monthly "+employee.earning());
                System.out.println();
            }
        }
    }

    // Show details of the base plus commission employees.
    void basePlusCommissionEmployees(){
        for(Employee employee : employees){
            if(employee instanceof BasePlusCommissionEmployee){
                employee.displayAllDetails();
                System.out.println("The employee "+employee.getName()+" earns monthly "+employee.earning());
                System.out.println();
            }
        }
    }

    // Show details of one employee.
    void oneEmployeeDetails(int index){
        employees.get(index).displayAllDetails();
    }

    // Statistics.
    void statisticsPart1(ArrayList<Double> salaries){
        int fix =0 , hourly =0 , commission =0 , basePlusCommission =0;
        double average = 0;
        int products =0;
        int males = 0, females = 0;

        System.out.println("Name of the company : "+employees.getFirst().companyName());
        System.out.println("The number of employees : "+employees.size());

        for(Employee gender : employees){
            if(gender.getSex() == Gender.male){
                males++;
            }
            else if(gender.getSex() == Gender.female){
                females++;
            }
        }
        System.out.println("The number of males : "+males);
        System.out.println("The number of females : "+females);

        for (Employee employee : employees) {
            if (employee instanceof SalariedEmployee) {
                fix++;
            }
            else if (employee instanceof HourlyEmployee) {
                hourly++;
            }
            else if (employee instanceof BasePlusCommissionEmployee) {
                basePlusCommission++;
            }
            else if (employee instanceof CommissionEmployee) {
                commission++;
            }
        }
        System.out.println("The number of fix employees : "+fix);
        System.out.println("The number of hourly employees : "+hourly);
        System.out.println("The number of commission employees : "+commission);
        System.out.println("The number of base plus commission employees : "+basePlusCommission);

        for(Double salary : salaries ){
            average += salary;
        }
        average = average/salaries.size();
        System.out.println("Average salaries : "+average);

        for (Employee employee : employees) {
            if (employee instanceof soldProducts) {
                products += ((soldProducts) employee).getSoldProduct();
            }
        }
        System.out.println("The number of sold products : "+products);
    }
    void statisticsPart2(){
        ArrayList<Employee> list = new ArrayList<>(employees);
        int fixEmployee = 0;

        System.out.println("\nThe general salary for the fix employees in one year : ");
        for(Employee employee : list){
            if(employee instanceof SalariedEmployee){
                double average = employee.earning() * 12;
                System.out.println("The general salary for the employee "+employee.getName()+" : "+average);
                fixEmployee++;
            }
        }
        if(fixEmployee == 0){
            System.out.println("There are no fix employees.");
        }
        System.out.println();

        list.sort(Comparator.comparingDouble(Employee::earning).reversed());
        double highSalary = list.getFirst().earning();
        double lowSalary = list.getLast().earning();
        System.out.println("The highest salary : "+ highSalary + ", The employee : "+list.getFirst().getName());
        System.out.println("The lowest salary : "+ lowSalary + ", The employee : "+list.getLast().getName());
    }

    // This is for showing salaries.
    void employeesSalaries(employeeType type){
        switch (type){
            case employeeType.Fix :
                for(Employee employee : employees){
                    if(employee instanceof SalariedEmployee){
                        System.out.println("The employee "+employee.getName()+" earns monthly : "+employee.earning());
                    }
                }
                break;
            case employeeType.Hourly :
                for(Employee employee : employees){
                    if(employee instanceof HourlyEmployee){
                        System.out.println("The employee "+employee.getName()+" earns daily : "+employee.earning());
                    }
                }
                break;
            case employeeType.Base_Plus_Commission :
                for(Employee employee : employees){
                    if(employee instanceof BasePlusCommissionEmployee){
                        System.out.println("The employee "+employee.getName()+" earns monthly : "+employee.earning());
                    }
                }
                break;
            case employeeType.Commission :
                for(Employee employee : employees){
                    if(employee instanceof CommissionEmployee && !(employee instanceof BasePlusCommissionEmployee)){
                        System.out.println("The employee "+employee.getName()+" earns monthly : "+employee.earning());
                    }
                }
                break;
            default:
                System.out.println("Something went wrong.");
        }
    }

    // Check the name if it is already in the company.
    String checkName(String name){
        for(Employee employee : employees){
            if(employee.getName().toLowerCase().trim().equals(name)){
                return name;
            }
        }
        return "";
    }
}
