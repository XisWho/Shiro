package com.xw.dao;

import com.xw.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleDAO {
    List<UserRoleDTO> queryByUserName(String userName);

}
