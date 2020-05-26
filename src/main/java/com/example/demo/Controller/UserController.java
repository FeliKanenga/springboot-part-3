package com.example.demo.Controller;
import com.example.demo.Model.User;
import com.example.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public UserController(UserServiceImpl service) {
        this.userService = service;
    }

    @PostMapping("/addPerson")
    public String createUser(@RequestBody User user) {
       return userService.addUser(user.getId(), user.getName(),user.getSurname());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
       userService.removeUser(id);
    }

    @GetMapping("/GetById/{id}")
    public String viewUser(@PathVariable int id) {
        return userService.getUser(id);
    }




}
