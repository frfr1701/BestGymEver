package bestgymever.repository;

import bestgymever.models.*;
import java.sql.*;
import static java.sql.ResultSet.*;
import java.util.*;

public class Repository {

    PropertiesReader pr;
    String query;
    ResultSet rs;

    public Repository() {
        this.pr = new PropertiesReader();
        pr.loadProperties();
    }

    private void oneOrAll(String input) {
        query = isLongerThanOne(input) ? query + " where ID = ?" : query;
    }

    private void oneOrAllMemberID(String input) {
        query = isLongerThanOne(input) ? query + " where Member_ID = ?" : query;
    }

    private void oneOrAllBookingID(String input) {
        query = isLongerThanOne(input) ? query + " where Booking_ID = ?" : query;
    }

    private Boolean isLongerThanOne(String input) {
        return input.length() > 0;
    }

    public int ReceptionistlogIn(String username, String password) {
        query = "SELECT * FROM BestGymEver.ReceptionistLogin where Username = ? and Password = ?";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return -1;
    }

    public int AdministratorlogIn(String username, String password) {
        query = "SELECT * FROM BestGymEver.AdministratorLogin where Username = ? and Password = ?";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return -1;
    }

    public int PersonalTrainerlogIn(String username, String password) {
        query = "SELECT * FROM BestGymEver.PersonalTrainerLogin where Username = ? and Password = ?";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return -1;
    }

    public int MemberlogIn(String username, String password) {
        query = "SELECT * FROM BestGymEver.MemberLogin where Username = ? and Password = ?";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return -1;
    }

