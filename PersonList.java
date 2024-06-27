import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonList {
    private List<Person> persons;

    public PersonList() {
        persons = new ArrayList<>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public Person findByName(String name) {
        if (name == null || !name.matches("^[a-zA-Z]+ [a-zA-Z]+$")) {
            throw new IllegalArgumentException("Name must be properly formatted as 'firstName lastName'");
        }
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public Person clone(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Argument must be a Person object");
        }
        int newId = persons.stream().mapToInt(Person::getId).max().orElse(0) + 1;
        return new Person(newId, person.getName(), person.getAge(), person.getOccupation());
    }

    public void writePersonToFile(Person person, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(person.toString());
        } catch (IOException e) {
            System.err.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}