package utility;

import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBconnection {
/*    String dburl = "jdbc:mysql://10.10.11.151:3306/tasklist";
    final String username = "root1";
    final String password = "password";*/

    /*String dburl = "jdbc:mysql://localhost:3306/tasklist";
    final String username = "root";
    final String password = "root";*/

    String dburl = "jdbc:mysql://wapproject.cdgwlcn3knwq.us-east-2.rds.amazonaws.com:3306/tasklist";
    final String username = "luatnguyen";
    final String password = "123456789";

    public DBconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC driver not found in DBConnection\n" + ex);
            System.exit(0);
        }
    }

    /*String retrieveUserFullname(String email) {
        String readQuery = "SELECT fullname from users where email = '" + email + "';";
        String fullname = "No information found for the requested user: " + email;

        try (Connection con = DriverManager.getConnection(dburl, username, password);
             Statement stmt = con.createStatement()) {

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

    }*/

    public List<Task> retrieveTaskList(String user) {
        String readQuery = "SELECT taskid, taskname, category, duedate, priority, userid, taskstatus from task";
        if (user != null && !"".equals(user) && !"all".equals(user)) {
            readQuery += " WHERE userid = '" + user + "';";
        }
        ArrayList<Task> taskList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             Statement stmt = con.createStatement()) {
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
                String status = rs.getString("taskstatus");
                taskList.add(new Task(id, task, duedate, category, priority, userid, status));
            }
            stmt.close();
        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }
        return taskList;
    }

    public void modifyTask(Task task) {
        String readQuery = "SELECT taskid from task where taskid = " + task.getId() + ";";
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             Statement stmt = con.createStatement()) {
            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("taskid");
            }
            stmt.close();
            if (id > 0) {
                updateTask(task);
            } else {
                insertTask(task);
            }
        } catch (SQLException s) {
            System.out.println("Exception thrown in modify task ....");
            s.printStackTrace();
        }
    }

    private void updateTask(Task task) {
        String query = "update task set taskname='" + task.getTask() + "', category='" + task.getCategory() + "', duedate='" + task.getRequiredBy()
                + "', priority='" + task.getPriority() + "', userid='" + task.getUser() + "', taskstatus='" + task.getStatus() + "' where taskid=" + task.getId();
        System.out.println("Query: " + query);
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.executeUpdate();
            preparedStmt.close();
            con.close();
        } catch (SQLException s) {
            System.out.println("Exception thrown in update Task ....");
            s.printStackTrace();
        }
    }

    private void insertTask(Task task) {
        String query = "INSERT INTO task (taskname, category, duedate, priority, userid, taskstatus) VALUES ('" + task.getTask() + "', '" + task.getCategory()
                + "', '" + task.getRequiredBy() + "', '" + task.getPriority() + "', '" + task.getUser() + "', '" + task.getStatus() + "');";
        System.out.println("Query: " + query);
        //try (Connection con = DriverManager.getConnection(dburl, "luatnguyen", "123456789");
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.executeUpdate();
            preparedStmt.close();
            con.close();
        } catch (SQLException s) {
            System.out.println("Exception thrown in insert Task ....");
            s.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        String query = "DELETE FROM task WHERE taskid=" + id;
        System.out.println("Query: " + query);
        //try (Connection con = DriverManager.getConnection(dburl, "luatnguyen", "123456789");
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.executeUpdate();
            preparedStmt.close();
            con.close();
        } catch (SQLException s) {
            System.out.println("Exception thrown in delete Task ....");
            s.printStackTrace();
        }
    }

    public void completeTask(int id) {
        String query = "update task set taskstatus='Completed' where taskid=" + id;
        System.out.println("Query: " + query);
        try (Connection con = DriverManager.getConnection(dburl, username, password);
             PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.executeUpdate();
            preparedStmt.close();
            con.close();
        } catch (SQLException s) {
            System.out.println("Exception thrown in complete Task ....");
            s.printStackTrace();
        }
    }

    public String mockRetrieveUserFullname(String email) {
        String fullname = "no definition found";
        switch (email) {
            case "asaad@mum.edu":
                fullname = "Asaad Saad";
                break;
            default:
                fullname = "CS472 Student";
                break;
        }
        return fullname;
    }

}
