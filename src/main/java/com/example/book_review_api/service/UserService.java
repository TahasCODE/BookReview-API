package com.example.book_review_api.service;

import com.example.book_review_api.dto.UserRequestDTO;
import com.example.book_review_api.dto.UserResponseDTO;
import com.example.book_review_api.entity.User;
import com.example.book_review_api.exception.ResourceNotFoundException;
import com.example.book_review_api.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

private final UserRepository userRepository;

public UserResponseDTO createUser(UserRequestDTO dto){
    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    User saved = userRepository.save(user);
    return toResponseDTO(saved);
}

public List<UserResponseDTO> getAllUsers(){
    return userRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
}


public UserResponseDTO getUserById(Long id){
    return toResponseDTO(getUserEntityById(id));
}

    private UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(
            user.getId(), user.getName(), user.getEmail()
    );
    }

    public User getUserEntityById(Long id) {
      return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found " + id));
    }
}
