/**
 * Created by qvk871 on 4/19/17.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;




public class Submission {
    public static void main(String[] args) throws IOException {

        String fileName = "people.txt";
        Path path = Paths.get("/Users/qvk871/people.txt");
        Stream<String> filePepsStreamed = Files.lines(path);
        path = Paths.get("/Users/qvk871/counties.txt");
        Stream<String> fileCountStreamed = Files.lines(path);
        Stream<People> peopleStream = filePepsStreamed.map(
         line ->{String [] l = line.split(",");
         int income = (int)Integer.parseInt(l[2]);
         int zip = (int)Integer.parseInt(l[3]);
         return new People(l[0],l[1],income,zip);
         }
        );
        Stream<County> countyStream = fileCountStreamed.map(
                (line)-> {
                    String[] l = line.split(",");
                    int zip = (int) Integer.parseInt(l[2]);
                    return new County(l[0], l[1], zip);
                }
        );

        countyStream.forEach(
                (county -> System.out.println(county.getZip()))
        );
        peopleStream.forEach(
                (person) ->{
                    System.out.println(person.getFirstName());
                }
        );

        List<String> ManCityStars = new ArrayList<String>();
        ManCityStars.add("Raheem Sterling");
        ManCityStars.add("David Silva");
        ManCityStars.add("Kevin DeBruyne");
        ManCityStars.add("Leroy Sane");
        ManCityStars.add("Sergio Aguero");
        ManCityStars.add("Fernandinho");
        ManCityStars.add("Vincent Kompany");
        ManCityStars.add("John Stones");

        ManCityStars.forEach(
                (name) -> {
                    System.out.println(name);
                }
        );

        int count = 0;
        ManCityStars.stream().map(
                (String name) -> name.length()
        ).forEach(System.out::println);
    }
}
