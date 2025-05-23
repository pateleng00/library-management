package com.spring.learning.library_management.user.controller;


import com.spring.learning.library_management.common.dto.RestApiResponse;
import com.spring.learning.library_management.user.dto.request.CreateUser;
import com.spring.learning.library_management.user.dto.request.UserByEmail;
import com.spring.learning.library_management.user.repository.UserRepository;
import com.spring.learning.library_management.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final IUserService userService;


    @GetMapping("/fetch-all")
    public ResponseEntity<RestApiResponse<Object>> getAllUsers() {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userRepository.findAll()));
    }

    @GetMapping("/fetch-by-id/{id}")
    public ResponseEntity<RestApiResponse<Object>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id))));
    }

    @GetMapping("/fetch-by-email")
    public ResponseEntity<RestApiResponse<Object>> getUserByEmail(@RequestBody UserByEmail userByEmail ) throws Exception {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userService.findByEmail(userByEmail)));
    }

    @PostMapping("/add")
    public ResponseEntity<RestApiResponse<Object>> addUser(@RequestBody CreateUser createUser) throws Exception {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userService.addUser(createUser)));
    }


    @DeleteMapping("/deactivate-user/{id}")
    public ResponseEntity<RestApiResponse<Object>> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(RestApiResponse.buildSuccess("User deactivated successfully with id: " + id));
    }


}
