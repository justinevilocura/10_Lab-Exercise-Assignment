package com.appdev.jeb.vilocurag6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.jeb.vilocurag6.entity.User;
import com.appdev.jeb.vilocurag6.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User create(User user) {
        if (repo.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findById(int id) {
        return repo.findById(id);
    }

    public User update(int id, User user) {
        User existing = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setRoleid(user.getRoleid());
        return repo.save(existing);
    }

    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        repo.deleteById(id);
    }
}
