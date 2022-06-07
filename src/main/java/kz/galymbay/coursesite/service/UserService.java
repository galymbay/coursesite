package kz.galymbay.coursesite.service;

import kz.galymbay.coursesite.dto.User;
import kz.galymbay.coursesite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).stream().findFirst().get();
    }

    public User updateUser(User user, Long id) {
        User updatedUser = findById(id);

        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAge(user.getAge());
        updatedUser.setActive(user.isActive());
        updatedUser.setBlock(user.isBlock());

        userRepository.save(updatedUser);

        return updatedUser;
    }

    public User deleteById(Long id) {
        User user = findById(id);
        userRepository.deleteById(id);

        return user;
    }
}
