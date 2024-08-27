window.onload = loadTasks;

function loadTasks() {
    fetch("http://localhost:8081/TODO-Task-App/task")
        .then(response => response.json())
        .then(tasks => {
            const container = document.getElementById('todoList');
            container.innerHTML = '';

            tasks.forEach(task => {
                const taskDiv = `
                    <div class="container" >
                        <div class="row task border bg-primary-subtle p-1 m-2 rounded">
                            <div class="col-md-6 col-lg-3">
                                <h3 class="p-2">${task.title}</h3>
                            </div>
                            <div class="col-md-6 col-lg-2">
                                ${task.dueDate}
                            </div>
                            <div class="col-md-3 col-lg-2">
                                ${task.startTime}
                            </div>
                            <div class="col-md-3 col-lg-2">
                                ${task.remainderTime}
                            </div>
                            <div class="col-md-4 col-lg-2 d-flex align-items-center border">
                                ${getPriorityString(task.priority)}
                            </div>
                            <div class="d-flex align-items-center col-md-2 col-lg-1">
                                <button type="button" class="icon-btn bg-primary-subtle" aria-label="Edit" onclick='edit(${JSON.stringify(task)})'>
                                    <i class="bi bi-pencil"></i>
                                </button>
                                <button type="button" class="icon-btn bg-primary-subtle ms-2" onclick="deleteTask(this, ${task.taskId})" aria-label="Delete">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                `;
                container.innerHTML += taskDiv;
            });
        });
}

function deleteTask(element, taskId) {
    fetch(`http://localhost:8081/TODO-Task-App/task?taskId=${taskId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            element.closest('.container').remove();
            loadTasks();
        } else {
            console.error('Error deleting task:', response.statusText);
        }
    })
    .catch(error => console.error('Error deleting task:', error));
}

function getPriorityString(priority) {
    switch (priority) {
        case 'HIGH': return 'High';
        case 'MEDIUM': return 'Medium';
        case 'LOW': return 'Low';
        default: return 'Unknown';
    }
}

const themeBtn = document.getElementById("themeBtn");
const forTheme = document.getElementById("forTheme");
themeBtn.addEventListener("click", () => {
    const themeColor = forTheme.getAttribute("data-bs-theme");
    if (themeColor === "dark") {
        forTheme.setAttribute("data-bs-theme", "light");
        themeBtn.innerHTML = `<i class="bi bi-moon"></i>`;
    } else {
        forTheme.setAttribute("data-bs-theme", "dark");
        themeBtn.innerHTML = `<i class="bi bi-sun"></i>`;
    }
});

function edit(task) {
    document.getElementById('editTaskId').value = task.taskId;
    document.getElementById('editTaskTitle').value = task.title;
    document.getElementById('editDueDate').value = task.dueDate;
    document.getElementById('editPriority').value = task.priority;
    document.getElementById('editStartTime').value = task.startTime.substring(0, 5);
    document.getElementById('editReminderTime').value = task.remainderTime.substring(0, 5);

    new bootstrap.Modal(document.getElementById('editTaskModal')).show();
}


document.getElementById('editTaskForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const taskId = document.getElementById('editTaskId').value;
    const title = document.getElementById('editTaskTitle').value;
    const dueDate = document.getElementById('editDueDate').value;
    const priority = document.getElementById('editPriority').value;
    const startTime = `${document.getElementById('editStartTime').value}:00`; // Ensure format HH:MM:SS
    const remainderTime = `${document.getElementById('editReminderTime').value}:00`; // Ensure format HH:MM:SS

    fetch(`http://localhost:8081/TODO-Task-App/task?taskId=${taskId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ taskId, title, dueDate, priority, startTime, remainderTime })
    })
    .then(response => {
        if (response.ok) {
            new bootstrap.Modal(document.getElementById('editTaskModal')).hide();
            loadTasks();
        } else {
            return response.text().then(text => { throw new Error(text); });
        }
    })
    .catch(error => console.error('Error updating task:', error));
});


