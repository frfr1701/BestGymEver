package bestgymever.models;

import java.util.*;

public class Workout {

    private final int id;
    private int avalibleSlots;
    private final PersonalTrainer personalTrainer;
    private final Date startDate, endDate;
    private final WorkoutRoom workoutRoom;
    private final WorkoutType workoutType;
    private final Map<Integer, Booking> bookings;

    public Workout(int id, PersonalTrainer personalTrainer, int avalibleSlots, Date startDate, Date endDate, WorkoutRoom workoutRoom, WorkoutType workoutName) {
        bookings = new HashMap<>();
        this.id = id;
        this.personalTrainer = personalTrainer;
        this.avalibleSlots = avalibleSlots;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workoutRoom = workoutRoom;
        this.workoutType = workoutName;
    }

    public int getId() {
        return id;
    }

    public int getAvalibleSlots() {
        return avalibleSlots;
    }

    public void setAvalibleSlots(int avalibleSlots) {
        this.avalibleSlots = avalibleSlots;
    }

    public Date getStarDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public WorkoutRoom getWorkoutRoom() {
        return workoutRoom;
    }

    public WorkoutType getWorkoutName() {
        return workoutType;
    }

    public Map<Integer, Booking> getBookings() {
        return bookings;
    }

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    public void addBookings(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public void removeBookings(Booking booking) {
        bookings.remove(booking.getId());
    }
}
