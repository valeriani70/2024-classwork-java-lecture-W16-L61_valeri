package ge.itvet.csvreaderlibrary;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * T-ს აუცილებლად თავზე უნდა ეწეროს @Csv ანოტაცია;
 *
 * @param <T>
 */
public class CsvReader<T> {
    private final Class<T> clazz;

    public CsvReader(Class<T> clazz) {
        this.clazz = clazz;

        if (!clazz.isAnnotationPresent(Csv.class)) {
            throw new IllegalArgumentException("The class " + clazz.getName() + " is not annotated with @Csv");
        }
    }

    public Set<T> read() {
        Csv declaredAnnotation = clazz.getDeclaredAnnotation(Csv.class);

        String filePath = declaredAnnotation.filePath();

        try {
            return Files.lines(Path.of(filePath))// ხსნის ფაილს path-ის მიხედვით, აბრუნებს line-ს
                    .skip(1)// გამოვტოვოთ 1 ხაზი რადგან სათარუები წერია;
                    .filter(line -> !line.isEmpty()) // გავფილტროთ ცარიელი ხაზები
                    .map(line -> line.split(","))
                    .map(this::arrayToModel)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private T arrayToModel(String[] line) {
        try {
            Constructor<T> defaultConstructor = clazz.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            T t = defaultConstructor.newInstance();


            Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(IndexInLine.class))
                    .forEach(f -> {
                        f.setAccessible(true);
                        int index = f.getAnnotation(IndexInLine.class).value();
                        try {
                            switch (f.getType().getCanonicalName()) {
                                case "long":
                                case "java.lang.Long":
                                    f.set(t, Long.parseLong(line[index]));
                                    break;
                                case "java.time.LocalDate":
                                    String format = f.getAnnotation(IndexInLine.class).dateFormat();
                                    f.set(t, LocalDate.parse(line[index], DateTimeFormatter.ofPattern(format)));
                                    break;
                                default:
                                    f.set(t, line[index]);
                            }

                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        // f.getType()
                        // f.set(t,);
                    });
            return t;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
