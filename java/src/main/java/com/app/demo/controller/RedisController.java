package com.app.demo.controller;

import com.app.demo.repository.UserRedisRepository;
import com.app.demo.model.User;
import com.app.demo.model.UserRedis;
import com.app.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/*
*
*
* */
@Controller // This means that this class is a Controller
@RequestMapping(path="/redis") // This means URL's start with /redisAPI (after Application path)
public class RedisController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRedisRepository userRedisRepository;

    private List<UserRedis> userRedisList;
    @PostMapping(path="/updateUsers")
    public @ResponseBody String updateUsers () {
        // @ResponseBody means the returned String is the response, not a view name
        passUsers(userRepository.findAll());
        userRedisRepository.deleteAll();
        userRedisRepository.saveAll(userRedisList);
        return "Updated";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserRedis> getAllUsersFromRedis() {
        // This returns a JSON or XML with the users
        return userRedisRepository.findAll();
    }

    private void passUsers(Iterable<User> userList) {
        userRedisList = new ArrayList<>();
        for (User a : userList) {
            UserRedis user = new UserRedis();
            user.setId(a.getId());
            user.setFirstName(a.getFirstName());
            user.setLastName(a.getLastName());
            user.setNickName(a.getNickName());
            user.setAge(a.getAge());
            user.setEmail(a.getEmail());
            userRedisList.add(user);
        }
    }
}