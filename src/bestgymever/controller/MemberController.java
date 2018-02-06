package bestgymever.controller;

import bestgymever.models.*;


public class MemberController implements IController{
    
    private final SuperModel model;
    private State state;

    public MemberController(SuperModel model, State state) {
        this.model = model;
        this.state = State.START;
    }
    

    @Override
    public SuperModel updateModel() {
        return updateModel();
    }
    
}
