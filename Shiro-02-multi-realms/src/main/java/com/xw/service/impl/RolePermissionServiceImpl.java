package com.xw.service.impl;

import com.xw.dao.RolePermissionDAO;
import com.xw.dto.RolePermissionDTO;
import com.xw.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    @Override
    public List<RolePermissionDTO> queryByRoleName(String roleName) {
        return rolePermissionDAO.queryByRoleName(roleName);
    }

}
