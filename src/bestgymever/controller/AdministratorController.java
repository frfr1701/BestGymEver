package bestgymever.controller;

import static bestgymever.controller.AdministratorState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class AdministratorController implements IController {

    SuperModel model;
    ConsoleView view;
    Repository repository;
    AdministratorState state;

    FunInt logIn = (m) -> repository.AdministratorlogIn(m, model.getUsername(), model.getPassword());
    FunInt createMember = (m) -> repository.addMember(model.getName(), model.getUsername(), model.getPassword());
    FunInt createReceptionist = (m) -> repository.addReceptionist(inName, inUsername, inPassword);
    FunInt createPersonalTrainer = (m) -> repository.addPersonalTrainer(inName, inUsername, inPassword);
    FunInt f1 = (m) -> repository.getMembers(m, "");
    FunInt f2 = (m) -> repository.mapNotesToMembers(m, "");
    FunInt f3 = (m) -> repository.mapBookingsToMembers(m, "");
    FunInt f4 = (m) -> repository.mapWorkoutsToBookings(m, "");
    FunInt f5 = (m) -> repository.getWorkouts(m, "");

    public AdministratorController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.view = view;
        this.repository = repository;
        state = START;
    }

    @Override
    public void updateModel(String input) {
        model.getViewList().clear();
        model.getViewList().clear();
        switch (state) {
            case USERNAME:
                model.setUsername(input);
                model.getViewList().add("Password");
                state = PASSWORD;
                break;
            case PASSWORD:
                model.setPassword(input);
                model.update(logIn);
                if (model.getUser() == null) {
                    model.getViewList().add("Wrong Username/Password");
                    state = USERNAME;
                    model.getViewList().add("Username");
                } else {
                    model.getViewList().add("Logged in as Admin");
                    AddMenyOptions();
                    state = MENU;
                }
                break;
            case MENU:
                switch (input) {
                    case "1":
                        
                        
                        break;
                    case "2":
                        
                        
                        break;
                    case "3":
                        
                        
                        break;
                    case "4":
                        
                        
                        break;
                    case "5":
                        
                        
                        break;
                    case "6":
                        state = USERNAME;
                        model.getViewList().add("Username");
                        break;
                }
                break;
            default:
                state=USERNAME;
                model.getViewList().add("Username");
        }
        updateView();
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
    
    private void AddMenyOptions() {
        model.getViewList().add("");
        model.getViewList().add("What do you wan't to do?");
        model.getViewList().add("[1] Create Workout");
        model.getViewList().add("[2] Create Member");
        model.getViewList().add("[3] Create Private Trainer");
        model.getViewList().add("[4] Create Admin");
        model.getViewList().add("[5] Create Receptionist");
        model.getViewList().add("[6] Log out");
    }
}
