package bestgymever.controller;

import static bestgymever.controller.MemberState.*;
import bestgymever.models.*;

public class MemberController implements IController {

    private final SuperModel model;
    private MemberState state;

    public MemberController(SuperModel model, MemberState state) {
        this.model = model;
        this.state = START;
    }

    @Override
    public void updateModel(String input) {

    }

    @Override
    public void updateView() {
    }

}
