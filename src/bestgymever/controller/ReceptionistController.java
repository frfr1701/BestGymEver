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
    
    FunInt getmembers = (m) -> repository.getMembers(m, "");
    FunInt getbookingsformembers = (m) -> repository.mapBookingsToMembers(m, "");
    FunInt getworkoutsforbookings = (m) -> repository.mapWorkoutsToBookings(m, "");
    FunInt login = (m) -> repository.ReceptionistlogIn(m, model.getUsername(), model.getPassword());
   

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
                model.setUsername(input);
                state = PASSWORD;
                model.getViewList().add("Write password: ");
                break;

            case PASSWORD:

                model.setPassword(input);
                model.update(login);
                if (model.getUser() == null) {
                    state = START;
                }
                else {
                    model.update(getmembers.andThen(getbookingsformembers).andThen(getworkoutsforbookings));
                    model.getWorkouts().values().forEach((workout) -> { 
                        model.getViewList().add(workout.toString());
                    });
                    state = CHOOSEWORKOUT;
                    break;
                }
               break;
                
            case CHOOSEWORKOUT:
                model.getViewList().add("Which workout would you like to add to? ");
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
