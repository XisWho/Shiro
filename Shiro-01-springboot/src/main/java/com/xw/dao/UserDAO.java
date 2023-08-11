package com.xw.dao;

import com.xw.dto.UserDTO;

public interface UserDAO {
    UserDTO queryByUserName(String userName);
}
