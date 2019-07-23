package br.com.softal.gerenciadortarefas.repositories;

import br.com.softal.gerenciadortarefas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
