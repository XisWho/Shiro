package com.xw.dao.impl;

import com.xw.dao.RolePermissionDAO;
import com.xw.dto.RolePermissionDTO;
import com.xw.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolePermissionDAOImpl implements RolePermissionDAO {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermissionDTO> queryByRoleName(String roleName) {
        return rolePermissionMapper.queryByRoleName(roleName);
    }

}
