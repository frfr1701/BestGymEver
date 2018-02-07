package bestgymever.controller;

import bestgymever.models.SuperModel;
import static bestgymever.controller.ReceptionistState.*;
import bestgymever.repository.Repository;
import bestgymever.view.ConsoleView;

public class ReceptionistController {

    SuperModel model;
    ConsoleView view;
    Repository repository;
    ReceptionistState state;

    FunInt f1 = (m) -> repository.getMembers(m, "");
    FunInt f2 = (m) -> repository.mapBookingsToMembers(m, "");
    FunInt f3 = (m) -> repository.mapWorkoutsToBookings(m, "");

    public ReceptionistController(SuperModel model, ConsoleView view, Repository repository) {

        this.state = START;
        this.model = model;
        this.view = view;
        this.repository = repository;
    }

    public void updateModel(String input) {
        model.getViewList().clear();

        switch (state) {
            case USERNAME:

            case PASSWORD:

                break;
            default:
                model.getViewList().add("Write username: ");
                

                state = USERNAME;
        }

        updateView();
    }

    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }

}
