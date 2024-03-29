package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    // 회원 등록
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    // 회원 정보 전체 조회
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        List<UserResponse> responses = new ArrayList<>();
        return userService.getUsers();
    }

    // 회원 정보 수정
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    // 회원 정보 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

}
