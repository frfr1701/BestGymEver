package bestgymever.models;

import bestgymever.controller.*;
import java.util.*;

public class SuperModel {

    private IPerson user;
    private String username, password, returnStatement, name;
    private final List<Workout> tempWorkouts;
    private final List<String> viewList;
    private final Map<Integer, Member> members;
    private final Map<Integer, PersonalTrainer> personalTrainers;
    private final Map<Integer, Workout> workouts;
    private final Map<Integer, WorkoutRoom> workoutRooms;
    private final Map<Integer, WorkoutType> workoutTypes;
    private final Map<Integer, Booking> bookings;
    private final Map<Integer, Note> notes;
    private final List<Booking> tempBookings;

    public SuperModel() {
        this.bookings = new HashMap<>();
        this.viewList = new ArrayList<>();
        this.tempWorkouts = new ArrayList<>();

        this.members = new HashMap<>();
        this.personalTrainers = new HashMap<>();
        this.workouts = new HashMap<>();
        this.workoutRooms = new HashMap<>();
        this.workoutTypes = new HashMap<>();
        this.notes = new HashMap<>();
        this.tempBookings = new ArrayList<>();
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }

    public Map<Integer, Note> getNotes() {
        return notes;
    }

    public Map<Integer, Booking> getBookings() {
        return bookings;
    }

    public Map<Integer, PersonalTrainer> getPersonalTrainers() {
        return personalTrainers;
    }

    public Map<Integer, Workout> getWorkouts() {
        return workouts;
    }

    public Map<Integer, WorkoutRoom> getWorkoutRooms() {
        return workoutRooms;
    }

    public Map<Integer, WorkoutType> getWorkoutTypes() {
        return workoutTypes;
    }

    public List<String> getViewList() {
        return viewList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IPerson getUser() {
        return user;
    }

    public void setUser(IPerson user) {
        this.user = user;
    }
    
    public void update(FunInt funInt) {
        funInt.update(this);
    }

    public List<Booking> getTempBookings() {
        return tempBookings;
    }

    public List<Workout> getTempWorkouts() {
        return tempWorkouts;
    }

    public String getReturnStatement() {
        return returnStatement;
    }

    public void setReturnStatement(String returnStatement) {
        this.returnStatement = returnStatement;
    }
}
