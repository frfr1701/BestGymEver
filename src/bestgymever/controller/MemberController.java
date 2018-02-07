package bestgymever.controller;

import static bestgymever.controller.MemberState.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class MemberController implements IController {

    private final SuperModel model;
    private MemberState state;
    private final ConsoleView view;
    private final Repository repository;

    public MemberController(SuperModel model, ConsoleView view, Repository repository) {
        this.model = model;
        this.state = START;
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void updateModel(String input) {
        model.getViewList().clear();
        switch(state){
            case USERNAME:
                model.setUsername(input);
                model.getViewList().add("Password");
                state=PASSWORD;
                break;
            case PASSWORD:
                model.setPassword(input);
                if(model.getMembers()==null){
                    model.getViewList().add("Wrong Username/Password");
                    model.getViewList().add("Username");
                }else{
                    model.getViewList().add("Welcome");
                    state = OPTION;
                    state=MENY;
                }
                break;
            case MENY:
                
                break;
            case OPTION:
                break;
            default:
                state=USERNAME;
                model.getViewList().add("Username");
                break;
        }
        updateView();
    }

    @Override
    public void updateView() {
         updateModel(view.display(model.getViewList()));
    }

}
