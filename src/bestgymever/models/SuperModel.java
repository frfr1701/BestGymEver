package bestgymever.models;

import java.util.*;

public class SuperModel {

    private final Map<Integer, Member> members;
    private final Map<Integer, PersonalTrainer> personalTrainers;
    private final Map<Integer, Workout> workouts;
    private final Map<Integer, WorkoutRoom> workoutRooms;
    private final Map<Integer, WorkoutType> workoutTypes;
    

    public SuperModel() {
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
    
    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public void removeMember(Member member) {
        members.remove(member.getId());
    }

    public void addPersonalTrainer(PersonalTrainer personalTrainer) {
        personalTrainers.put(personalTrainer.getId(), personalTrainer);
    }

    public void removePersonalTrainer(PersonalTrainer personalTrainer) {
        personalTrainers.remove(personalTrainer.getId());
    }

    public void addWorkout(Workout workout) {
        workouts.put(workout.getId(), workout);
    }

    public void removeWorkout(Workout workout) {
        workouts.remove(workout.getId());
    }

    public void addWorkoutType(WorkoutType workoutType) {
        workoutTypes.put(workoutType.getId(), workoutType);
    }

    public void removeWorkoutType(WorkoutType workoutType) {
        workouts.remove(workoutType.getId());
    }

    public void addWorkoutRoom(WorkoutRoom workoutRoom) {
        workoutRooms.put(workoutRoom.getId(), workoutRoom);
    }

    public void removeWorkoutRoom(WorkoutRoom workoutRoom) {
        workoutRooms.remove(workoutRoom.getId());
    }
}
