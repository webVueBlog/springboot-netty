package org.dada.demo.netty.service.impl;

import org.dada.demo.netty.domain.User;
import org.dada.demo.netty.service.UserRepository;
import org.dada.demo.netty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepository dataRepository;

    @Autowired
    public void setDataRepository(UserRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void save(User user) {
        dataRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        dataRepository.deleteById(id);
    }

    @Override
    public User queryUserById(String id) {
        Optional<User> optionalUser = dataRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    public Iterable<User> queryAll() {
        return dataRepository.findAll();
    }

    @Override
    public Page<User> findByName(String name, PageRequest request) {
        return dataRepository.findByName(name, request);
    }

}
