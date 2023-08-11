package com.ray.springmail.service.Impl;

import com.ray.springmail.dto.UserRequestDto;
import com.ray.springmail.entity.User;
import com.ray.springmail.repository.UserRepository;
import com.ray.springmail.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByUserId(Integer userId) {
        return userRepository.getUserByUserId(userId);
    }

    @Override
    public User register(UserRequestDto userRequestDto) {
        User isRegistered = userRepository.findByEmail(userRequestDto.getEmail());
        if (isRegistered != null) {
            log.warn("該 email {} 已被註冊", userRequestDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCreatedDate(ZonedDateTime.now());
        user.setLastModifiedDate(ZonedDateTime.now());
        return userRepository.save(user);
    }
}
