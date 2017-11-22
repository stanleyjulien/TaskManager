function initScreen() {
    $(document).ready(function () {
        tasksController.init($('#taskPage'), function () {
            tasksController.loadTasks();
        });
    });
}

if (window.indexedDB) {
    console.log("using indexedDB 111917kl");
    $.getScript("resources/scripts/tasks-indexeddb.js")
        .done(function (script, textStatus) {
            initScreen();
        })
        .fail(function (jqxhr, settings, exception) {
            console.log('Failed to load indexed db script');
        });
} else if (window.localStorage) {
    console.log("using webstorage 111917kl");
    $.getScript("resources/scripts/tasks-webstorage.js")
        .done(function (script, textStatus) {
            initScreen();
        })
        .fail(function (jqxhr, settings, exception) {
            console.log('Failed to load web storage script');
        });
}