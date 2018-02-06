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
    public SuperModel updateModel(String input) {
        
        model.getOrderMap().forEach((t, u) -> {
            u.getProductList().clear();
        });
        try {
            switch (state) {
                
            }
        } catch (IndexOutOfBoundsException e) {
            model.getViewList().clear();
            model.getViewList().add("You can't select that");
            AddMenyOptions();
            state = OPTION;
        }
        return model;
    }
    
}
