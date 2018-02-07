package bestgymever;

import bestgymever.controller.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;

public class BestGymEver {

//    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Date date = new Date();
//    System.out.println(dateFormat.format(date));
    
    public static void main(String[] args) {
        BestGymEver go = new BestGymEver();
        go.go();
    }

    private void go() {
        Repository repository = new Repository();
        SuperModel model = new SuperModel();
        ConsoleView view = new ConsoleView();
        ReceptionistController controller = new ReceptionistController(model, view, repository);
        controller.updateModel("");
    }
}
