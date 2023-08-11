package com.xw.service;

import com.xw.dto.UserDTO;

public interface UserService {

    UserDTO queryByUserName(String userName);

}
