package utility;

import model.Task;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** utility class to return mock data for testing
 *  @since 11/19/2017
 *  @author kl */
public class MockData {

    public ArrayList<Task> taskList = new ArrayList<>();


    public ArrayList<Task> retrieveTaskList() {

        taskList.add(new Task(101, "first task", "2017-11-19", "Personal", "High", "luat", "Completed"));
        taskList.add(new Task(102, "second task", "2017-11-23", "Work", "Medium", "stanley", "Completed"));
        taskList.add(new Task(103, "third task", "2017-12-19", "Work", "Low", "samson", "Completed"));

        return taskList;
    }

    private HashMap<String, User> loginData = new HashMap<>();

    public HashMap<String, User> retrieveLoginData(){
        loginData.put("luat", new User("luat", "luat", "Luat Nguyen" ));
        loginData.put("stanley", new User("stanley", "stanley", "Stanley Julien" ));
        loginData.put("samson", new User("samson", "samson", "Samson Weldegebriel" ));
        loginData.put("admin", new User("admin", "admin", "Administrator" ));
        return loginData;
    }



}


