package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;

public interface UserService extends UserDetailsService
 {

    Iterable<User> getAllUsers();

    void addUser(User user);

    void update(User user, long id);

    User getUser(long id);

    void deleteUser(long id);

    User getUser(String name);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
