package bestgymever.controller;

import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class AdministratorController {
    SuperModel model;
    ConsoleView view;
    Repository repository;

    public AdministratorController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.view = view;
        this.repository = repository;
    }

    public void updateModel(String input) {
        model.getViewList().clear();
        
        repository.getMembers(model.getMembers(), input);
        
        model.getMembers().values().stream().forEach((t) -> {
            model.getViewList().add(t.getName());
        });
        
        updateView();
    }
    
    public void updateView(){
        updateModel(view.display(model.getViewList()));
    }
}
