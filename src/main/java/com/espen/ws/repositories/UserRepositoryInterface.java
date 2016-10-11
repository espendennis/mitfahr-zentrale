package com.espen.ws.repositories;

import org.springframework.data.repository.CrudRepository;

import com.espen.ws.model.User;

public interface UserRepositoryInterface extends CrudRepository<User, String>{

}
