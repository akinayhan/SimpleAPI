package com.akinayhan.images.controller;

import com.akinayhan.images.model.Image;
import com.akinayhan.images.model.User;
import com.akinayhan.images.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/images")
    public ResponseEntity<Image> uploadImage(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        Image uploadedImage = userService.uploadImage(userId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
    }

    @DeleteMapping("/{userId}/images/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String userId, @PathVariable String imageId) {
        userService.deleteImage(userId, imageId);
        return ResponseEntity.noContent().build();
    }
}
