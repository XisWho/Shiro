package com.xw.mapper;

import com.xw.dto.RolePermissionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    List<RolePermissionDTO> queryByRoleName(String roleName);

}
