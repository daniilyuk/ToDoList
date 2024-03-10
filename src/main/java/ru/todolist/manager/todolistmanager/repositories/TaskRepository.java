package ru.todolist.manager.todolistmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.todolist.manager.todolistmanager.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByCompleted(boolean completed);
    List<Task> findByDateAndCompleted(Date date, boolean completed);
}
