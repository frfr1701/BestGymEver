package bestgymever.models;

import java.util.*;

public class SuperModel {

    private final List<String> viewList;
    
    private final Map<Integer, Member> members;
    private final Map<Integer, PersonalTrainer> personalTrainers;
    private final Map<Integer, Workout> workouts;
    private final Map<Integer, WorkoutRoom> workoutRooms;
    private final Map<Integer, WorkoutType> workoutTypes;
    

    public SuperModel() {
        this.viewList = new ArrayList<>();
        
        this.members = new HashMap<>();
        this.personalTrainers = new HashMap<>();
        this.workouts = new HashMap<>();
        this.workoutRooms = new HashMap<>();
        this.workoutTypes = new HashMap<>();
    }

    public Map<Integer, Member> getMembers() {
        return members;
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
}
