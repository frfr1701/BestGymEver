package bestgymever.view;

import java.util.*;
import bestgymever.controller.*;
import bestgymever.models.*;

public class View {

     controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void display(SuperModel model) {
        model.getViewList().forEach((string) -> {
            System.out.println(string);
        });
        run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        display(controller.updateModel(line));
    }
}