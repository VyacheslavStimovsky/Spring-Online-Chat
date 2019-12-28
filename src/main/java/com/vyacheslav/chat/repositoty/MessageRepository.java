package com.vyacheslav.chat.repositoty;

import com.vyacheslav.chat.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer> {
}
