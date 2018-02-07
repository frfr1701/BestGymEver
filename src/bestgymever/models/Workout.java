package bestgymever.models;

import java.time.*;
import java.util.*;

public class Workout implements IModel {

    private final int id;
    private int availableSlots;
    private final PersonalTrainer personalTrainer;
    private final LocalDateTime startDate, endDate;
    private final WorkoutRoom workoutRoom;
    private final WorkoutType workoutType;
    private final Map<Integer, Booking> bookings;

    public Workout(int id, PersonalTrainer personalTrainer, int avalibleSlots, LocalDateTime startDate, LocalDateTime endDate, WorkoutRoom workoutRoom, WorkoutType workoutType) {
        bookings = new HashMap<>();
        this.id = id;
        this.personalTrainer = personalTrainer;
        this.availableSlots = avalibleSlots;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workoutRoom = workoutRoom;
        this.workoutType = workoutType;
    }

    public int getId() {
        return id;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public WorkoutRoom getWorkoutRoom() {
        return workoutRoom;
    }

    public WorkoutType getWorkoutType() {
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
    
    @Override
    public String toString(){
        return String.valueOf("Availableslots: " + availableSlots + "\tpersonalTrainer: " 
                + personalTrainer + "\tStartdate-Enddate: " + startDate +"-"+endDate + "\tWorkoutroom: " + workoutRoom 
                + "\tWorkouttype: " + workoutType);
    }
}
