package bestgymever.controller;

import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class AdministratorController implements IController {

    SuperModel model;
    ConsoleView view;
    Repository repository;

    FunInt f1 = (m) -> repository.getMembers(m, "");
    FunInt f2 = (m) -> repository.mapNotesToMembers(m, "");
    FunInt f3 = (m) -> repository.mapBookingsToMembers(m, "");
    FunInt f4 = (m) -> repository.mapWorkoutsToBookings(m, "");
    

    public AdministratorController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void updateModel(String input) {
        model.getViewList().clear();
        
        model.update(f1.andThen(f2).andThen(f3).andThen(f4));
        
        model.getMembers().values().forEach((member) -> {
            model.getViewList().add(member.getName());
            member.getNotes().values().forEach((note) -> {
                model.getViewList().add(note.getNote());
            });
            model.getViewList().add("");
        });
        
        updateView();
    }

    @Override
    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }
}
