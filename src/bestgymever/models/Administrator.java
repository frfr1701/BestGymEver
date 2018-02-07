package bestgymever.models;

public class Administrator implements IPerson{

    private final int id;

    public Administrator(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
