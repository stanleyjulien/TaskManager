<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21/11/2017
  Time: 04:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Task list</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/tasks.css" media="screen"/>
    <script src="resources/scripts/jquery-2.0.3.js"></script>
    <script src="resources/scripts/jquery-tmpl.js"></script>
    <script src="resources/scripts/jquery.validate.js"></script>
    <script src="resources/scripts/jquery-serialization.js"></script>
    <script src="resources/scripts/tasks-controller.js"></script>
    <script src="resources/scripts/date.js"></script>
    <script src="resources/scripts/jquery-init.js"></script>
</head>
<body>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<div style="float: right"> <%-- <%= session.getAttribute("loggedInUser")%> --%> </div>
<div style="float: right; margin-right: 10px; margin-top: 10px">Welcome <%= session.getAttribute("loggedInUser")%><%--${loggedInUser} --%>
    <span>
        <form action="/Logout" name="logout">
            <input type="submit" value="Logout!">
        </form>
    </span>
</div>
<header>

    <span>Task list</span>
</header>
<main id="taskPage">
    <section id="taskCreation" class="not">
        <form id="taskForm">
            <input type="hidden" name="id"/>
            <div>
                <label>Task</label> <input type="text" required="required"
                                           name="task" class="large" placeholder="Breakfast at Tiffanys"
                                           maxlength="200"/>
            </div>
            <div>
                <label>Required by</label> <input type="date" required="required"
                                                  name="requiredBy"/>
            </div>
            <div>
                <label>Category</label> <select class="large" name="category">
                <option value="Personal">Personal</option>
                <option value="Work">Work</option>
            </select>
            </div>

            <div>
                <label>Priority</label> <select class="large" name="priority">
                <option value="High">High</option>
                <option value="Medium">Medium</option>
                <option value="Low">Low</option>
            </select>
            </div>
            <div>
                <label>User</label> <select class="large" name="user">
                <option value="luat">Luat Nguyen</option>
                <option value="stanley">Stanley Julien</option>
                <option value="samson">Samson Weldegebriel</option>
            </select>
            </div>
            <div>
                <label>Status </label><select name="status">
                <option selected value="Not Start">Not Start</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
            </select>
            </div>
            <nav>
                <a href="#" id="saveTask">Save task</a>
                <!-- https://stackoverflow.com/questions/4855168/what-is-href-and-why-is-it-used -->
                <a href="#" id="clearTask">Clear task</a>
                <a href="#" id="cancel">Cancel</a>
            </nav>
        </form>
    </section>
    <section>
        <table id="tblTasks">
            <colgroup>
                <col width="30%">
                <col width="12%">
                <col width="12%">
                <col width="12%">
                <col width="12%">
                <col width="22%">
            </colgroup>
            <thead>
            <tr>
                <th>Name</th>
                <th id="dueHead">Due</th>
                <th id="priorityHead">Priority</th>
                <th>Category</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <nav>
            <label>Please select a user: <select name="user2" id="selecteduser">
                <option value="all">All</option>
                <option value="luat">Luat Nguyen</option>
                <option value="samson">Samson Weldegebriel</option>
                <option value="stanley">Stanley Julien</option>
            </select></label><br>
            <a href="#" id="btnRetrieveTasks">Retrieve tasks from server</a> <br><br><br>
            <a href="#" id="btnAddTask">Add task</a>
        </nav>
    </section>
</main>
<footer>You have <span id="taskCount"></span> tasks</footer>
</body>


<script id="taskRow" type="text/x-jQuery-tmpl">
<tr>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${task}</td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}><time datetime="${duedate}">${requiredBy}</time></td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${priority}</td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${category}</td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${status}</td>
	<td>
		<nav>
			{{if complete == false}}
				<a href="#" class="editRow" data-task-id="${id}">Edit</a>
				<a href="#" class="completeRow" data-task-id="${id}">Complete</a>
			{{/if}}
			<a href="#" class="deleteRow" data-task-id="${id}">Delete</a>
		</nav>
	</td>
</tr>

</script>


</html>

