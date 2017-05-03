import java.util.Comparator;

/**
 * Created by qvk871 on 5/2/17.
 */
public class AdjustedPerson {
    String firstName,lastName,county;
    int zipCode,income;

    public AdjustedPerson(String fn,String ln,int zp,int inc,String count){
        this.firstName = fn;
        this.lastName = ln;
        this.zipCode = zp;
        this.income = inc;
        this.county = count;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getIncome() {
        return income;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    static class PersonCompare implements Comparator<AdjustedPerson>
    {
        @Override
        public int compare(AdjustedPerson o1, AdjustedPerson o2) {
            Integer a1 = o1.getIncome();
            Integer a2 = o2.getIncome();
            return a2.compareTo(a1);
        }
    }
}
