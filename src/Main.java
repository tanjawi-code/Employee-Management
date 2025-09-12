import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {

        EmployeeManager manager = new EmployeeManager();

        int choose;
        do{
            System.out.println("""
                    1 : Add an employee.
                    2 : Show the details of all the employees.
                    3 : Show details of one employee.
                    4 : Manage details of one employee.
                    5 : Remove an employee.
                    6 : Calculate salaries of all types of the employees.
                    7 : Statistics.
                    8 : Exit.""");
            while(true){
                try{
                    System.out.print("Choose : ");
                    choose = input.nextInt();
                    if(choose >= 0){
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Cannot enter letters or symbols.");
                    input.nextLine();
                }
            }
            System.out.println();
            switch (choose){
                case 1 :
                    addEmployee(manager);
                    break;
                case 2 :
                    if(!manager.employeeIsEmpty()){
                        employeesInfo(manager);
                    }
                    else{
                        System.out.println("There are no employees yet.\n");
                    }
                    break;
                case 3 :
                    if(!manager.employeeIsEmpty()){
                        searchForEmployee(manager);
                    }
                    else{
                        System.out.println("There are no employees yet.\n");
                    }
                    break;
                case 4 :
                    if(!manager.employeeIsEmpty()){
                        manageEmployeeDetails(manager);
                    }
                    else{
                        System.out.println("There are no employees yet.\n");
                    }
                    break;
                case 5 :
                    removeEmployee(manager);
                    break;
                case 6 :
                    if(!manager.employeeIsEmpty()){
                        calculateSalaries(manager);
                    }
                    else{
                        System.out.println("There are no employees yet.\n");
                    }
                    break;
                case 7 :
                    statistics(manager);
                    break;
                case 8 :
                    System.out.println("You have finished the program.");
                    break;
                default :
                    System.out.println("Wrong choice, try again.\n");
            }
        } while(choose != 8);

        input.close();
    }

    // This is for setting the gender of the employee. It is a part of the function (addEmployee).
    static Gender addEmployeeGender(){
        System.out.println("What is the gender of the employee : (1 : male || 2 : female) ");
        do{
            try{
                System.out.print("Enter the choice number : ");
                int choice = input.nextInt();
                if(choice == 1){
                    input.nextLine();
                    System.out.println();
                    return Gender.male;
                }
                else if(choice == 2){
                    input.nextLine();
                    System.out.println();
                    return Gender.female;
                }
                else{
                    System.out.println("The choice must be only 1 or 2.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("The choice cannot have letters or symbols.");
                input.nextLine();
            }
        } while(true);
    }

    // This is for setting the name of the employee. It is a part of the function (addEmployee).
    static String addEmployeeName(int count, EmployeeManager manager){
        System.out.println("Enter the details of the employee number "+(count+1)+" : ");
        do{
            System.out.print("The name of the employee : ");
            String name = input.nextLine().toLowerCase().trim();
            if(name.equals(manager.checkName(name))){
                System.out.println("The name is already in the list.");
            }
            else if(name.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")){
                return name;
            }
            else{
                System.out.println("The name cannot have numbers or symbols.");
            }
        } while(true);
    }

    // This is for setting the SSN of the employee. It is a part of the function (addEmployee).
    static int addEmployeeSSN() {
        int number1 = random.nextInt(1000,10000);
        int number2 = random.nextInt(1000,10000);
        return number1 + number2;
    }

    // This is for setting the address of the employee. It is a part of the function (addEmployee).
    static String addEmployeeAddress(String name){
        String address;
         do{
             System.out.print("The address of the employee "+name+" : ");
             address = input.nextLine().trim().toLowerCase();
             if(address.matches("[a-zA-Z]+")){
                 return address;
             }
             else{
                 System.out.println("The name cannot have symbols or numbers or space.");
             }
         } while(true);
     }

    // This is for setting the ID of the employee. It is a part of the function (addEmployee).
    static int addEmployeeID(){
        return random.nextInt(1000,10000);
    }

    // This is for setting details of the fix employee. It is a part of the function (addEmployee).
    static void setFixEmployee(SalariedEmployee employee, String name){
        System.out.println("\nEnter the salary of the fix employee ,"+name+", : ");
        double salary;
        do{
            try{
                System.out.print("Enter The salary : ");
                salary = input.nextDouble();
                if(salary >= 5000){
                    employee.setSalary(salary);
                    break;
                }
                else{
                    System.out.println("The salary can't be less than 5000DH.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Cannot enter letters or symbols.");
                input.nextLine();
            }
        } while(true);
        System.out.println();
    }

    // This is for setting details of the hourly employee. It is a part of the function (addEmployee).
    static void setHourlyEmployee(HourlyEmployee employee, String name){
        System.out.println("\nEnter the details of the hourly employee ,"+name+", : ");
        int hoursNumber;
        do{
            try{
                System.out.print("Enter the number of hours : ");
                hoursNumber = input.nextInt();
                if(hoursNumber >= 5 ){
                    employee.setNumbersOfHours(hoursNumber);
                    break;
                }
                else{
                    System.out.println("The numbers of working hours can't be less than 5.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Cannot enter letters or symbols.");
                input.nextLine();
            }
        } while(true);
        System.out.println();
    }

    // This is for setting the details of the commission employee. It is a part of the function (addEmployee).
    static void setCommissionEmployee(CommissionEmployee employee, String name){
        System.out.println("\nEnter the details of the commission employee ,"+name+", : ");
        int grossSales;
        double commissionRate = 10;
        do{
            try{
                System.out.print("Enter the number of gross sales : ");
                grossSales = input.nextInt();
                if(grossSales >= 0){
                    employee.setGrossSales(grossSales);
                    employee.setCommissionRate(commissionRate);
                    break;
                }
                else{
                    System.out.println("The number cannot be negative.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Cannot enter letters or symbols.");
                input.nextLine();
            }
        } while(true);
        System.out.println();
    }

    // This is for setting the details of the base plus commission employee. It is a part of the function (addEmployee).
    static void setBasePlusCommissionEmployee(BasePlusCommissionEmployee employee, String name){
        System.out.println("\nEnter the details of the base plus commission employee ,"+name+", : ");
        double commissionRate = 10, salary;
        int grossSales;
        do{
            try{
                System.out.print("Enter the salary : ");
                salary = input.nextDouble();
                System.out.print("Enter the gross sales : ");
                grossSales = input.nextInt();
                if(grossSales >= 0 && salary >= 5000){
                    employee.setSalary(salary);
                    employee.setGrossSales(grossSales);
                    employee.setCommissionRate(commissionRate);
                    break;
                }
                else{
                    System.out.println("The salary must be 5000Dh or higher, and gross sales must be 0 or higher.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Cannot enter letters or symbols.");
                input.nextLine();
            }
        } while(true);
        System.out.println();
    }

    // This is for adding an employee.
    static void addEmployee(EmployeeManager manager){
        int count = 0;
        int choose;
        do{
            Gender sex = addEmployeeGender();
            String name = addEmployeeName(count,manager);
            int SSN = addEmployeeSSN();
            int ID = addEmployeeID();
            String address = addEmployeeAddress(name);

            System.out.println("\nWhat is the type of the employee ?");
            System.out.println("""
                    1 : Fix employee.
                    2 : Hourly employee.
                    3 : Commission employee.
                    4 : Base plus commission employee.""");
            int choice = 0;
            do {
                try{
                    System.out.print("Choose : ");
                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            SalariedEmployee fixEmployee = new SalariedEmployee(name,SSN,address,sex,
                                    ID,employeeType.Fix);
                            setFixEmployee(fixEmployee, name);
                            manager.saveEmployee(fixEmployee);
                            break;
                        case 2:
                            HourlyEmployee hourlyEmployee = new HourlyEmployee(name,SSN,address,sex,ID,
                                    employeeType.Hourly);
                            setHourlyEmployee(hourlyEmployee, name);
                            manager.saveEmployee(hourlyEmployee);
                            break;
                        case 3:
                            CommissionEmployee commission = new CommissionEmployee(name,SSN,address,sex,ID,
                                    employeeType.Commission);
                            setCommissionEmployee(commission, name);
                            manager.saveEmployee(commission);
                            break;
                        case 4:
                            BasePlusCommissionEmployee basePlusCommission = new BasePlusCommissionEmployee(name,SSN,
                                    address,sex,ID,employeeType.Base_Plus_Commission);
                            setBasePlusCommissionEmployee(basePlusCommission, name);
                            manager.saveEmployee(basePlusCommission);
                            break;
                        default:
                            System.out.println("Wrong choice, Try again.\n");
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Cannot enter letters or symbols.");
                    input.nextLine();
                }
            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

            do{
                try{
                    System.out.print("Do you want to add another employee (1 : yes || 2 : no) : ");
                    choose = input.nextInt();
                    if(choose == 1 || choose == 2){
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.println("The choice must be 1 or 2.");
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("The choice cannot have letters or symbols.");
                    input.nextLine();
                }
            } while(true);

            count++;
        } while(choose != 2);
    }

    // This is for showing the details of the all employees.
    static void employeesInfo(EmployeeManager manager){
        System.out.println("""
                1 : Show fix employees details.
                2 : Show hourly employees details.
                3 : Show commission employees details.
                4 : Show base plus commission employees details.
                5 : Show all types of employees.""");
        do{
            try{
                System.out.print("Choose : ");
                int choice = input.nextInt();
                System.out.println();
                switch (choice){
                    case 1 : manager.fixEmployeesDetails(); break;
                    case 2 : manager.hourlyEmployeeDetails(); break;
                    case 3 : manager.commissionEmployeeDetails(); break;
                    case 4 : manager.basePlusCommissionEmployees(); break;
                    case 5 : manager.allEmployeesDetails(); break;
                    default: System.out.println("Wrong choice, try again.\n");
                }
                if(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5){
                    break;
                }
                else{
                    System.out.println("The choice must be 1 or 2 or 3 4 or 5.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("The choice must be a number.");
                input.nextLine();
            }
        } while(true);
        System.out.println();
    }

    // Statistics.
    static void statistics(EmployeeManager manager){
        if(manager.employeeIsEmpty()){
            System.out.println("There no employees in the list.");
        }
        else{
            System.out.println("Statistics of the company : ");
            ArrayList<Double> salaries = new ArrayList<>();
            for(int x = 0; x< manager.employeesSize() ; x++){
                salaries.add(manager.getEmployeeSalary(x));
            }
            manager.statisticsPart1(salaries);
            manager.statisticsPart2();
        }
        System.out.println();
    }

    // This is for removing an employee.
    static void removeEmployee(EmployeeManager manager){
        if(!manager.employeeIsEmpty()){
            System.out.print("Enter the employee name you want to remove : ");
            input.nextLine();
            String name = input.nextLine().trim().toLowerCase();
            boolean isFound = false;
            int index = 0;

            for(int x = 0 ;x<manager.employeesSize() ; x++){
                if(name.equals(manager.getEmployeeName(x))){
                    System.out.println("The employee "+name+" is found, these are the details : ");
                    manager.oneEmployeeDetails(x);
                    System.out.println("The employee earns monthly : "+manager.getEmployeeSalary(x));
                    isFound = true;
                    index = x;
                    break;
                }
            }
            if(isFound){
                do{
                    System.out.print("\nDo you want to remove the employee "+name+" (yes/no) : ");
                    String choice = input.nextLine().trim().toLowerCase();
                    if(choice.equals("yes")){
                        manager.removeEmployee(index);
                        System.out.println("You have removed the employee "+name+".\n");
                        break;
                    }
                    else if (choice.equals("no")){
                        System.out.println("The employee "+name+" is not removed.\n");
                        break;
                    }
                    else{
                        System.out.println("The choice must be yes or no.");
                    }

                } while(true);
            }
            else{
                System.out.println("The employee "+name+" is not found in the list.\n");
            }
        }
        else{
            System.out.println("There are no employees yet in the list.\n");
        }
    }

    // A method for choosing the way to search for the employee. It is a part of the function (searchForEmployee).
    static int searchingWay(){
        System.out.println("Here you can choose how you want to search for the employee.\n");
        int choice;
        do{
            try{
                System.out.print("Choose the way, (searching by name : 1 || searching by ID : 2) : ");
                choice = input.nextInt();
                if(choice == 1){
                    System.out.println();
                    return choice;
                }
                else if(choice == 2){
                    System.out.println();
                    return choice;
                }
                else{
                    System.out.println("The choice must be 1 or 2.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("The choice cannot hava letters or symbols.");
                input.nextLine();
            }
        } while (true);
    }

    // Search for an employee and see his/her details.
    static void searchForEmployee(EmployeeManager manager){
        int choice = searchingWay();

        switch (choice){
            case 1 :
                System.out.print("Enter the name of the employee : ");
                input.nextLine();
                String name = input.nextLine().trim().toLowerCase();
                boolean found = false;
                for(int x = 0 ;x< manager.employeesSize() ; x++) {
                    if (name.equals(manager.getEmployeeName(x))) {
                        System.out.println("\nThe details of the employee " + name + " : ");
                        manager.oneEmployeeDetails(x);
                        System.out.println("The employee earns : " + manager.getEmployeeSalary(x));
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println("Could not find the employee "+name);
                }
                break;
            case 2 :
                int ID;
                do{
                    try{
                        System.out.print("Enter the ID of the employee : ");
                        ID = input.nextInt();
                        if(ID >= 0){
                            break;
                        }
                        else{
                            System.out.println("The ID cannot be negative.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("The ID can't have letters or symbols.");
                        input.nextLine();
                    }
                } while (true);

                boolean isFound = false;
                for(int x = 0 ;x< manager.employeesSize() ; x++) {
                    if (ID == manager.getEmployeeID(x)) {
                        System.out.println("\nThe details of the employee " + manager.getEmployeeName(x) + " : ");
                        manager.oneEmployeeDetails(x);
                        System.out.println("The employee earns monthly : " + manager.getEmployeeSalary(x));
                        isFound = true;
                        break;
                    }
                }
                if(!isFound){
                    System.out.println("Could not find the employee ID "+ID);
                }
                break;
            default:
                System.out.println("Something went wrong.");
        }
        System.out.println();
    }

    // This is for showing the salary for every employee in any type.
    static void calculateSalaries(EmployeeManager manager){
        System.out.println("""
                1 : Show fix employees salaries.
                2 : Show hourly employees salaries.
                3 : Show commission employees salaries.
                4 : Show base plus commission employees salaries.""");
        int choice;
        do{
            try{
                System.out.print("Choose : ");
                choice = input.nextInt();
                System.out.println();
                switch (choice){
                    case 1 : manager.employeesSalaries(employeeType.Fix); break;
                    case 2 : manager.employeesSalaries(employeeType.Hourly); break;
                    case 3 : manager.employeesSalaries(employeeType.Commission); break;
                    case 4 : manager.employeesSalaries(employeeType.Base_Plus_Commission); break;
                    default: System.out.println("Wrong choice, try again.\n");
                }
                if(choice == 1 || choice == 2 || choice == 3 || choice == 4){
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Can't accept letters or symbols.");
            }
        } while(true);
        System.out.println("\n");
    }

    // Manage the employee details.
    static void manageEmployeeDetails(EmployeeManager manager){
        System.out.println("You can manage any type of the employee.\n");
        String name;
        boolean isFound = false;
        input.nextLine();
        while(true){
            System.out.print("Enter the name of the employee you want to modify : ");
            name = input.nextLine().trim().toLowerCase();
            if(name.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")){
                break;
            }
            else{
                System.out.println("The name cannot have numbers or symbols.");
            }
        }

        for(int x = 0 ; x< manager.employeesSize() ; x++){
            if(manager.getEmployeeName(x).equals(name)){
                manager.getManageDetails(x);
                isFound = true;
                break;
            }
        }
        if(!isFound){
            System.out.println("The employee "+name+" is not found.\n");
        }
    }
}