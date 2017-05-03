/**
 * Created by qvk871 on 4/19/17.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.*;
import static org.junit.Assert.assertTrue;





public class Submission {
    static final int [] totalAmount = new int[1];
    static List<String> countyNameList;
    static List<AdjustedPerson> adjustedPeople = new ArrayList<AdjustedPerson>();
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[1]);
        Stream<String> filePepsStreamed = Files.lines(path);
        path = Paths.get(args[0]);
        Stream<String> fileCountStreamed = Files.lines(path);
        countyNameList = new ArrayList<String>();
//People Filter
        Stream<People> peopleStream = filePepsStreamed.map(
         line ->{String [] l = line.split(",");
         int income = (int)Integer.parseInt(l[2]);
         int zip = (int)Integer.parseInt(l[3]);
         return new People(l[0],l[1],income,zip);
         }
        );
        List<People> people = peopleStream.collect(Collectors.toList());
//    County Filter
        Stream<County> countyStream = fileCountStreamed.map(
                (line)-> {
                    String[] l = line.split(",");
                    int zip = (int) Integer.parseInt(l[2]);
                    return new County(l[0], l[1], zip);
                }
        );
        List<County> county = countyStream.collect(Collectors.toList());
        County[] countyArray = new County[county.size()];
        county.toArray(countyArray);
        county.stream().forEach(
            r ->{
             if (!countyNameList.contains(r.getCounty())){
                 countyNameList.add(r.getCounty());
             }
            }
        );
        Stream<County> counties =  county.stream();
        Stream<Integer> countiesZip = counties.map(
                (county1)-> {
                   return  county1.getZip();
        });

        List<Integer> countyZips = countiesZip.collect(Collectors.toList());
        List<ArrayList<County>> countyList = new ArrayList<ArrayList<County>>();
        //Get avg income
        Stream<String> countyStream1 = countyNameList.stream();
        countyStream1.forEach(
                name ->{
                    ArrayList<County> list = new ArrayList<County>();
                    county.stream().forEach(
                            county1 -> {
                               if (county1.getCounty().equals(name)){
                                    list.add(county1);
                               }
                            }
                    );
                    countyList.add(list);
                }
        );
        Stream<ArrayList<County>> totalCalcuation = countyList.stream();
        final int[] count = {1};
        final int[] avgIncome = {0};
        //System.out.println(countyList.get(0));
        totalCalcuation.forEach(
                counties1 -> {
                    final String[] countName = {""};
                    List<People> personList = new ArrayList<People>();
                    List<People> combinedList = new ArrayList<People>();

                    counties1.stream().forEach(
                            county1 -> {
                                countName[0] = county1.getCounty();
                                people.stream().forEach(
                                        person->{
                                            if (person.getZipCode()==county1.getZip() && !county1.getState().equals("NC")){
                                                personList.add(person);
                                                combinedList.add(person);
                                            }
                                        }
                                );
                                if (count[0]<= counties1.size() && !county1.getState().equals("NC")){
                                    avgIncome[0] = avgIncome[0] + getAVGIncome(personList, county1.getZip());
                                }
                                count[0] = count[0] + 1;
                                if (count[0]>counties1.size() && combinedList.size()!=0) {
                                    List<AdjustedPerson> check = getRelativeIncome(combinedList, county1.getZip(), countName[0], avgIncome[0]/combinedList.size());
                                    check.stream().forEach(
                                            ap->{
                                                adjustedPeople.add(ap);
                                            }
                                    );
                                }
                                personList.clear();
                            }
                    );
                    avgIncome[0] = 0;
                    count[0] = 1;
                    combinedList.clear();
                }
        );
        Collections.sort(adjustedPeople,new AdjustedPerson.PersonCompare());
        adjustedPeople.stream().forEach(
                adjustedPerson -> {
                    System.out.println(adjustedPerson.getFirstName() + " " + adjustedPerson.getLastName() + " " + adjustedPerson.getIncome() + " " + adjustedPerson.county);

                }
        );
        //get zipcodes from each then compare between list make sure they are list of zipcodes
        //then check prior list of whole counties and the you should be
    }



    static Predicate<AdjustedPerson> checker(Integer integer)
    {
        return r-> (r.getZipCode()==integer);
    }



    private static ArrayList<AdjustedPerson> getRelativeIncome(List<People> peoples, Integer currentZip,String countyName,int avg) {
        ArrayList<AdjustedPerson> matchedPeople = new ArrayList<AdjustedPerson>();
        List<People> peopleList = new ArrayList<People>(peoples);
        List<People> dummyList = new ArrayList<People>();
        dummyList.add(new People("", "",0,0));
        Stream<People> relativeIncome = dummyList.stream();
        Stream<People> rIncome = peopleList.stream();
        relativeIncome.forEach(
            r->{
                peopleList.stream().forEach(t->{
                    matchedPeople.add(new AdjustedPerson(t.getFirstName(),t.getLastName(),t.getZipCode(),(t.getIncome()-(avg)),countyName));
                });
            }
        );
        return matchedPeople;
    }



    private static Integer getAVGIncome(List<People> wholeCounty, Integer zip) {
        totalAmount[0]=0;
        final int[] count = {0};
        Stream<People> tempers = wholeCounty.stream();
        tempers.forEach( item-> {
            if (item.getZipCode()==zip) {
                totalAmount[0] = totalAmount[0] + item.getIncome();
                count[0] = count[0] + 1;
            }
                }
        );
        return totalAmount[0];
    }
}
