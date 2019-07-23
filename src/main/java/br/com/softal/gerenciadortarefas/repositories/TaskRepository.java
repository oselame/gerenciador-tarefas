package br.com.softal.gerenciadortarefas.repositories;

import br.com.softal.gerenciadortarefas.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t where t.user.email = :userEmail")
    List<Task> findTasksByUserEmail(@Param("userEmail") String email);

}
