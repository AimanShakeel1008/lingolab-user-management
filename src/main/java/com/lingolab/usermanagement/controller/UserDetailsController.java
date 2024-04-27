package com.lingolab.usermanagement.controller;

import com.lingolab.usermanagement.model.UserAllDetails;
import com.lingolab.usermanagement.model.UserAllDetailsRequest;
import com.lingolab.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @PostMapping("/details")
    public ResponseEntity<UserAllDetails> saveUserDetails(@RequestBody UserAllDetailsRequest userAllDetailsRequest) {
        UserAllDetails savedDetail = userService.saveUserDetails(userAllDetailsRequest);
        return new ResponseEntity<>(savedDetail, HttpStatus.CREATED);
    }

    @GetMapping("/details/{username}")
    public ResponseEntity<UserAllDetails> getUserDetails(@PathVariable String username) {
        UserAllDetails detail = userService.getUserDetailsByUserName(username);
        return ResponseEntity.ok(detail);
    }
}
