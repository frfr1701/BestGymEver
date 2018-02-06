package bestgymever.repository;

import bestgymever.models.*;
import java.sql.*;
import static java.sql.ResultSet.*;

public class Repository {

    PropertiesReader pr;
    String query;
    ResultSet rs;
    String returnStatement;

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

    public SuperModel getMembers(SuperModel model, String member) {
        query = "SELECT * FROM BestGymEver.Member";
        oneOrAll(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getMembers().containsKey(rs.getInt("ID"))) {
                    model.getMembers().put(rs.getInt("ID"), new Member(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    model.getMembers().get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel getPersonalTrainers(SuperModel model, String personalTrainer) {
        query = "SELECT * FROM BestGymEver.PersonalTrainer";
        oneOrAll(personalTrainer);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(personalTrainer)) {
                stmt.setString(1, personalTrainer);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getPersonalTrainers().containsKey(rs.getInt("ID"))) {
                    model.getPersonalTrainers().put(rs.getInt("ID"), new PersonalTrainer(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    model.getPersonalTrainers().get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel getWorkoutRooms(SuperModel model, String workoutRoom) {
        query = "SELECT * FROM BestGymEver.WorkoutRoom";
        oneOrAll(workoutRoom);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workoutRoom)) {
                stmt.setString(1, workoutRoom);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getWorkoutRooms().containsKey(rs.getInt("ID"))) {
                    model.getWorkoutRooms().put(rs.getInt("ID"), new WorkoutRoom(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    model.getWorkoutRooms().get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel getWorkoutTypes(SuperModel model, String workoutType) {
        query = "SELECT * FROM BestGymEver.WorkoutType";
        oneOrAll(workoutType);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workoutType)) {
                stmt.setString(1, workoutType);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getWorkoutTypes().containsKey(rs.getInt("ID"))) {
                    model.getWorkoutTypes().put(rs.getInt("ID"), new WorkoutType(rs.getInt("ID"), rs.getString("Name")));
                } else {
                    model.getWorkoutTypes().get(rs.getInt("ID")).setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel getWorkouts(SuperModel model, String workout) {
        query = "SELECT * FROM BestGymEver.GetWorkouts";
        oneOrAll(workout);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(workout)) {
                stmt.setString(1, workout);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getWorkouts().containsKey(rs.getInt("Workout_ID"))) {

                    if (!model.getWorkoutRooms().containsKey(rs.getInt("WorkoutRoom_ID"))) {
                        model.getWorkoutRooms().put(rs.getInt("WorkoutRoom_ID"), new WorkoutRoom(rs.getInt("WorkoutRoom_ID"), rs.getString("WorkoutRoom_Name")));
                    } else {
                        model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")).setName(rs.getString("WorkoutRoom_Name"));
                    }
                    if (!model.getWorkoutTypes().containsKey(rs.getInt("WorkoutType_ID"))) {
                        model.getWorkoutTypes().put(rs.getInt("WorkoutType_ID"), new WorkoutType(rs.getInt("WorkoutType_ID"), rs.getString("WorkoutType_Name")));
                    } else {
                        model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID")).setName(rs.getString("WorkoutType_Name"));
                    }
                    if (!model.getPersonalTrainers().containsKey(rs.getInt("PersonalTrainer_ID"))) {
                        model.getPersonalTrainers().put(rs.getInt("PersonalTrainer_ID"), new PersonalTrainer(rs.getInt("PersonalTrainer_ID"), rs.getString("PersonalTrainer_Name")));
                    } else {
                        model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")).setName(rs.getString("PersonalTrainer_Name"));
                    }

                    model.getWorkouts().put(rs.getInt("Workout_ID"), new Workout(rs.getInt("Workout_ID"),
                            model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")),
                            rs.getInt("AvailableSlots"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")),
                            model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID"))));

                    model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                    model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                    model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                } else {
                    model.getWorkouts().get(rs.getInt("Workout_ID")).setAvalibleSlots(rs.getInt("AvailableSlots"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel mapNotesToMembers(SuperModel model, String member) {
        query = "SELECT * FROM Note";
        oneOrAllMemberID(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!model.getNotes().containsKey(rs.getInt("ID"))) {
                    if (model.getMembers().containsKey(rs.getInt("Member_ID"))) {
                        Note note = new Note(rs.getInt("ID"), rs.getString("Note"), model.getMembers().get(rs.getInt("Member_ID")));

                        model.getNotes().put(rs.getInt("ID"), note);
                        model.getMembers().get(rs.getInt("Member_ID")).addNote(note);
                    }
                } else {
                    model.getNotes().get(rs.getInt("ID")).setNote(rs.getString("Note"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel mapWorkoutsToBookings(SuperModel model, String booking) {
        query = "SELECT * FROM GetWorkoutsWithBooking";
        oneOrAllBookingID(booking);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(booking)) {
                stmt.setString(1, booking);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (model.getBookings().containsKey(rs.getInt("Booking_ID"))) {
                    if (!model.getWorkouts().containsKey(rs.getInt("Workout_ID"))) {

                        if (!model.getWorkoutRooms().containsKey(rs.getInt("WorkoutRoom_ID"))) {
                            model.getWorkoutRooms().put(rs.getInt("WorkoutRoom_ID"), new WorkoutRoom(rs.getInt("WorkoutRoom_ID"), rs.getString("WorkoutRoom_Name")));
                        } else {
                            model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")).setName(rs.getString("WorkoutRoom_Name"));
                        }
                        if (!model.getWorkoutTypes().containsKey(rs.getInt("WorkoutType_ID"))) {
                            model.getWorkoutTypes().put(rs.getInt("WorkoutType_ID"), new WorkoutType(rs.getInt("WorkoutType_ID"), rs.getString("WorkoutType_Name")));
                        } else {
                            model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID")).setName(rs.getString("WorkoutType_Name"));
                        }
                        if (!model.getPersonalTrainers().containsKey(rs.getInt("PersonalTrainer_ID"))) {
                            model.getPersonalTrainers().put(rs.getInt("PersonalTrainer_ID"), new PersonalTrainer(rs.getInt("PersonalTrainer_ID"), rs.getString("PersonalTrainer_Name")));
                        } else {
                            model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")).setName(rs.getString("PersonalTrainer_Name"));
                        }

                        model.getWorkouts().put(rs.getInt("Workout_ID"), new Workout(rs.getInt("Workout_ID"),
                                model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")),
                                rs.getInt("AvailableSlots"),
                                rs.getDate("StartDate"),
                                rs.getDate("EndDate"),
                                model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")),
                                model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID"))));

                        model.getWorkoutRooms().get(rs.getInt("WorkoutRoom_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                        model.getWorkoutTypes().get(rs.getInt("WorkoutType_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                        model.getPersonalTrainers().get(rs.getInt("PersonalTrainer_ID")).addWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
                    } else {
                        model.getWorkouts().get(rs.getInt("Workout_ID")).setAvalibleSlots(rs.getInt("AvailableSlots"));
                    }
                }
                model.getBookings().get(rs.getInt("Booking_ID")).setWorkout(model.getWorkouts().get(rs.getInt("Workout_ID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public SuperModel mapBookingsToMembers(SuperModel model, String member) {
        query = "SELECT * FROM Booking";
        oneOrAllMemberID(member);
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {
            if (isLongerThanOne(member)) {
                stmt.setString(1, member);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (model.getMembers().containsKey(rs.getInt("Member_ID"))) {
                    if (!model.getBookings().containsKey(rs.getInt("ID"))) {
                        Booking booking = new Booking(rs.getInt("ID"), rs.getBoolean("CheckedIn"), model.getMembers().get(rs.getInt("Member_ID")));

                        model.getBookings().put(rs.getInt("ID"), booking);
                        model.getMembers().get(rs.getInt("Member_ID")).addBooking(booking);
                    } else {
                        model.getBookings().get(rs.getInt("ID")).setCheckedIn(rs.getBoolean("CheckedIn"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return model;
    }

    public String addMember(String inName, String inUsername, String inPassword) {
        query = "call add_Member(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(4);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }

    public String addPersonalTrainer(String inName, String inUsername, String inPassword) {
        query = "call add_PersonlTrainer(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(4);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }

    public String addReceptionist(String inName, String inUsername, String inPassword) {
        query = "call add_Receptionist(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inName);
            stmt.setString(2, inUsername);
            stmt.setString(3, inPassword);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(4);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }

    public String addNote(String inMember_ID, String inNote) {
        query = "call add_Note(?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inMember_ID);
            stmt.setString(2, inNote);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(3);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }

    public String cancelBooking(String inMemberID, String inBookingID) {
        query = "call cancelBooking(?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inMemberID);
            stmt.setString(2, inBookingID);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(3);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }

    public String createBooking(String inMemberID, String inWorkoutID) {
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

    public String createWorkout(String inStartDate, String inEndDate,
            String inAvailableSlots, String inWorkoutRoom,
            String inWorkoutType, String inPersonalTrainer) {
        query = "call createWorkout(?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                CallableStatement stmt = con.prepareCall(query)) {

            stmt.setString(1, inStartDate);
            stmt.setString(2, inEndDate);
            stmt.setString(3, inAvailableSlots);
            stmt.setString(4, inWorkoutRoom);
            stmt.setString(5, inWorkoutType);
            stmt.setString(6, inPersonalTrainer);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            rs = stmt.executeQuery();

            returnStatement = stmt.getString(7);

        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
        return returnStatement;
    }
}
