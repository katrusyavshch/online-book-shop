package org.example.bookshop.service;

import org.example.bookshop.dto.UserRegistrationRequestDto;
import org.example.bookshop.dto.UserResponseDto;
import org.example.bookshop.exception.RegistrationException;

public interface UserService {
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
