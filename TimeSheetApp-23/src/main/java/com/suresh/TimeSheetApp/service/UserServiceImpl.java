package com.suresh.TimeSheetApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.suresh.TimeSheetApp.dto.UserDto;
import com.suresh.TimeSheetApp.dto.UserMapper;
import com.suresh.TimeSheetApp.entity.Role;
import com.suresh.TimeSheetApp.entity.User;
import com.suresh.TimeSheetApp.repository.RoleRepository;
import com.suresh.TimeSheetApp.repository.UserRepository;
import com.suresh.TimeSheetApp.util.EmailUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    
    
    @Autowired
    private EmailUtils emailUtil;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<UserDto> findAllUsers() {

        List<User> users=userRepository.findAll();

        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null)
            return null;
        return userMapper.toDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user=userMapper.toEntity(userDto);
        List<Role> roles=new ArrayList<>();
        for(String roleName:userDto.getRoles())
        {
            Role role=roleRepository.findByName(roleName);
            if(role==null){
                role= Role.builder().name(roleName).build();
                roleRepository.save(role);
            }
            roles.add(role);
        }
        user.setRoles(roles);
        User savedUser=userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto findUserByName(String userName) {
        User user= userRepository.findByEmail(userName);
        if(user!=null)
            return userMapper.toDto(user);
        else
            return null;

    }


	@Override
	public boolean forgotPwd(String email) {
		// check Record Presence in db with given mail
				User entity = userRepository.findByEmail(email);

				// if record not available sent msg
				if (entity == null) {
					return false;
				}

				// if record available in db sent password and sent success msg
				String subject = "<h1>Recover Password<h1>";
				String body = "Your Pwd :: " + entity.getPassword();

				emailUtil.sendEmail(email, subject, body);

				return true;
		
	}

	@Override
    public List<UserDto> findUsersByRole(String role) {
        List<User> users;
        if (role.equals("ROLE_ADMIN")) {
            users = userRepository.findByRoles_Name("ROLE_ADMIN");
        } else if (role.equals("ROLE_USER")) {
            users = userRepository.findByRoles_Name("ROLE_USER");
        } else {
            // Handle invalid role
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

	@Override
	public List<Role> retriveUnique(String request) {
		Role role=new Role();
		role.setName(request);
		Example<Role> example =Example.of(role);
		List<Role> roles=roleRepository.findAll(example);
		return roles;
	}

}
