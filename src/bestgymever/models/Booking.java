
package bestgymever.models;

public class Booking {
    private final int id;
    private boolean checkedIn;
    
    public Booking(int id) {
        this.id = id;
        this.checkedIn = false;
    }

    public Booking(int id, boolean checkedIn) {
        this.id = id;
        this.checkedIn = checkedIn;
    }

    public int getId() {
        return id;
    }
    
    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
