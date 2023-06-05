package com.akinayhan.images.service;

import com.akinayhan.images.model.Image;
import com.akinayhan.images.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    User createUser(User user);
    User updateUser(String id, User user);
    void deleteUser(String id);
    Image uploadImage(String userId, MultipartFile file);
    void deleteImage(String userId, String imageId);
}
