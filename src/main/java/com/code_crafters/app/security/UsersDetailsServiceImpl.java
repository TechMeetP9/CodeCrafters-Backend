package com.code_crafters.app.security;

import com.code_crafters.app.entity.Users;
import com.code_crafters.app.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + username
                ));

        return UsersDetailsImpl.build(user);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + email
                ));

        return UsersDetailsImpl.build(user);
    }
}