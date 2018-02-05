package bestgymever.repository;

import java.sql.*;
import static java.sql.ResultSet.*;

public class Repository {
    
    PropertiesReader pr;
    String query;
    ResultSet rs;

    public Repository() {
        this.pr = new PropertiesReader();
        pr.loadProperties();
    }
    
    public void logIn() {
        query = "SELECT * FROM BestGymEver.Member";
        try (Connection con = DriverManager.getConnection(pr.getConnectionString());
                PreparedStatement stmt = con.prepareStatement(query, TYPE_SCROLL_SENSITIVE, CONCUR_READ_ONLY)) {

            
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }
    
    
}
