package com.vadim.springtask.dao;

import com.vadim.springtask.model.entity.User;

import java.util.UUID;

public interface UserDao extends CrudDao<User, UUID> {


}
