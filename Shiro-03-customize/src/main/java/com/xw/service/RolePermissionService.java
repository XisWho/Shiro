package com.xw.service;

import com.xw.dto.RolePermissionDTO;

import java.util.List;

public interface RolePermissionService {

    List<RolePermissionDTO> queryByRoleName(String roleName);

}
