package com.app.demo.controller;

import com.app.demo.model.User;
import com.app.demo.params.UserParams;
import com.app.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /userAPI (after Application path)
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody UserParams user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestBody means it is a JSON parameter from the GET or POST request

        if(user.getFirstName() == null || user.getFirstName().isEmpty())
            return "First name cannot be null";
        if(user.getLastName() == null || user.getLastName().isEmpty())
            return "Last name cannot be null";
        if(user.getNickName() == null && user.getNickName().isEmpty())
            return "Nick name cannot be null";
        if(user.getAge() < 0)
            return "Age cannot be null";
        if(user.getEmail() == null && user.getEmail().isEmpty())
            return "First name cannot be null";

        User n = new User();
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setNickName(user.getNickName());
        n.setAge(user.getAge());
        n.setEmail(user.getEmail());
        userRepository.save(n);

        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUser (@PathVariable int id) {
        userRepository.deleteById(id);
        return "Deleted";
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateUser (@RequestBody UserParams user,
                                            @PathVariable int id) {

        User n = userRepository.findById(id).get();
        System.out.println(user.toString());

        if(user == null) return "Nothing to update";

        if(user.getFirstName() != null && !user.getFirstName().isEmpty()) n.setFirstName(user.getFirstName());
        if(user.getLastName() != null && !user.getLastName().isEmpty()) n.setLastName(user.getLastName());
        if(user.getNickName() != null && !user.getNickName().isEmpty()) n.setNickName(user.getNickName());
        if(user.getAge() > 0) n.setAge(user.getAge());
        if(user.getEmail() != null && !user.getEmail().isEmpty()) n.setEmail(user.getEmail());
        userRepository.save(n);
        return "Updated";
    }
}