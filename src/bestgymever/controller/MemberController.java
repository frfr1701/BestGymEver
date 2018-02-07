package bestgymever.controller;

import static bestgymever.controller.MemberState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class MemberController implements IController {
    
    private Repository repository;

    private final SuperModel model;
    private final ConsoleView view;
    private MemberState state;
    
    private final FunInt login = (m) -> repository.MemberlogIn(m,m.getUsername(), m.getPassword());
    private final FunInt getBookings = (m) -> repository.mapBookingsToMembers(m, String.valueOf(m.getUser().getId()));
    private final FunInt getWorkouts = (m) -> repository.getWorkouts(m, "");
    
    public MemberController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.state = START;
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void updateModel(String input) {
        model.getViewList().clear();
        switch(state){
            case USERNAME:
                model.setUsername(input);
                model.getViewList().add("Password");
                state=PASSWORD;
                break;
            case PASSWORD:
                model.setPassword(input);
                model.update(login);
                if(model.getUser()==null){
                    model.getViewList().add("Wrong Username/Password");
                    state = USERNAME;
                    model.getViewList().add("Username");
                }else{
                    model.getViewList().add("Welcome " + model.getMembers().get(model.getUser().getId()).getName());
                    AddMenyOptions();
                    state = OPTION;
                }
                break;
            case OPTION:
                switch(input){
                    case "1":
                    state = BOOKING;
                        break;
                    case "2":
                    state = BOOKINGS;
                        break;
                    case "3":
                    state = USERNAME;
                    model.getViewList().add("Username");
                    break;
                }
                break;    
            case MENY:
                
                break;
            case BOOKING:
                break;    
            case BOOKINGS:
                break;
            default:
                state=USERNAME;
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
