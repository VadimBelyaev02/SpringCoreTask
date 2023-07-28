package com.vadim.springtask.dao.impl;

import com.vadim.springtask.dao.OrderDao;
import com.vadim.springtask.model.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private final EntityManager manager;
    @Override
    public List<Order> findAll() {
        String query = "SELECT s FROM Order s";
        return manager.createQuery(query, Order.class).getResultList();
    }

    @Override
    public Optional<Order> findById(UUID id) {
        Order order = manager.find(Order.class, id);
        if (Objects.nonNull(order)) {
            manager.detach(order);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public Order save(Order order) {
        manager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        return manager.merge(order);
    }

    @Override
    public void deleteById(UUID id) {
        String query = "DELETE FROM Order o WHERE o.id = :id";
        manager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Order> findAllByUserId(UUID id) {
        final String query = "SELECT o FROM Order o WHERE o.user.id = :id";
        return manager.createQuery(query, Order.class)
                .setParameter("id", id)
                .getResultList();
    }
}
