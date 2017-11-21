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
        String selecteduser = request.getParameter("selecteduser");
        Task task = new Task();
        if (strtask != null &&!"".equals(strtask)) {
            Gson gson = new Gson();
            task = gson.fromJson(strtask, Task.class);
        }
        PrintWriter out = response.getWriter();
        DBconnection dBconnection = new DBconnection();
        //Task task = new Task();
        if ("modify".equals(action)) {
            dBconnection.modifyTask(task);
        } else if ("delete".equals(action)) {
            String taskId = request.getParameter("taskid");
            int id = 0;
            if (taskId != null && !"".equals(taskId)) {
                try {
                    id = Integer.parseInt(taskId);
                } catch (NumberFormatException nfe) {

                }
            }
            dBconnection.deleteTask(id);
        } else if ("complete".equals(action)) {
            //TODO later
        }

        //List<Task> taskList = new MockData().retrieveTaskList();
        List<Task> taskList = dBconnection.retrieveTaskList(selecteduser);
        String JSONtasks = new Gson().toJson(taskList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(JSONtasks);
    }
}
