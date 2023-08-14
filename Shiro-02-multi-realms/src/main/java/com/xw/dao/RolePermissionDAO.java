package com.xw.dao;

import com.xw.dto.RolePermissionDTO;

import java.util.List;

public interface RolePermissionDAO {
    List<RolePermissionDTO> queryByRoleName(String roleName);

}
