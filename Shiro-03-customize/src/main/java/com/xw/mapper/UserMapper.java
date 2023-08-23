package com.xw.mapper;

import com.xw.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO queryByUserName(String userName);
}
