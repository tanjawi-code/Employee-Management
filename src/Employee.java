enum Gender{male,female}
enum employeeType{Fix, Hourly, Commission, Base_Plus_Commission}

public abstract class Employee implements DisplayAble{

    private final String name;
    private final int SSN;
    private final String address;
    private Gender sex;
    private final int ID;
    private employeeType type;

    Employee(){
        this.name = "No name yet";
        this.SSN = 0;
        this.address = "No address set";
        this.ID = 0;
    }
    Employee(String name , int ssn , String address, Gender sex, int ID, employeeType type){
        this.name = name;
        this.SSN = ssn;
        this.address = address;
        this.sex = sex;
        this.ID = ID;
        this.type = type;
    }
    Employee(Employee other){
        this.name = other.name;
        this.SSN = other.SSN;
        this.address = other.address;
        this.sex = other.sex;
        this.ID = other.ID;
        this.type = other.type;
    }

    // Getters.
    String getName(){
        return this.name;
    }
    int getID(){
        return this.ID;
    }
    String companyName(){
        return companyName;
    }
    employeeType getType() {
        return this.type;
    }

    void info() {
        System.out.println("Name : " + name + " || Gender : " + sex + " || Address : " + address + " || Type : " + type + " employee.");
        System.out.println("SSN : " + SSN + " || The ID : " + ID);
    }

    abstract double earning();
    abstract void manageDetails();
}
