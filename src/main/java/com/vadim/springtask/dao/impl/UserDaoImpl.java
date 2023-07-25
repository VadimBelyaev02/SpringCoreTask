package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.UserDao;
import com.vadim.springtask.model.entity.Tag;
import com.vadim.springtask.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<User> findAll() {
        String query = "SELECT u FROM User u";
        return manager.createQuery(query, User.class).getResultList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        User user = manager.find(User.class, id);
        if (Objects.nonNull(user)) {
            manager.detach(user);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        manager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return manager.merge(user);
    }

    @Override
    public void deleteById(UUID id) {
        String query = "DELETE FROM User u WHERE u.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
