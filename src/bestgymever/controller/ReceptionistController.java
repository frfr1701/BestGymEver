package bestgymever.controller;

import bestgymever.models.SuperModel;
import static bestgymever.controller.ReceptionistState.*;
import bestgymever.repository.Repository;
import bestgymever.view.*;
import java.time.*;
import java.util.stream.Collectors;

public class ReceptionistController {

    SuperModel model;
    IView view;
    Repository repository;
    ReceptionistState state;

    FunInt getmembers = (m) -> repository.getMembers(m, "");
    FunInt getbookingsformembers = (m) -> repository.mapBookingsToMembers(m, "");
    FunInt getworkoutsforbookings = (m) -> repository.mapWorkoutsToBookings(m, "");
    FunInt login = (m) -> repository.ReceptionistlogIn(m, model.getUsername(), model.getPassword());
    FunInt checkIn = (m) -> repository.checkInMember(m, model.getBookingID());

    public ReceptionistController(SuperModel model, IView view, Repository repository) {

        this.state = START;
        this.model = model;
        this.view = view;
        this.repository = repository;
    }

    public void updateModel(String input) {
        try {
            model.getViewList().clear();
            switch (state) {
                case USERNAME:
                    model.setUsername(input);
                    state = PASSWORD;
                    model.getViewList().add("Write password:");
                    break;
                case PASSWORD:
                    model.setPassword(input);
                    model.update(login);
                    if (model.getUser() == null) {
                        model.getViewList().add("User not found | Write username:");
                        state = USERNAME;
                    } else {
                        model.update(getmembers.andThen(getbookingsformembers).andThen(getworkoutsforbookings));
                        model.getTempMembers().clear();
                        model.getTempBookings().clear();
                        if (!model.getBookings().values().stream().filter((booking) -> (!booking.isCheckedIn()) && (booking.getWorkout().getStartDate().getDayOfYear()==LocalDateTime.now().getDayOfYear()) &&
                                (booking.getWorkout().getStartDate().getYear()==LocalDateTime.now().getYear())).collect(Collectors.toList()).isEmpty()) {
                            model.getViewList().add("Which member would you like to checkin?");
                            model.getMembers().values().forEach((member) -> {
                                member.getBookings().values().stream()
                                        .filter((booking) -> (!booking.isCheckedIn()) && (booking.getWorkout().getStartDate().getDayOfYear()==LocalDateTime.now().getDayOfYear()) &&
                                (booking.getWorkout().getStartDate().getYear()==LocalDateTime.now().getYear())).forEach((booking) -> {
                                    if (!model.getTempMembers().contains(member)) {
                                        model.getTempMembers().add(member);
                                        model.getViewList().add("[" + model.getTempMembers().size() + "] " + member.toString());
                                    }
                                });
                            });
                            state = CHOOSEMEMBER;
                        } else {
                            model.getViewList().add("No members to check in, you get logged out");
                            state = START;
                            model.clearUser();
                        }
                    }
                    break;
                case CHOOSEMEMBER:
                    if (model.getTempMembers().isEmpty()) {
                        model.getViewList().add("FUCK U");
                        state = MENU;
                    } else {
                        model.getViewList().add("Which workout do you want to check in?");
                        model.getMembers().values().forEach((member) -> {
                            if (member == model.getTempMembers().get(Integer.parseInt(input) - 1)) {
                                member.getBookings().values().stream()
                                        .filter(booking -> !booking.isCheckedIn())
                                        .forEach((booking) -> {
                                            model.getTempBookings().add(booking);
                                            model.getViewList().add("[" + model.getTempBookings().size() + "] " + booking.getWorkout().BookingsAccessToString());
                                        });
                            }
                        });
                        state = MEMBERCHECKEDIN;
                    }
                    break;
                case MEMBERCHECKEDIN:
                    model.setBookingID(String.valueOf(model.getTempBookings().get(Integer.parseInt(input) - 1).getId()));
                    model.update(checkIn);
                    model.getViewList().add(model.getReturnStatement());
                    state = PASSWORD;
                    break;
                default:
                    model.getViewList().add("Write username:");
                    state = USERNAME;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("You can't select that");
            state = PASSWORD;
        }
        updateView();
    }

    public void updateView() {
        updateModel(view.display(model.getViewList()));
    }

}
