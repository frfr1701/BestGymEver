package bestgymever.models;

public class Booking {

    private final int id;
    private boolean checkedIn;
    private final Member member;
    private final Workout workout;

    public Booking(int id, boolean checkedIn, Member member, Workout workout) {
        this.id = id;
        this.checkedIn = checkedIn;
        this.member = member;
        this.workout = workout;
    }

    public int getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Workout getWorkout() {
        return workout;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
