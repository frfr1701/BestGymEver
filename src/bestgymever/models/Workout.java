
package bestgymever.models;

import java.time.*;
import java.util.*;


public class Workout {
    private final int id;
    private int avalibleSlots;
    private PersonalTrainer personalTrainer;
    LocalDateTime starDate, endDate;
    private WorkoutRoom workoutRoom;
    private WorkoutType workoutType;
    private final Map<Integer, Booking> bookings;

    public Workout(int id, PersonalTrainer personalTrainer, int avalibleSlots, LocalDateTime starDate, LocalDateTime endDate, WorkoutRoom workoutRoom, WorkoutType workoutName) {
        bookings=new HashMap<>();
        this.id = id;
        this.personalTrainer=personalTrainer;
        this.avalibleSlots = avalibleSlots;
        this.starDate = starDate;
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

    public LocalDateTime getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDateTime starDate) {
        this.starDate = starDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public WorkoutRoom getWorkoutRoom() {
        return workoutRoom;
    }

    public void setWorkoutRoom(WorkoutRoom workoutRoom) {
        this.workoutRoom = workoutRoom;
    }

    public WorkoutType getWorkoutName() {
        return workoutType;
    }

    public void setWorkoutName(WorkoutType workoutName) {
        this.workoutType = workoutName;
    }
    
    public Map<Integer, Booking> getBookings() {
        return bookings;
    }

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

    
    public void addBookings(Booking booking) {
        bookings.put(booking.getId(), booking);
    }
    
    public void removeBookings(Booking booking) {
        bookings.remove(booking.getId());
    }
}
