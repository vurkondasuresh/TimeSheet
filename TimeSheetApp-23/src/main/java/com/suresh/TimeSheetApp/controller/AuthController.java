package com.suresh.TimeSheetApp.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.suresh.TimeSheetApp.dto.UserDto;
import com.suresh.TimeSheetApp.entity.Role;
import com.suresh.TimeSheetApp.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private UserService userService;
    private JavaMailSender javaMailSender;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>  getUsers()
    {
        List<UserDto> users=userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    
    //for retrive all unique data
    
    @PostMapping("/unique")
    public List<Role>retriveUnique(@RequestParam String request)
    {
    	
    	return userService.retriveUnique(request);
    }
    
    @GetMapping("/users/role")
    public ResponseEntity<List<UserDto>> getUsersByRole(@RequestParam("role") String role) {
        List<UserDto> users = userService.findUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")

    public ResponseEntity<UserDto> registeredUser(@Valid @RequestBody UserDto userDto,BindingResult result)
    {
        UserDto existingUser=userService.findUserByEmail(userDto.getEmail());
        if(existingUser !=null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty())
        {
            result.rejectValue("email",null,"There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(userDto);
        }
        UserDto savedUser=userService.saveUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserProfile(org.springframework.security.core.Authentication authentication) {
        String userName = authentication.getName();
        UserDto existingUser = userService.findUserByName(userName);

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Authenticate the user based on the email and password
        UserDto user = userService.findUserByEmail(email);
        /*if (user == null) {
            // User not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid email or password"));
        }

        if (!isValidPassword(user, password)) {
            // Invalid password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid email or password"));
        }*/

        // Assuming authentication is successful
        // Generate a token or session for the logged-in user
        String token = generateToken(user); // Replace this with your own token generation logic

        // Create a JSON object to return in the response
        Map<String, String> response = new HashMap<>();
        response.put("UserId & UserName", token);

        // Return the JSON object in the response
        return ResponseEntity.ok(response);
    }


    private boolean isValidPassword(UserDto user, String password) {
        // Compare the provided password with the user's stored password
        // You can use your password hashing and verification logic here

        // Assuming a simple comparison for demonstration purposes
        return user.getPassword().equals(password);
    }

    private String generateToken(UserDto user) {
        // Implement your token generation logic here
        // Generate a unique token for the user

        // Replace this with your own token generation code
        String username =  user.getId() + " " +user.getEmail();

        return username;
    }

    

 // forgot password
	
 		@GetMapping("/forgot")
 		public String forgetPwd() {
 			return "forgot";
 		}

 		@PostMapping("/forgotPwd")
 		public ResponseEntity<String> forgotPwdPage(@RequestParam("email") String email) {
 		    String temp = "";
 		    boolean status = userService.forgotPwd(email);

 		    if (status) {
 		        temp = "Your Password sent to your mail";
 		    } else {
 		        temp = "Invalid Email";
 		    }

 		    return ResponseEntity.ok().body("{\"message\": \"" + temp + "\"}");
 		}



}
