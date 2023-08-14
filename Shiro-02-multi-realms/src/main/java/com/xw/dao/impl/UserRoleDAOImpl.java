package com.xw.dao.impl;

import com.xw.dao.UserRoleDAO;
import com.xw.dto.UserRoleDTO;
import com.xw.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleDTO> queryByUserName(String userName) {
        return userRoleMapper.queryByUserName(userName);
    }

}
