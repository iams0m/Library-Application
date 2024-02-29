package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원 등록
    @Transactional
    public void saveUser(UserCreateRequest request) {
        User user = userRepository.save(new User(request.getName(), request.getAge()));
    }

    // 전체 회원 목록 조회
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // 회원 정보 수정
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?;
        // Optional<User>

        // 1. id를 기준으로 1개의 데이터를 가져 와서 user가 없는 경우 예외 발생
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        // 2. user가 있다면, 객체 변경 후 저장
        user.updateName(request.getName());
        // userRepository.save(user);
    }

    // 회원 정보 삭제
    @Transactional
    public void deleteUser(String name) {

        // 1. 이름을 기준으로 1개의 데이터를 가져 와서 user가 없는 경우 예외 발생
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalAccessError::new);

        // 2 user가 있다면, 삭제
        userRepository.delete(user);
    }
}
