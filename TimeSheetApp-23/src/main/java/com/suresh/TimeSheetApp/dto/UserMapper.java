package com.suresh.TimeSheetApp.dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.suresh.TimeSheetApp.entity.Role;
import com.suresh.TimeSheetApp.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getName().split(" ")[0]);
        userDto.setLastname(user.getName().split(" ").length > 1 ? user.getName().split(" ")[1] : "");
        userDto.setEmail(user.getEmail());
        userDto.setPassword(""); // Leave it empty for security reasons
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        userDto.setRoles(roles);
        return userDto;
    }



    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getFirstname() + " " + userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
