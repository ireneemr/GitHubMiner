package aiss.GitHubMiner.controller;

import aiss.GitHubMiner.Service.UserService;
import aiss.GitHubMiner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PostMapping("/users/{username}")
    public User postUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }


}