    public void getMembers(Map<Integer, Member> members, String member) {
        query = "SELECT * FROM BestGymEver.Member";
        oneOrAll(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!members.containsKey(rs.getInt("ID"))) {
                    members.put(rs.getInt("ID"), new Member(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    members.get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void getPersonalTrainers(Map<Integer, PersonalTrainer> personalTrainers, String personalTrainer) {
        query = "SELECT * FROM BestGymEver.PersonalTrainer";
        oneOrAll(personalTrainer);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(personalTrainer)) {
                stmt.setString(1, personalTrainer);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!personalTrainers.containsKey(rs.getInt("ID"))) {
                    personalTrainers.put(rs.getInt("ID"), new PersonalTrainer(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    personalTrainers.get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void getWorkoutRooms(Map<Integer, WorkoutRoom> workoutRooms, String workoutRoom) {
        query = "SELECT * FROM BestGymEver.WorkoutRoom";
        oneOrAll(workoutRoom);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workoutRoom)) {
                stmt.setString(1, workoutRoom);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!workoutRooms.containsKey(rs.getInt("ID"))) {
                    workoutRooms.put(rs.getInt("ID"), new WorkoutRoom(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    workoutRooms.get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void getWorkoutTypes(Map<Integer, WorkoutType> workoutTypes, String workoutType) {
        query = "SELECT * FROM BestGymEver.WorkoutType";
        oneOrAll(workoutType);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workoutType)) {
                stmt.setString(1, workoutType);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!workoutTypes.containsKey(rs.getInt("ID"))) {
                    workoutTypes.put(rs.getInt("ID"), new WorkoutType(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    workoutTypes.get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void getWorkouts(Map<Integer, Workout> workouts,
            Map<Integer, WorkoutType> workoutTypes,
            Map<Integer, WorkoutRoom> workoutRooms,
            Map<Integer, PersonalTrainer> personalTrainers,
            String workout) {
        query = "SELECT * FROM BestGymEver.GetWorkouts";
        oneOrAll(workout);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workout)) {
                stmt.setString(1, workout);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!workouts.containsKey(rs.getInt("Workout_ID"))) {
                    
                    if (!workoutRooms.containsKey(rs.getInt("WorkoutRoom_ID"))) {
                        workoutRooms.put(rs.getInt("WorkoutRoom_ID"), new WorkoutRoom(rs.getInt("WorkoutRoom_ID"), rs.getString("WorkoutRoom_Name")));
                    } else {
                        workoutRooms.get(rs.getInt("WorkoutRoom_ID")).setName(rs.getString("WorkoutRoom_Name"));
                    }
                    if (!workoutTypes.containsKey(rs.getInt("WorkoutType_ID"))) {
                        workoutTypes.put(rs.getInt("WorkoutType_ID"), new WorkoutType(rs.getInt("WorkoutType_ID"), rs.getString("WorkoutType_Name")));
                    } else {
                        workoutTypes.get(rs.getInt("WorkoutType_ID")).setName(rs.getString("WorkoutType_Name"));
                    }
                    if (!personalTrainers.containsKey(rs.getInt("PersonalTrainer_ID"))) {
                        personalTrainers.put(rs.getInt("PersonalTrainer_ID"), new PersonalTrainer(rs.getInt("PersonalTrainer_ID"), rs.getString("PersonalTrainer_Name")));
                    } else {
                        personalTrainers.get(rs.getInt("PersonalTrainer_ID")).setName(rs.getString("PersonalTrainer_Name"));
                    }
                    
                    workouts.put(rs.getInt("Workout_ID"), new Workout(rs.getInt("Workout_ID"),
                            personalTrainers.get(rs.getInt("PersonalTrainer_ID")),
                            rs.getInt("AvailableSlots"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            workoutRooms.get(rs.getInt("WorkoutRoom_ID")),
                            workoutTypes.get(rs.getInt("WorkoutType_ID"))));
                    
                    workoutRooms.get(rs.getInt("WorkoutRoom_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                    workoutTypes.get(rs.getInt("WorkoutType_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                    personalTrainers.get(rs.getInt("PersonalTrainer_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                } else {
                    workouts.get(rs.getInt("Workout_ID")).setAvalibleSlots(rs.getInt("AvailableSlots"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void mapNotesToMembers(Map<Integer, Note> notes, Map<Integer, Member> members, String member) {
        query = "SELECT * FROM Note";
        oneOrAllMemberID(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!notes.containsKey(rs.getInt("ID"))) {
                    if (members.containsKey(rs.getInt("Member_ID"))) {
                        Note note = new Note(rs.getInt("ID"), rs.getString("Note"), members.get(rs.getInt("Member_ID")));

                        notes.put(rs.getInt("ID"), note);
                        members.get(rs.getInt("Member_ID")).addNote(note);
                    }
                } else {
                    notes.get(rs.getInt("ID")).setNote(rs.getString("Note"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void mapWorkoutsToBookings(Map<Integer, Workout> workouts,
            Map<Integer, Booking> bookings,
            Map<Integer, WorkoutType> workoutTypes,
            Map<Integer, WorkoutRoom> workoutRooms,
            Map<Integer, PersonalTrainer> personalTrainers,
            String booking) {
        query = "SELECT * FROM GetWorkoutsWithBooking";
        oneOrAllBookingID(booking);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(booking)) {
                stmt.setString(1, booking);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (bookings.containsKey(rs.getInt("Booking_ID"))) {
                    if (!workouts.containsKey(rs.getInt("Workout_ID"))) {

                        if (!workoutRooms.containsKey(rs.getInt("WorkoutRoom_ID"))) {
                            workoutRooms.put(rs.getInt("WorkoutRoom_ID"), new WorkoutRoom(rs.getInt("WorkoutRoom_ID"), rs.getString("WorkoutRoom_Name")));
                        } else {
                            workoutRooms.get(rs.getInt("WorkoutRoom_ID")).setName(rs.getString("WorkoutRoom_Name"));
                        }
                        if (!workoutTypes.containsKey(rs.getInt("WorkoutType_ID"))) {
                            workoutTypes.put(rs.getInt("WorkoutType_ID"), new WorkoutType(rs.getInt("WorkoutType_ID"), rs.getString("WorkoutType_Name")));
                        } else {
                            workoutTypes.get(rs.getInt("WorkoutType_ID")).setName(rs.getString("WorkoutType_Name"));
                        }
                        if (!personalTrainers.containsKey(rs.getInt("PersonalTrainer_ID"))) {
                            personalTrainers.put(rs.getInt("PersonalTrainer_ID"), new PersonalTrainer(rs.getInt("PersonalTrainer_ID"), rs.getString("PersonalTrainer_Name")));
                        } else {
                            personalTrainers.get(rs.getInt("PersonalTrainer_ID")).setName(rs.getString("PersonalTrainer_Name"));
                        }

                        workouts.put(rs.getInt("Workout_ID"), new Workout(rs.getInt("Workout_ID"),
                                personalTrainers.get(rs.getInt("PersonalTrainer_ID")),
                                rs.getInt("AvailableSlots"),
                                rs.getDate("StartDate"),
                                rs.getDate("EndDate"),
                                workoutRooms.get(rs.getInt("WorkoutRoom_ID")),
                                workoutTypes.get(rs.getInt("WorkoutType_ID"))));
                        
                        workoutRooms.get(rs.getInt("WorkoutRoom_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                        workoutTypes.get(rs.getInt("WorkoutType_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                        personalTrainers.get(rs.getInt("PersonalTrainer_ID")).addWorkout(workouts.get(rs.getInt("Workout_ID")));
                    } else {
                        workouts.get(rs.getInt("Workout_ID")).setAvalibleSlots(rs.getInt("AvailableSlots"));
                    }
                }
                bookings.get(rs.getInt("Booking_ID")).setWorkout(workouts.get(rs.getInt("Workout_ID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void mapBookingsToMembers(Map<Integer, Booking> bookings, Map<Integer, Member> members, String member) {
        query = "SELECT * FROM Booking";
        oneOrAllMemberID(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (members.containsKey(rs.getInt("Member_ID"))) {
                    if (!bookings.containsKey(rs.getInt("ID"))) {
                        Booking booking = new Booking(rs.getInt("ID"), rs.getBoolean("CheckedIn"), members.get(rs.getInt("Member_ID")));

                        bookings.put(rs.getInt("ID"), booking);
                        members.get(rs.getInt("Member_ID")).addBooking(booking);
                    } else {
                        bookings.get(rs.getInt("ID")).setCheckedIn(rs.getBoolean("CheckedIn"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }
    
    public String addMember(String inName, String inUsername, String inPassword){
        
        String returnStatement = ""; 
        query = "call add_Member(?,?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            stmt.setString(4, returnStatement);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
    
    public String addPersonalTrainer(String inName, String inUsername, String inPassword){
        
        String returnStatement = ""; 
        query = "call add_PersonlTrainer(?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
            
            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }

    public String addReceptionist(String inName, String inUsername, String inPassword){
        
        String returnStatement = ""; 
        query = "call add_Receptionist(?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
            
            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
    
    public String addNote(String inMember_ID, String inNote){
        
        String returnStatement = ""; 
        query = "call add_Note(?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            stmt.setString(1, inMember_ID);
            stmt.setString(2, inNote);
            stmt.setString(3, returnStatement);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
    
    public String cancelBooking(int inMemberID, int inBookingID){
        
        String returnStatement = ""; 
        query = "call cancelBooking(?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
            
            stmt.setInt(1, inMemberID);
            stmt.setInt(2, inBookingID);
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
    
    public String createBooking(String inMemberID, String inWorkoutID){
        
        String returnStatement = ""; 
        query = "call BestGymEver.createBooking(?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            stmt.setString(1, inMemberID);
            stmt.setString(2, inWorkoutID);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            System.out.println("Hello");
            
            
                returnStatement = stmt.getString(3); 
                
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
    
    public String createWorkout(int inStartDate, int inEndDate, int inAvailableSlots, int inWorkoutRoom, 
                                int inWorkoutType, int inPersonalTrainer){
        
        String returnStatement = ""; 
        query = "call createWorkout(?,?,?,?,?,?)";
                
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                returnStatement = rs.getString("returnStatement"); 
            }
            
            stmt.setInt(1, inStartDate);
            stmt.setInt(2, inEndDate);
            stmt.setInt(3, inAvailableSlots);
            stmt.setInt(4, inWorkoutRoom);
            stmt.setInt(5, inWorkoutType);
            stmt.setInt(6, inPersonalTrainer);
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement; 
    }
}
