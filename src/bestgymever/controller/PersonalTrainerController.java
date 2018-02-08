package bestgymever.controller;

import static bestgymever.controller.PersonalTrainerState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class PersonalTrainerController implements IController {
    
    SuperModel model;
    ConsoleView view; 
    Repository repository;
    PersonalTrainerState state; 
   
    private final FunInt logIn = (m) -> repository.PersonalTrainerlogIn(m,m.getUsername(),m.getPassword());
    
    private final FunInt loadWorkouts = (m) -> repository.getWorkouts(m, "");
    private final FunInt loadMembers = (m) -> repository.getMembers(m, "");
    private final FunInt loadPersonalTrainer = (m) -> repository.getPersonalTrainers(m, "");
    private final FunInt mapNotesToMembers = (m) -> repository.mapNotesToMembers(m, ""); 
    private final FunInt mapBookingsToMembers = (m) -> repository.mapBookingsToMembers(m, ""); 
    private final FunInt mapWorkoutsToBookings = (m) -> repository.mapWorkoutsToBookings(m, "");
    private final FunInt addNote = (m) -> repository.addNote(m, model.getName(),"");

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
                    model.getViewList().add("Logged in as " + model.getPersonalTrainers().get(model.getUser().getId()).getName());
                    AddMenyOptions();
                    state = MENU;
                }
                break;
                
            case MENU:
                switch(input){
                    case "1":
                        model.update(loadMembers);
                        
                        model.getTempMembers().clear(); 
                        
                         model.getViewList().add("Choose Member");
                            model.getMembers().values().forEach((member) -> {
                                model.getTempMembers().add(member);
                                model.getViewList().add("[" + model.getTempMembers().size() + "]" + member.toString());
                        });
                        state = SHOWMEMBERWORKOUT;
                        break;
                    case "2":
                    model.update(loadMembers);
                        
                        model.getTempMembers().clear(); 
                        
                         model.getViewList().add("Choose Member");
                            model.getMembers().values().forEach((member) -> {
                                model.getTempMembers().add(member);
                                model.getViewList().add("[" + model.getTempMembers().size() + "]" + member.toString());
                        });
                        state = SHOWMEMBERNOTE;
                        break;
                    case "3":
                        model.update(loadMembers);
                        
                        model.getTempMembers().clear(); 
                        
                         model.getViewList().add("Choose Member");
                            model.getMembers().values().forEach((member) -> {
                                model.getTempMembers().add(member);
                                model.getViewList().add("[" + model.getTempMembers().size() + "]" + member.toString());
                        });
                        state = ADDMEMBERNOTE;
                        break;
                    case "4":
                    state = USERNAME;
                    model.getViewList().add("Username");
                    break;
                }
                break; 
            
            case SHOWMEMBERWORKOUT:
                model.update(loadMembers.andThen(mapBookingsToMembers).andThen(mapWorkoutsToBookings));
                
                model.getMembers().values().forEach((member) -> {
                    if(member.getId() ==  model.getTempMembers().get(Integer.parseInt(input)-1).getId()){
                        member.getBookings().values().stream()
                           .filter(booking -> booking.isCheckedIn() && booking.getWorkout().getEndDate().isBefore(LocalDateTime.now()))                   
                            .forEach((t) -> {
                                model.getViewList().add(t.getWorkout().BookingsAccessToString());
                            });
                    }
                });              
                    model.getViewList().add("Write exit to return to menu"); 
                    state = RETURNTOMENUOPTION;
                break; 
                
            case SHOWMEMBERNOTE:
                model.update(loadMembers.andThen(mapNotesToMembers));
                
                model.getMembers().values().forEach((member) -> {
                    if(member.getId() == model.getTempMembers().get(Integer.parseInt(input)-1).getId()){
                        member.getNotes().forEach(member);
                    }
            });

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
                
            case RETURNTOMENUOPTION: 
                if(input.equalsIgnoreCase("exit")){
                    AddMenyOptions(); 
                    state = MENU;  
                }
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
