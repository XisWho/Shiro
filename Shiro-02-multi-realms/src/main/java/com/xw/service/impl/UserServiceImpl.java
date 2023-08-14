package com.xw.service.impl;

import com.xw.dao.UserDAO;
import com.xw.dto.UserDTO;
import com.xw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO queryByUserName(String userName) {
        return userDAO.queryByUserName(userName);
    }

}
