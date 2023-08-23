package com.xw.dao.impl;

import com.xw.dao.UserDAO;
import com.xw.dto.UserDTO;
import com.xw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO queryByUserName(String userName) {
        return userMapper.queryByUserName(userName);
    }

}
