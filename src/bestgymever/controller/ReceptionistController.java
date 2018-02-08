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
                } else {
                    state = CHOOSEMEMBER;
                    model.update(getmembers.andThen(getbookingsformembers).andThen(getworkoutsforbookings));
                    model.getMembers().values().forEach((member) -> {
                        member.getBookings().values().stream()
                                .filter((booking) -> !booking.isCheckedIn())
                                .forEach((booking) -> {
                                    model.getTempBookings().add(booking);
                                    model.getViewList().add("["+model.getTempBookings().size()+"] " + booking.getWorkout().toString());
                                    model.getViewList().add("Which workout would you like to add to? ");
                        });
                    });
                    state = CHOOSEMEMBER;
                }
                break;

            case CHOOSEMEMBER:
                model.getViewList().add("test ");
                
                 model.update(getmembers.andThen(getbookingsformembers));
                    model.getMembers().values().forEach((member) -> {
                        member.getBookings().values().stream()
                                .forEach((booking) -> {
                                    model.getTempBookings().add(booking);
                                    model.getViewList().add("["+model.getTempBookings().size()+"] " + booking.getWorkout().toString());
                                    model.getViewList().add("Which workout would you like to add to? ");
                        });
                    });
                
                
                
                
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
