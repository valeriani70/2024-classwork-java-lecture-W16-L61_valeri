package ge.itvet;

import ge.itvet.csvreaderlibrary.CsvReader;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception{
        //PersonReader.readPeople("person.csv");
        CsvReader<Person> reader = new CsvReader<>(Person.class);
        System.out.println(" ");
        System.out.println(" პირადი მონაცემები");
        System.out.println(" ");
        reader.read().forEach(System.out::println);
        System.out.println(" ");
        CsvReader<Vehicle> vehicleReader = new CsvReader<>(Vehicle.class);
        System.out.println(" მანქანის მონაცემები");
        System.out.println(" ");
        vehicleReader.read().forEach(System.out::println);

        System.out.println(" ");
        CsvReader<Masters2Bench> Masters2Bench = new CsvReader<>(ge.itvet.Masters2Bench.class);
        System.out.println(" ");
        System.out.println("მასტერ 2 ვეტერანთა კატეგორიის მსოფლიოს რეკორდები წოლჭიმში ");
        System.out.println(" ");
Masters2Bench.read().forEach(System.out::println);


    }

    public static <T> T createInstance(Class<T> clazz) throws Exception {
        Constructor<T> defaultConstructor = clazz.getDeclaredConstructor();
        defaultConstructor.setAccessible(true);
        return defaultConstructor.newInstance();
    }
}
