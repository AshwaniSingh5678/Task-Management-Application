package com.Taskmanagement.Service;
import com.Taskmanagement.Entity.Task;
import com.Taskmanagement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void update(Task task) {
        Task existingTask = taskRepository.findById(task.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + task.getId()));

        existingTask.setTitle(task.getTitle());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setRemarks(task.getRemarks());
        existingTask.setUpdatedBy(task.getUpdatedBy());
        existingTask.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(existingTask);
    }

}
