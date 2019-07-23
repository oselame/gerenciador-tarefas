package br.com.softal.gerenciadortarefas.services;

import br.com.softal.gerenciadortarefas.models.User;
import br.com.softal.gerenciadortarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        user.setPassword( passwordEncoder.encode(user.getPassword()) );
        userRepository.save(user);
    }



}
