package com.Taskmanagement.Controller;
import com.Taskmanagement.Entity.Task;
import com.Taskmanagement.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("formToShow", "addForm");
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    // View all tasks
    @GetMapping("/tasks")
    public String showTaskPage(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword) {

        List<Task> tasks = (keyword != null && !keyword.isEmpty())
                ? taskService.searchTasks(keyword)
                : taskService.getAllTasks();


        model.addAttribute("taskList", tasks);
        model.addAttribute("keyword", keyword);
        model.addAttribute("newTask", new Task());

        model.addAttribute("task", new Task());
        return "index.html";
    }

    @PostMapping("/add-task")
    public String createTask(@Valid @ModelAttribute("newTask") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("taskList", taskService.getAllTasks());
            model.addAttribute("keyword", "");
            return "index";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@Valid @ModelAttribute Task task,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formToShow", "updateForm");
            return "task-edit-form";
        }

        taskService.update(task);
        redirectAttributes.addFlashAttribute("message", "Task updated successfully!");
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);

        if (task.getDueDate() == null) {
            System.out.println(" DueDate is NULL for task ID: " + id);
        }
        model.addAttribute("task", task);
        return "editform";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/overdue")
    public String getOverdueTasks(Model model) {
        List<Task> overdueTasks = taskService.getOverdueTasks();
        model.addAttribute("taskList", overdueTasks);
        model.addAttribute("task", new Task());
        return "index";
    }

}


