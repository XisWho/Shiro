package com.xw.service;

import com.xw.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleService {

    List<UserRoleDTO> queryByUserName(String userName);

}
