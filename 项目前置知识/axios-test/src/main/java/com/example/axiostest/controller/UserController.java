package com.example.axiostest.controller;

import com.example.axiostest.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author 邓和颖
 * @since 2023/9/28 11:04
 */
// 使当前 controller 支持跨域请求
@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class UserController {

    // get 请求
    @GetMapping("/user/query")
    public User queryUser() {
        return new User(2000300211,"邓和颖",22,"男");
    }

    // get 请求
    @GetMapping("/user/get")
    public User queryUserByNameAndId(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return new User(id,name,22,"男");
    }

    @PostMapping("/user/post")
    public User queryUserByNameAndIdPost(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return new User(id,name,22,"男");
    }

    @PostMapping("/user/post2")
    public User queryUserByNameAndIdPost(@RequestBody User user) {

        user.setAge(22);
        user.setSex("男");

        return user;
    }
}
