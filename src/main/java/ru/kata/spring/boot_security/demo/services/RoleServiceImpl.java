package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getListRoles(String... roles) {
        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            Role r = roleRepository.findByAuthority(role);
            if (r == null) {
                r = new Role(role);
            }
            roleList.add(r);
        }
        return roleList;
    }
}
