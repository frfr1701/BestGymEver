package bestgymever;

import bestgymever.controller.*;
import bestgymever.models.*;
import bestgymever.repository.*;
import bestgymever.view.*;
import java.util.*;

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
        Options();
        Scanner sc = new Scanner(System.in);
        
        choice:
        while(true){
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                AdministratorController controller = new AdministratorController(model, view, repository);
                controller.updateModel("");
                break choice;
                
            case "2":
                ReceptionistController controller2 = new ReceptionistController(model, view, repository);
                controller2.updateModel("");
                break choice;
                
            case "3":
                MemberController controller3 = new MemberController(model, view, repository);
                controller3.updateModel("");
                break choice;
                
            case "4":
                PersonalTrainerController controller4 = new PersonalTrainerController(model, view, repository);
                controller4.updateModel("");
                break choice;
      
            default:
                System.out.println("No Client match, try again!\n");
                Options();
                break;
        }
        }
    }

    private void Options() {
        System.out.println("Choose type of user\n"
                + "1. Administrator\n"
                + "2. Receptionist\n"
                + "3. Member\n"
                + "4. PersonalTrainer\n");
    }
}
