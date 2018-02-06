package bestgymever.controller;

import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class AdministratorController implements IController {

    SuperModel model;
    ConsoleView view;
    Repository repository;
    
    private int id;

    FunInt f1 = (m) -> repository.getMembers(m, "");
    FunInt f2 = (m) -> repository.mapNotesToMembers(m, "");

    public AdministratorController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.view = view;
        this.repository = repository;
        
        this.id = -1;
    }

    @Override
    public void updateModel(String input) {
        model.getViewList().clear();
        
        model.update(f1.andThen(f2));
//        model.update(f1).update(f2);
        
        model.getMembers().values().forEach((member) -> {
            model.getViewList().add(member.getName());
            member.getNotes().values().forEach((note) -> {
                model.getViewList().add(note.getNote());
            });
        });
        
        updateView();
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
}
