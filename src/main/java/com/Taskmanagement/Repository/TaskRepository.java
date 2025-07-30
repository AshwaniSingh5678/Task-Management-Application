package com.Taskmanagement.Repository;

import com.Taskmanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDueDateBefore(LocalDate date);

    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String desc);

    List<Task> findByCreatedBy(String createdBy);

    List<Task> findByUpdatedBy(String updatedBy);

    List<Task> findByStatus(Task.TaskStatus status);

    @Query("SELECT t FROM Task t WHERE " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.remarks) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.createdBy) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.updatedBy) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchAcrossFields(@Param("keyword") String keyword);
}
