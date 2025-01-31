package ge.itvet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public final class PersonReader {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Set<Person> readPeople(String fileName) throws IOException {
       return Files.lines(Path.of(fileName))// ხსნის ფაილს path-ის მიხედვით, აბრუნებს line-ს
                .skip(1)// გამოვტოვოთ 1 ხაზი რადგან სათარუები წერია;
                .filter(line -> !line.isEmpty()) // გავფილტროთ ცარიელი ხაზები
                .map(line -> line.split(","))// დავყავით ცალ-ცალკე მნიშვლენლობებად
                .map(PersonReader::arrayToPerson) // გადავიყვანეთ ცალ-ცალკე მნიშვნელობები პერსონ ინსტანსში
                .collect(Collectors.toSet());// გადავიყვანოთ set-ში
    }

    private static Person arrayToPerson(String[] line) {
        return new Person(
                Long.parseLong(line[0]),// id
                line[1],//personal no
                line[2],// full name
                LocalDate.parse(line[3], formatter)// birthday
        );
    }
}
