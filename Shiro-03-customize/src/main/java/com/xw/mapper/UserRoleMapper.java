package com.xw.mapper;

import com.xw.dto.UserRoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<UserRoleDTO> queryByUserName(String userName);
}
