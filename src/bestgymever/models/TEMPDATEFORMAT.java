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
        SuperModel a = new SuperModel();
        
        hej.getMembers(a.getMembers(), "4");
        System.out.println(a.getMembers().size());
        
    }
}
