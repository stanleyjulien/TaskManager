package controller;

import com.google.gson.Gson;
import model.Task;
import utility.DBconnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String strtask = request.getParameter("task");
        PrintWriter out = response.getWriter();
        DBconnection dBconnection = new DBconnection();
        Task task = new Task();
        if ("insert".equals(action) || "update".equals(action)) {
            dBconnection.modifyTask(task);
        } else if ("delete".equals(action)) {
            dBconnection.deleteTask(task.getId());
        } else if ("complete".equals(action)) {
            //TODO later
        }

        //List<Task> taskList = new MockData().retrieveTaskList();
        List<Task> taskList = dBconnection.retrieveTaskList("luat");
        String JSONtasks = new Gson().toJson(taskList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(JSONtasks);
    }
}
