package kz.galymbay.coursesite.service;

import kz.galymbay.coursesite.dto.Role;
import kz.galymbay.coursesite.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public Role updateRole(Role role, Long id) {
        Role updatedRole = findById(id);

        updatedRole.setRoleName(role.getRoleName());
        roleRepository.save(updatedRole);

        return updatedRole;
    }

    public Role deleteById(Long id) {
        Role role = findById(id);
        roleRepository.deleteById(id);

        return role;
    }
}
