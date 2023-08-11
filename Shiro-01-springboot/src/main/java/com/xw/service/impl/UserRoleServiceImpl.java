package com.xw.service.impl;

import com.xw.dao.UserRoleDAO;
import com.xw.dto.UserRoleDTO;
import com.xw.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public List<UserRoleDTO> queryByUserName(String userName) {
        return userRoleDAO.queryByUserName(userName);
    }

}
