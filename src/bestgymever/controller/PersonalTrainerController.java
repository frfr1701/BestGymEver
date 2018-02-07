package bestgymever.controller;

import static bestgymever.controller.PersonalTrainerState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
import java.util.Date;
import java.util.Scanner;

public class PersonalTrainerController implements IController {
    
    private final SuperModel model;
    private final ConsoleView view; 
    private Repository repository;
    private PersonalTrainerState state; 
   
    private final FunInt logIn = (m) -> repository.PersonalTrainerlogIn(m,m.getUsername(),m.getPassword());
    private final FunInt f1 = (m) -> repository.getPersonalTrainers(m, "");
    private final FunInt f2 = (m) -> repository.getMembers(m,""); 
    private final FunInt f3 = (m) -> repository.mapNotesToMembers(m, ""); 
    private final FunInt f4 = (m) -> repository.mapBookingsToMembers(m, ""); 
    // FunInt f5 = (m) -> repository.addNote("", ""); 

    public PersonalTrainerController(SuperModel model, ConsoleView view, Repository repository) {
        
        this.state = START; 
        this.model = model;
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
                model.update(logIn);
                if(model.getUser()==null){
                    model.getViewList().add("Wrong Username/Password");
                    state = USERNAME;
                    model.getViewList().add("Username");
                }else{
                    model.getViewList().add("Welcome " + model.getPersonalTrainers().get(model.getUser().getId()).getName());
                    AddMenyOptions();
                    state = OPTION;
                }
                break;
                
            case OPTION:
                switch(input){
                    case "1":
                        model.update(f2);
                        state = CHOOSEMEMBERWORKOUT;
                        break;
                    case "2":
                    state = CHOOSEMEMBERNOTE;
                        model.update(f2);
                        break;
                    case "3":
                    state = ADDMEMBERNOTE;
                        break;
                    case "4":
                    state = USERNAME;
                    model.getViewList().add("Username");
                    break;
                }
                break; 
            
            case CHOOSEMEMBERWORKOUT:
                model.getMembers().values().forEach((member) -> {
                    member.getBookings().values().stream()
                            .filter(booking -> booking.isCheckedIn() 
                                    && booking.getWorkout().getEndDate().before(new Date()))
                            .forEach((t) -> {
                                model.getViewList().add(t.toString());
                            });
            });
                
                break; 
                
            case CHOOSEMEMBERNOTE:
                model.getMembers().values().forEach((member) -> {
                    member.getNotes().values().stream()
                            .filter(note -> note.getNote().equalsIgnoreCase(member.getName()))
                            .forEach((t) -> {
                                model.getViewList().add(t.toString());
                            });
                    
            });
                
                
                break; 
                
            case ADDMEMBERNOTE:
                
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
        model.getViewList().add("[1] Check member workouts");
        model.getViewList().add("[2] Check member notes");
        model.getViewList().add("[3] Add member note");
        model.getViewList().add("[4] Log out");
    }
    
    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
    
}
