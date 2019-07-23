package br.com.softal.gerenciadortarefas.repositories;

import br.com.softal.gerenciadortarefas.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
