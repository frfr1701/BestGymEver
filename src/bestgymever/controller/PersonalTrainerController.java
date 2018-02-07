package bestgymever.controller;

import static bestgymever.controller.PersonalTrainerState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
import java.util.Date;
import java.util.Scanner;

public class PersonalTrainerController implements IController {
    
    SuperModel model;
    ConsoleView view; 
    Repository repository;
    PersonalTrainerState state; 
   
    FunInt f1 = (m) -> repository.getPersonalTrainers(m, "");
    FunInt f2 = (m) -> repository.getMembers(m,""); 
    FunInt f3 = (m) -> repository.mapNotesToMembers(m, ""); 
    FunInt f4 = (m) -> repository.mapBookingsToMembers(m, ""); 
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
        
        model.update(f1.andThen(f2).andThen(f3).andThen(f4));
        
        switch (state){
            case USERNAME:
                
                break;
            case PASSWORD:
                
                break; 
            
            case MENY:
                model.getMembers().values().forEach((member) -> {
                    model.getViewList().add(member.getName());
                });
                
                break;
            case CHOOSEFORNOTE:
                
                
                break; 
            case CHOOSEFORBOOKING:
                model.getMembers().values().forEach((member) -> {
                    member.getBookings().values().stream()
                            .filter(booking -> booking.isCheckedIn() 
                                    && booking.getWorkout().getEndDate().before(new Date()))
                            .forEach((t) -> {
                                model.getViewList().add(t.toString());
                            });
            });
                
                break; 
            default:    
                
                
                
        }
        updateView(); 
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
    
}
