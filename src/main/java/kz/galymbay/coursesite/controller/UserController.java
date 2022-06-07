package kz.galymbay.coursesite.controller;

import kz.galymbay.coursesite.dto.User;
import kz.galymbay.coursesite.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/get/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
