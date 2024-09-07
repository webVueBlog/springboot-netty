package org.dada.demo.netty.service;

import org.dada.demo.netty.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface UserService {

    void save(User user);

    void deleteById(String id);

    User queryUserById(String id);

    Iterable<User> queryAll();

    Page<User> findByName(String name, PageRequest request);

}
