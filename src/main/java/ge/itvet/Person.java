package ge.itvet;

import ge.itvet.csvreaderlibrary.Csv;
import ge.itvet.csvreaderlibrary.IndexInLine;

import java.time.LocalDate;

@Csv(filePath = "person.csv")
public class Person {

    @IndexInLine(0)
    private final long id;
    @IndexInLine(value = 1)
    private final String personalNo;
    @IndexInLine(2)
    private final String fullName;
    @IndexInLine(value = 3, isDate = true)
    private final LocalDate birthDate;
    private Long beso;

    public Person() {
        this(0, "", "", LocalDate.now());
    }

    public Person(long id, String personalNo, String fullName, LocalDate birthDate) {
        this.id = id;
        this.personalNo = personalNo;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personalNo='" + personalNo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
