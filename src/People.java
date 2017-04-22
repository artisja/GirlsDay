/**
 * Created by qvk871 on 4/22/17.
 */
public class People {
    String firstName,lastName;
    int income,zipCode;

    public People(String first,String last,int money,int zip){
        this.firstName = first;
        this.lastName = last;
        this.income = money;
        this.zipCode = zip;
    }

    public int getIncome() {
        return income;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
