package utility;

import model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBconnection {

    String dburl = "jdbc:mysql://localhost:3306/tasklist";

    public DBconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC driver not found in DBConnection\n" + ex);
            System.exit(0);
        }
    }

    String retrieveUserFullname(String email) {
        String readQuery = "SELECT fullname from users where email = '" + email + "';";
        String fullname = "No information found for the requested user: " + email;

        try (Connection con = DriverManager.getConnection(dburl, "root", "password");
                Statement stmt = con.createStatement();) {

            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
                fullname = rs.getString("fullname");
                System.out.println("User Fullname: " + fullname);
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }

        return fullname;

    }

    public List<Task> retrieveTaskList(String user) {
        String readQuery = "SELECT taskid, taskname, category, duedate, priority, userid from task where userid = '" + user + "';";
        String fullname = "No information found for the requested user: " + user;
        ArrayList<Task> taskList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(dburl, "wapproject", "password123");
             Statement stmt = con.createStatement();) {

            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
                int id = rs.getInt("taskid");
                String task = rs.getString("taskname");
                String duedate = rs.getString("duedate");
                duedate = duedate.substring(0, duedate.indexOf(' '));
                String category = rs.getString("category");
                String priority = rs.getString("priority");
                String userid = rs.getString("userid");
                System.out.println("Task: " + id + "\tduedate: " + duedate);
                taskList.add(new Task(id, task, duedate, category, priority, userid));
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }

        return taskList;

    }

    public String mockRetrieveUserFullname(String email) {
        String fullname = "no definition found";
        switch (email) {
            case "asaad@mum.edu":
                fullname = "Asaad Saad";
                break;
            default: fullname = "CS472 Student";
                break;
        }
        return fullname;
    }

}
