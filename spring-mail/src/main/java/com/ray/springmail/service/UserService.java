package com.ray.springmail.service;

import com.ray.springmail.dto.UserRequestDto;
import com.ray.springmail.entity.User;

public interface UserService {

    User getByUserId(Integer userId);

    User register(UserRequestDto userRequestDto);
}
