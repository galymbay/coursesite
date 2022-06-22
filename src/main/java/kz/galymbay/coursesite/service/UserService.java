package kz.galymbay.coursesite.service;

import kz.galymbay.coursesite.dto.Role;
import kz.galymbay.coursesite.dto.User;
import kz.galymbay.coursesite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role_user = roleService.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>() {{
            add(role_user);
        }};
        user.setRoles(roles);
        user.setActivationCode(UUID.randomUUID().toString());

        if (!user.getEmail().isEmpty()){
            String message = String.format("Hello, %s .\n " +
                    "To activate your account, please visit next link: " +
                    "http://localhost:8080/users/activate/code/" + user.getActivationCode(), user.getName());

            mailSender.send(user.getEmail(), "Activation Code", message);
        }

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
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public String activateUser(String token) {
        User user = userRepository.findByActivationCode(token);

        if (user != null) {
            user.setActive(true);
            user.setActivationCode(null);
            saveUser(user);

            return "Successfully activated";
        } else return "User not found";
    }
}
