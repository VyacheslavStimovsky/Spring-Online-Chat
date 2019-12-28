package com.vyacheslav.chat.repositoty;

import com.vyacheslav.chat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
}
