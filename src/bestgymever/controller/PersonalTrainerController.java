package bestgymever.controller;

import static bestgymever.controller.PersonalTrainerState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
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
        
        switch (state){
            case USERNAME:
                break;
            case PASSWORD:
                break; 
            default:    
                
                
                
        }
                
                
                
        
        updateView(); 
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
    
//    public void testlog(){
//        String inputUsr; 
//        String inputPsw; 
//        Scanner sc = new Scanner(System.in); 
//        System.out.println("Username:");
//        inputUsr = sc.nextLine(); 
//        System.out.println("Password:");
//        inputPsw = sc.nextLine(); 
//        System.out.println(repository.PersonalTrainerlogIn(inputUsr, inputPsw));
//    }
    
}
