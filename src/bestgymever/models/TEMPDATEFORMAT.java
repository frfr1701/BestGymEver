package bestgymever.models;

import bestgymever.repository.*;
import java.text.*;
import java.util.*;

public class TEMPDATEFORMAT {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        Repository hej = new Repository();
        hej.logIn();
    }
}
