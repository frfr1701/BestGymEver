package bestgymever.controller;

import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class PersonalTrainerController implements IController {
    
    SuperModel model;
    ConsoleView view; 
    Repository repository;
   
    FunInt f1 = (m) -> repository.getPersonalTrainers(m, "");
    FunInt f2 = (m) -> repository.getMembers(m,""); 
    FunInt f3 = (m) -> repository.mapNotesToMembers(m, ""); 
    FunInt f4 = (m) -> repository.mapBookingsToMembers(m, ""); 
    // FunInt f5 = (m) -> repository.addNote("", ""); 

    public PersonalTrainerController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.view = view;
        this.repository = repository;
    }
    
    @Override
    public void updateModel(String input) {
        
        updateView(); 
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
    
}
