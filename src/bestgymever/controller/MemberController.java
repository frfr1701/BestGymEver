package bestgymever.controller;

import static bestgymever.controller.MemberState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
import java.util.*;

public class MemberController implements IController {

    private Repository repository;

    private final SuperModel model;
    private final ConsoleView view;
    private MemberState state;
    private String input;

    private final FunInt login = (m) -> repository.MemberlogIn(m, m.getUsername(), m.getPassword());
    private final FunInt getMyBookings = (m) -> repository.mapBookingsToMembers(m, String.valueOf(m.getUser().getId()));
    private final FunInt getMyWorkouts = (m) -> repository.mapWorkoutsToBookings(m, "");
    private final FunInt getWorkouts = (m) -> repository.getWorkouts(m, "");

    public MemberController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.state = START;
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void updateModel(String input) {
        this.input = input;
        model.getViewList().clear();
        switch (state) {
            case USERNAME:
                model.setUsername(input);
                model.getViewList().add("Password");
                state = PASSWORD;
                break;
            case PASSWORD:
                model.setPassword(input);
                model.update(login);
                if (model.getUser() == null) {
                    model.getViewList().add("Wrong Username/Password");
                    state = USERNAME;
                    model.getViewList().add("Username");
                } else {
                    model.getViewList().add("Welcome " + model.getMembers().get(model.getUser().getId()).getName());
                    AddMenyOptions();
                    state = OPTION;
                }
                break;
            case OPTION:
                switch (input) {
                    case "1":
                        model.update(getWorkouts);
                        model.getWorkouts().forEach((t, working) -> {
                            if(working.getStarDate().after(new Date())){
                                model.getTempWorkouts().add(working);
                                model.getViewList().add("["+ model.getTempWorkouts().size() +"] Type of workout: " + working.getWorkoutType().getName() + ", room: " + working.getWorkoutRoom().getName() + ", pt: " + working.getPersonalTrainer().getName() + ", start time: " + working.getStarDate() + ", end time: " + working.getEndDate());
                                model.getViewList().add("Choose workout to book or write exit to get to menu");
                            }
                        });
                        state = BOOKING;
                        break;
                    case "2":
                        model.update(getMyBookings);
                        model.update(getMyWorkouts);
                        model.getBookings().forEach((t, booking) -> {
                           if(booking.getWorkout().getStarDate().after(new Date())){
                               model.getTempBookings().add(booking);
                               model.getViewList().add("["+ model.getTempBookings().size() +"] Type of workout: " + booking.getWorkout().getWorkoutType().getName() + ", room: " + booking.getWorkout().getWorkoutRoom().getName() + ", pt: " + booking.getWorkout().getPersonalTrainer().getName() + ", start time: " + booking.getWorkout().getStarDate() + ", end time: " + booking.getWorkout().getEndDate());
                         }
                         model.getViewList().add("Choose workout to book or exit to menu");
                        });
                        state = BOOKINGS;
                        break;
                    case "3":
                        state = USERNAME;
                        model.getViewList().add("Username");
                        break;
                }
                break;
                
            case BOOKING:
                
                break;
            case BOOKINGS:
                        
                break;
            default:
                state = USERNAME;
                model.getViewList().add("Username");
                break;
        }
        updateView();
    }

    private void AddMenyOptions() {
        model.getViewList().add("");
        model.getViewList().add("What do you wan't to do?");
        model.getViewList().add("[1] Book a workout");
        model.getViewList().add("[2] Go to booked workouts");
        model.getViewList().add("[3] Log out");
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }

}
