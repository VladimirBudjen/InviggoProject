package com.inviggoproject.service;

import com.inviggoproject.model.User;

public interface UserService {
    User findByUsername(String username);
    User save(User user);
}
