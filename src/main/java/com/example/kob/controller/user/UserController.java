package com.example.kob.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.kob.mapper.UserMapper;
import com.example.kob.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user/all/")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @RequestMapping("/user/{userId}/")
    public User getuser(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("id", userId);
        return userMapper.selectOne(queryWrapper);
    }
    @RequestMapping("/user/add/{userId}/{username}/{password}/")
    public String addUser (
            @PathVariable int userId,
            @PathVariable String username,
            @PathVariable String password) {
        if (password.length() < 6) {
            return "密码太短";
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, username, encodedPassword);
        userMapper.insert(user);
        return "Add user successfully";
    }

    @PostMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Delete user successfully";
    }


}
