package com.example.kob.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kob.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
