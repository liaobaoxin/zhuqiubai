package com.zqb.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zqb.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface UserMapper extends BaseMapper<User> {

}