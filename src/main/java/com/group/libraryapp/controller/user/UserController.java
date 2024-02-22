package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원 등록
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    // 회원 정보 전체 조회
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        List<UserResponse> responses = new ArrayList<>();

        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    // 회원 정보 수정
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        // 사용자 존재 여부 확인
        String readSql = "SELECT * FROM user WHERE id = ?";

        // DB에 데이터 여부 확인
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        // 조회에 실패 하면 예외 발생
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    // 회원 정보 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        // 사용자 존재 여부 확인
        String readSql = "SELECT * FROM user WHERE name = ?";

        // DB에 데이터 여부 확인
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();

        // 조회에 실패 하면 예외 발생
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

}
