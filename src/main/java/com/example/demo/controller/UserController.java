package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/")
    public String login_page()
    {
        return"login_page";
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/login/{email}")
    public User getEmployee(@PathVariable("email") String email) {
        return userRepo.getUserByEmail(email);
    }

    @DeleteMapping("/login/{email}")
    public String deleteEmployee(@PathVariable("id") String email) {
        return  userRepo.delete(email);
    }

//    @PutMapping("/employee/{email}")
//    public String updateEmployee(@PathVariable("email") String email, @RequestBody User user) {
//        return userRepo.update(email, user);
//    }
}