package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    private final UserJdbcRepository userJdbcRepository;

    // 회원 등록
    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    // 전체 회원 목록 조회
    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    // 회원 정보 수정
    public void updateUser(UserUpdateRequest request) {

        // UserRepository 연결
        // 1. 사용자 존재 여부 확인
        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        // 2. 사용자 이름 수정
        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    // 회원 정보 삭제
    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUser(name);
    }
}
