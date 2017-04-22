/**
 * Created by qvk871 on 4/22/17.
 */
public class County {
    String county,state;
    int zip;

    public County(String count,String stat,int zipCode ){
        this.county = count;
        this.state = stat;
        this.zip = zipCode;
    }

    public int getZip() {
        return zip;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }
}
