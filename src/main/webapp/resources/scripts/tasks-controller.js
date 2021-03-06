tasksController = function() { 
	
	function errorLogger(errorCode, errorMessage) {
		console.log(errorCode +':'+ errorMessage);
	}
	
	var taskPage;
	var initialised = false;
    var dueascending = false;
    var priorityascending = false;

    /**
	 * makes json call to server to get task list.
	 * currently just testing this and writing return value out to console
	 * 111917kl
     */
	function retrieveTasksServer() {
        $.ajax("TaskServlet", {
            "type": "get",
			dataType: "json",
            data: {"selecteduser": $(taskPage).find('#selecteduser').val()}
        }).done(displayTasksServer.bind()); //need reference to the tasksController object
    }



    /**
	 * 111917kl
	 * callback for retrieveTasksServer
     * @param data
     */
    function displayTasksServer(data) { //this needs to be bound to the tasksController -- used bind in retrieveTasksServer 111917kl
    	console.log(data);
        tasksController.loadServerTasks(data);
    }
	
	function taskCountChanged() {
		var count = $(taskPage).find( '#tblTasks tbody tr').length;
		$('footer').find('#taskCount').text(count);
	}
	
	function clearTask() {
		$(taskPage).find('form').fromObject({});
	}
	
	function renderTable() {
		$.each($(taskPage).find('#tblTasks tbody tr'), function(idx, row) {
			var due = Date.parse($(row).find('[datetime]').text());
			if (due.compareTo(Date.today()) < 0) {
				$(row).addClass("overdue");
			} else if (due.compareTo((2).days().fromNow()) <= 0) {
				$(row).addClass("warning");
			}
		});
	}



	
	return { 
		init : function(page, callback) { 
			if (initialised) {
				callback()
			} else {
				taskPage = page;
				storageEngine.init(function() {
					storageEngine.initObjectStore('task', function() {
						callback();
					}, errorLogger) 
				}, errorLogger);	 				
				$(taskPage).find('[required="required"]').prev('label').append( '<span>*</span>').children( 'span').addClass('required');
				$(taskPage).find('tbody tr:even').addClass('even');

				$(taskPage).find('#btnAddTask').click(function(evt) {
					evt.preventDefault();
					$(taskPage).find('#taskCreation').removeClass('not');
				});

                /**	 * 11/19/17kl        */
                $(taskPage).find('#btnRetrieveTasks').click(function(evt) {
                    evt.preventDefault();
                    console.log('making ajax call');
                    retrieveTasksServer();
                });
				
				$(taskPage).find('#tblTasks tbody').on('click', 'tr', function(evt) {
					$(evt.target).closest('td').siblings().andSelf().toggleClass('rowHighlight');
				});	
				
				$(taskPage).find('#tblTasks tbody').on('click', '.deleteRow', 
					function(evt) { 					
						storageEngine.delete('task', $(evt.target).data().taskId, 
							function() {
								$(evt.target).parents('tr').remove(); 
								taskCountChanged();
                                $.ajax("TaskServlet", {
                                    "type": "get",
                                    dataType: "json",
                                    "data": {
                                        "action": "delete",
                                        "taskid": $(evt.target).data().taskId
                                    }
                                }).done(displayTasksServer.bind());
							}, errorLogger);
						
					}
				);




                function sortTasks() {
                    $(taskPage).find('#tblTasks tbody').empty();
                    storageEngine.findAll('task', function(tasks) {
                        tasks.sort(function(o1, o2) {
                            return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
                        });
                        $.each(tasks, function(index, task) {
                            if (!task.complete) {
                                task.complete = false;
                            }
                            $('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
                            taskCountChanged();
                            renderTable();
                        });
                    }, errorLogger);
                }


                $(taskPage).find('#dueHead').click(function (evt) {
                    $(taskPage).find('#tblTasks tbody').empty();
                    storageEngine.findAll('task', function(tasks) {
                    	if(dueascending){
                            tasks.sort(function(o2, o1) {
                                return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
                            });
                            dueascending = false;
						}
						else
						{
                            tasks.sort(function(o1, o2) {
                                return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
                            });
                            dueascending = true;
						}

                        $.each(tasks, function(index, task) {
                            if (!task.complete) {
                                task.complete = false;
                            }
                            $('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
                            taskCountChanged();
                            renderTable();
                        });
                    }, errorLogger);
                });


                $(taskPage).find('#priorityHead').click(function (evt) {
                    $(taskPage).find('#tblTasks tbody').empty();
                    storageEngine.findAll('task', function(tasks) {
                    	if(priorityascending)
						{
                            tasks.sort(function(o1, o2) {
                                var o1Priority = 0;
                                var o2Priority = 0;

                                if(o1.priority === 'High')
                                {
                                    o1Priority = 3;
                                }
                                else if(o1.priority == 'Medium')
                                {
                                    o1Priority = 2;
                                }
                                else
                                {
                                    o1Priority = 1;
                                }


                                if(o2.priority === 'High')
                                {
                                    o2Priority = 3;
                                }
                                else if(o2.priority == 'Medium')
                                {
                                    o2Priority = 2;
                                }
                                else
                                {
                                    o2Priority = 1;
                                }
                                return o2Priority - o1Priority;
                            });
                            priorityascending = false;
						}

						else
						{
                            tasks.sort(function(o1, o2) {
                                var o1Priority = 0;
                                var o2Priority = 0;

                                if(o1.priority === 'High')
                                {
                                    o1Priority = 1;
                                }
                                else if(o1.priority == 'Medium')
                                {
                                    o1Priority = 2;
                                }
                                else
                                {
                                    o1Priority = 3;
                                }


                                if(o2.priority === 'High')
                                {
                                    o2Priority = 1;
                                }
                                else if(o2.priority == 'Medium')
                                {
                                    o2Priority = 2;
                                }
                                else
                                {
                                    o2Priority = 3;
                                }
                                return o2Priority - o1Priority;
                            });
                            priorityascending = true;
						}

                        $.each(tasks, function(index, task) {
                            if (!task.complete) {
                                task.complete = false;
                            }
                            $('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
                            taskCountChanged();
                            renderTable();
                        });
                    }, errorLogger);
                });
				
				$(taskPage).find('#tblTasks tbody').on('click', '.editRow', 
					function(evt) { 
						$(taskPage).find('#taskCreation').removeClass('not');
						storageEngine.findById('task', $(evt.target).data().taskId, function(task) {
							$(taskPage).find('form').fromObject(task);

						}, errorLogger);
					}
				);
				
				$(taskPage).find('#clearTask').click(function(evt) {
					evt.preventDefault();
					clearTask();
				});

                $(taskPage).find('#cancel').click(function(evt) {
                    evt.preventDefault();
                    clearTask();
                    $(taskPage).find('#taskCreation').addClass('not');
                });
				
				$(taskPage).find('#tblTasks tbody').on('click', '.completeRow', function(evt) { 					
					storageEngine.findById('task', $(evt.target).data().taskId, function(task) {
						task.complete = true;
						storageEngine.save('task', task, function() {
							tasksController.loadTasks();
						},errorLogger);
                        $.ajax("TaskServlet", {
                            "type": "get",
                            dataType: "json",
                            "data": {
                                "action": "complete",
                                "taskid": $(evt.target).data().taskId
                            }
                        }).done(displayTasksServer.bind());
					}, errorLogger);
				});
				
				$(taskPage).find('#saveTask').click(function(evt) {
					evt.preventDefault();
					if ($(taskPage).find('form').valid()) {
						var task = $(taskPage).find('form').toObject();		
						storageEngine.save('task', task, function() {
							$(taskPage).find('#tblTasks tbody').empty();
							tasksController.loadTasks();
							clearTask();
							$(taskPage).find('#taskCreation').addClass('not');
                            $.ajax("TaskServlet", {
                                "type": "get",
                                dataType: "json",
                                "data": {
                                    "action": "modify",
                                    "task": JSON.stringify(task)
                                }
                            }).done(displayTasksServer.bind());
						}, errorLogger);
					}
				});
				initialised = true;
			}
		},
        /**
		 * 111917kl
		 * modification of the loadTasks method to load tasks retrieved from the server
         */
		loadServerTasks: function(tasks) {
            $(taskPage).find('#tblTasks tbody').empty();
            storageEngine.initObjectStore('task', errorLogger);
            $.each(tasks, function (index, task) {
                if (!task.complete ) {
                    task.complete = false;
                }
                if (task.status == "Completed") {
                    task.complete = true;
                }
                $('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
                storageEngine.save('task', task, errorLogger);
                taskCountChanged();
                console.log('about to render table with server tasks');
                renderTable(); //skip for now, this just sets style class for overdue tasks 111917kl
            });
		},

		loadTasks : function() {
			$(taskPage).find('#tblTasks tbody').empty();
			storageEngine.findAll('task', function(tasks) {
				tasks.sort(function(o1, o2) {
					return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
				});
				$.each(tasks, function(index, task) {
					if (!task.complete) {
						task.complete = false;
					}
					$('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
					taskCountChanged();
					renderTable();
				});
			}, errorLogger);
		}
	} 
}();
