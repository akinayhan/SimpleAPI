package com.akinayhan.images.service;

import com.akinayhan.images.model.Image;
import com.akinayhan.images.model.User;
import com.akinayhan.images.repository.UserRepository;
import com.akinayhan.images.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setImages(user.getImages());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        userRepository.delete(user);
    }

    @Override
    public Image uploadImage(String userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        try {
            Image image = new Image();
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setData(file.getBytes());
            image.setImageId(userId+file.getOriginalFilename());

            user.getImages().add(image);
            userRepository.save(user);

            return image;
        } catch (Exception ex) {
            throw new RuntimeException("Error uploading image for user " + userId, ex);
        }
    }

    @Override
    public void deleteImage(String userId, String imageId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        List<Image> images = user.getImages();

        for (Image image : images) {
            if (image.getImageId().equals(imageId)) {
                images.remove(image);
                userRepository.save(user);
                return;
            }
        }

        throw new ResourceNotFoundException("Image not found with id " + imageId + " for user " + userId);
    }
}