
package bestgymever.models;

public class PersonalTrainer {
    private final int id;
    private String name;

    public PersonalTrainer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
