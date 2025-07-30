package com.Taskmanagement.Service;
import com.Taskmanagement.Entity.Task;
import com.Taskmanagement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {

        if (task.getStatus() == null) {
            task.setStatus(Task.TaskStatus.PENDING);
        }
        if (task.getCreatedBy() == null || task.getCreatedBy().isEmpty()) {
            task.setCreatedBy("System");
        }
        task.setUpdatedBy(task.getCreatedBy());
        return taskRepository.save(task);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Not found with id: "+ id));
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public List<Task> getOverdueTasks(){
        return taskRepository.findByDueDateBefore(LocalDate.now());
    }

    public List<Task> getTasksByStatus(Task.TaskStatus taskStatus){
        return taskRepository.findByStatus(taskStatus);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.searchAcrossFields(keyword);
    }

    public List<Task> getTasksCreatedBy(String createdBy){
        return taskRepository.findByCreatedBy(createdBy);
    }

    public List<Task> getTasksUpdatedBy(String updatedBy) {
        return taskRepository.findByUpdatedBy(updatedBy);
    }
}
