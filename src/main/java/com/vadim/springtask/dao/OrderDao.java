package com.vadim.springtask.dao;

import com.vadim.springtask.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDao extends CrudDao<Order, UUID> {

    List<Order> findAllByUserId(UUID id);
}
