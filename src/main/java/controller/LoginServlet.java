package controller;

import com.mysql.cj.api.Session;
import model.User;
import utility.MockData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username  = request.getParameter("uname");
        String password = request.getParameter("password");
        boolean loginSuccess = false;

        MockData md = new MockData();
        HashMap<String, User> loginMap = new HashMap<>();
        loginMap = md.retrieveLoginData();

        for(String un: loginMap.keySet())
        {
            if(un.equals(username) && loginMap.get(un).getPassword().equals(password)){
                loginSuccess = true;
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", loginMap.get(un).getFullName());
                break;
            }
            else {
                loginSuccess = false;
            }
        }

        if(loginSuccess)
        {
            response.sendRedirect("tasks.jsp");
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
