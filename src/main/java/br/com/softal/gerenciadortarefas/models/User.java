package br.com.softal.gerenciadortarefas.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "email", length = 100, nullable = false)
    @NotNull(message = "The E-mail is required!")
    @Length(max=100, min=5, message = "The E-mail must contain between 5 and 100 caracters!")
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    @NotNull(message = "The Password is required!")
    @Length(max=100, message = "The Password must contain up to 100 caracters!")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
