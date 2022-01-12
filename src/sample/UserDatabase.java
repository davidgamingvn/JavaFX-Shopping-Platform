package sample;
import java.sql.*;
public class UserDatabase {
        public Connection getConnection () {
            Connection c = null;
            Statement smt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/user_database", "postgres", "");
                System.out.println("Connected to the database");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.print(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            return c;
        }
    }

