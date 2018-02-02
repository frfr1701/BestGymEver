
package bestgymever.models;

public class WorkoutType {
    private final int id;
    private String name;

    public WorkoutType(int name, String id) {
        this.id = name;
        this.name = id;
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
