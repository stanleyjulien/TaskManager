package model;

public class Task {

    private int id;
    private String task;
    private String dueDate;
    private String category;
    private String priority;
    private String userId;

    public Task(int id, String task, String dueDate, String category, String priority, String userId) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
        this.category = category;
        this.userId = userId;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
